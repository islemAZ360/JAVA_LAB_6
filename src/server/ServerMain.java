package server;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

import common.Request;
import common.Serializer;
import common.Response;
import common.models.Const;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import common.utils.HumanBeingFileManager;

public class ServerMain {
    private static final Logger logger = LogManager.getLogger(ServerMain.class);

    private static final String FILE_PATH = "data/human.csv";

    private static RequestHandler requestHandler;

    public static void main(String[] args) {
        logger.info("Hello! Log4j2 run successfully.");
//        logger.error("Here an example of error log.");

        CollectionManager collectionManager = new CollectionManager();
        HumanBeingFileManager humanBeingFileManager = new HumanBeingFileManager(FILE_PATH);
        CommandManager commandManager = new CommandManager(collectionManager, humanBeingFileManager);
        requestHandler = new RequestHandler(commandManager);

        // 1. đọc file khi khởi động
        humanBeingFileManager.readFileAndLoadHumanBeing(collectionManager);

//        // 2. save tự động khi server tắt
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Сервер завершает работу. Сохранение коллекции...");
            humanBeingFileManager.saveAll(collectionManager);
        }));
//
//        // 3. tạm thời chỉ test startup/shutdown cho Issue 4
//        System.out.println("Сервер запущен.");
//        System.out.println(collectionManager.getCollectionInfo());
        try (Selector selector = Selector.open();
             ServerSocketChannel serverChannel = ServerSocketChannel.open()) {

            // 1. Config Server Non-blocking
            serverChannel.bind(new InetSocketAddress(Const.host, Const.port));
            serverChannel.configureBlocking(false);

            // Event accept connection
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            logger.info("\uD83D\uDE80 Server NIO is running at localhost: {}", Const.port);
//            System.out.println("🚀 Server NIO is running at localhost:" + common.models.Const.port);

            while (true) {
                // Đợi sự kiện (có thể kết hợp kiểm tra CLI ở đây)
                if (selector.select(1000) == 0) continue;

                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();

                    if (!key.isValid()) continue;

                    if (key.isAcceptable()) {
                        handleAccept(serverChannel, selector);
                    } else if (key.isReadable()) {
                        handleRead(key);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- HANDSHAKE (ACCEPT) ---
    private static void handleAccept(ServerSocketChannel serverChannel, Selector selector) throws IOException {
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false); // => NIO
        // Gắn một "túi chứa" mới cho Client này
        clientChannel.register(selector, SelectionKey.OP_READ, new ConnectionState());

        // Sent 2 byte header response to CLIENT to accept
        // 0x-hex 0byte (hex signature = 4 bit => AC = 8 bít = 1 byte) (OxAC ED: STREAM_MAGIC (Java Serialized Identifier), Ox00 05: STREAM_VERSION (Serialization version))
//        byte[] header = {(byte) 0xAC, (byte) 0xED, (byte) 0x00, (byte) 0x05};
//        clientChannel.write(ByteBuffer.wrap(header));
        logger.info("\uD83E\uDD1D Sent greeting and accepted: {}", clientChannel.getRemoteAddress());
//        System.out.println("🤝 Sent greeting and accepted: " + clientChannel.getRemoteAddress());
    }

    // --- RECEIPT REQUEST (READ) ---
    private static void handleRead(SelectionKey key) {
        SocketChannel sc = (SocketChannel) key.channel();
        ConnectionState state = (ConnectionState) key.attachment();

        try {
            // A. Đọc Header (4 byte)
            if (!state.hasReadSize) {
                int read = sc.read(state.headerBuffer);
                if (read == -1) {
                    // Client closed connection
                    key.cancel();
                    sc.close();
                    logger.info("👋 Client leaved.");
//                    System.out.println("👋 Client leaved.");
                }

                if (!state.headerBuffer.hasRemaining()) {
                    state.headerBuffer.flip();
                    int size = state.headerBuffer.getInt();
                    logger.info("\uD83D\uDCE6 Received Object: {} bytes", size);
//                    System.out.println("📦 Received Object: " + size + " bytes");

                    if (size > 0) {
                        state.dataBuffer = ByteBuffer.allocate(size);
                        state.hasReadSize = true;
                    } else {
                        state.headerBuffer.clear(); // Reset nếu size lỗi
                    }
                }
            }

            // B. Đọc Data (sau khi đã có Size)
            if (state.hasReadSize && state.dataBuffer != null) {
                sc.read(state.dataBuffer);

                if (!state.dataBuffer.hasRemaining()) {
                    // C. ĐÃ NHẬN ĐỦ REQUEST
                    byte[] data = state.dataBuffer.array();
                    Object requestObj = Serializer.deserialize(data);
                    logger.info("\uD83D\uDCE5 Received Request: {}", requestObj);
//                    System.out.println("📥 Received Request: " + requestObj);

                    // Xử lý logic xong thì Reset để chờ Request tiếp theo
                    state.headerBuffer.clear();
                    state.dataBuffer = null;
                    state.hasReadSize = false;

                    // (OPTIONAL) Send response here or register OP_WRITE
                    Response response = requestHandler.handle((Request) requestObj);
                    sendResponse(sc, response);
                }
            }
        } catch (Exception e) {
            logger.error("❌ Reading data error: {}", e.getMessage());
//            System.err.println("❌ Reading data error: " + e.getMessage());
            try { sc.close(); } catch (IOException _) {}
        }
    }

    /**
     * Gửi phản hồi từ Server về Client theo giao thức: [Size] + [Object Data]
     * @param channel Kênh kết nối với Client
     * @param responseObj Đối tượng phản hồi cần gửi
     */
    private static void sendResponse(SocketChannel channel, Response responseObj) {
        try {
            // 1. Convert Response to byte[] (Serialization)
            // Lưu ý: Response have to implement Serializable
            byte[] data = Serializer.serialize(responseObj);

            // 2. Create Buffer to contain 4 byte Size + byte[] data
            ByteBuffer buffer = ByteBuffer.allocate(4 + data.length);

            // 3. Đổ dữ liệu vào Buffer
            buffer.putInt(data.length); // Write 4 byte of size first
            buffer.put(data);             // Write Object data in byte

            // 4. Handle pointer
            buffer.flip();

            // 5. Sent data into TCP
            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }
            logger.info("📤 Send response to Client ({} bytes)", data.length);
//            System.out.println("📤 Send response to Client (" + data.length + " bytes)");

        } catch (IOException e) {
            logger.error("❌ Response sending error: {}", e.getMessage());
//            System.err.println("❌ Response sending error: " + e.getMessage());
            try { channel.close(); } catch (IOException ignored) {}
        }
    }
}
