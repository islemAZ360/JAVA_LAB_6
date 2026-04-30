package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import common.Request;
import common.Response;
import common.Serializer;
import common.models.Const;

public class ClientMain {
    public static void main(String[] args) {
        // Use try-with-resources to auto close socket when error
        // Init output first to avoid Deadlock (server and client will be waiting header for each other => all hang)
        try (Socket socket = new Socket(Const.host, Const.port);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             // Only this one communicates with Socket (read header and data => ois)
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("✅ Connected to server successfully!");
            System.out.printf(Const.GREEN + Const.cat + Const.RESET);

            // 2. Send Header instantly to server to get handshake response
            // Translate byte[] to obj in deserialize
            // ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            // oos.flush();

            while (true) {
                System.out.println("\nПрограмма готова к работе! Введите 'help' для подержки || 'exit' для выхода.");
                System.out.println("What are you thinking?");
                System.out.print(">>> ");
                String input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input)) break;

                // Handles one or multiple spaces perfectly
                String[] arguments = input.split("\\s+");

                // 1. Tạo đối tượng Request
                Request req = new Request(
                        arguments[0],
                        arguments.length>1? arguments[1]:null,
                        "hello");

                // 2. Serialize Object ra mảng byte để đếm độ dài
                byte[] dataBytes = Serializer.serialize(req);

                // 3. GỬI: 4 byte Header (độ dài) + Dữ liệu thật
                dos.writeInt(dataBytes.length); // Ghi 4 byte int
                dos.write(dataBytes);           // Ghi mảng byte
                dos.flush();
                System.out.println("🚀 Request was sent (" + dataBytes.length + " bytes)");

                // 4. NHẬN: Đợi phản hồi từ Server (Blocking)
                // Lưu ý: Phía Server cũng phải gửi theo quy tắc tương tự
                // Ở đây mình minh họa đọc trực tiếp Object nếu Server dùng ObjectOutputStream
                try {
                    // Đọc nhãn (4 byte)
                    int size = dis.readInt();
                    System.out.printf("First 4 received bytes in (Hex): %08X\n", size);
                    // Đọc gói hàng
                    byte[] data = new byte[size];
                    dis.readFully(data);

                    // Giải mã (Trong này vẫn dùng ObjectInputStream nhưng là dùng "nội bộ" với mảng byte)
                    Response resp = (Response) Serializer.deserialize(data);

                    System.out.println(resp);

                    System.out.println("📩 Server response:\n" + resp.getMessage());
                } catch (ClassNotFoundException e) {
                    System.err.println("❌ Can not understand data type from Server.");
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.err.println("❌ Connecting error: Server busy or not response. (If server is not running, start server first!)");
        }
    }
}
