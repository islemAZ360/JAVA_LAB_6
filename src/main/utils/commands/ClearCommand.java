package main.utils.commands;

import main.utils.CollectionManager;
import main.utils.Command;
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

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Команда clear не принимает аргументы.");
            System.out.println("Использование: clear");
            return;
        }

        if (collectionManager.isEmpty()) {
            System.out.println("Коллекция уже пуста.");
            return;
        }

        System.out.println("В коллекции " + collectionManager.size() + " элементов.");
        System.out.print("Вы уверены, что хотите очистить коллекцию? (yes/no): ");

        String answer = scanner.nextLine().trim().toLowerCase();

        if (answer.equals("yes") || answer.equals("y")) {
            int oldSize = collectionManager.size();
            collectionManager.clear();

            System.out.println("Коллекция очищена. Удалено элементов: " + oldSize);
            System.out.println("Не забудьте ввести 'save' для сохранения изменений в файл.");
        } else {
            System.out.println("Операция отменена.");
        }
    }
}