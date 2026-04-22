package server;

import main.utils.CollectionManager;
import main.utils.HumanBeingFileManager;

public class ServerMain {

    private static final String FILE_PATH = "data/human.csv";

    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        HumanBeingFileManager humanBeingFileManager = new HumanBeingFileManager(FILE_PATH);

        // 1. đọc file khi khởi động
        humanBeingFileManager.readFileAndLoadHumanBeing(collectionManager);

        // 2. save tự động khi server tắt
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nСервер завершает работу. Сохранение коллекции...");
            humanBeingFileManager.saveAll(collectionManager);
        }));

        // 3. tạm thời chỉ test startup/shutdown cho Issue 4
        System.out.println("Сервер запущен.");
        System.out.println(collectionManager.getCollectionInfo());
    }
}