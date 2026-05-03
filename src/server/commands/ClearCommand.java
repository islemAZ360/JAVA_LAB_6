package server.commands;

import common.Command;
import common.Request;
import common.Response;
import common.StatusCode;
import server.CollectionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Команда clear: очищает коллекцию (удаляет все элементы).
 * Version có xác nhận từ người dùng trước khi xóa.
 */
public class ClearCommand implements Command {

    private final CollectionManager collectionManager;
    private final Scanner scanner;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "clear : очистить коллекцию (с подтверждением)";
    }

//    @Override
//    public String execute(String[] args) {
//        System.out.println("В коллекции " + collectionManager.size() + " элементов.");
//        System.out.print("Вы уверены, что хотите очистить коллекцию? (yes/no): ");
//
//        String answer = scanner.nextLine().trim().toLowerCase();
//
//        if (answer.equals("yes") || answer.equals("y")) {
//            int oldSize = collectionManager.size();
//            collectionManager.clear();
//            return "Коллекция очищена. Удалено элементов: " + oldSize + "\nНе забудьте ввести 'save' для сохранения изменений в файл.";
//
//        } else {
//            return "Операция отменена.";
//        }
//    }

    @Override
    public Response execute(Request request) {
        if (request.getStringArgument() != null) {
            return new Response("Команда clear не принимает аргументы.\nИспользование: clear", StatusCode.BAD_REQUEST, null);
        }

        if (collectionManager.isEmpty()) {
            return new Response("Коллекция уже пуста", StatusCode.OK, null);
        }

        int size = collectionManager.size();
        collectionManager.clear();

        return new Response(
            "Коллекция очищена. Удалено элементов: " + size,
            StatusCode.OK,
            null
        );
    }
}
