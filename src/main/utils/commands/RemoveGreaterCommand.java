package main.utils.commands;

import main.objects.HumanBeing;
import main.utils.CollectionManager;
import main.utils.Command;

public class RemoveGreaterCommand implements Command {

    private final CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescription() {
        return "remove_greater id : удалить элементы с ID больше указанного";
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Использование: remove_greater id");
            System.out.println("Пример: remove_greater 10");
            System.out.println("(удалить все элементы с ID больше 10)");
            return;
        }

        try {
            long id = Long.parseLong(args[1]);

            HumanBeing element = collectionManager.getHumanById(id);

            if (element == null) {
                System.out.println("Элемент с ID " + id + " не найден.");
                return;
            }

            int oldSize = collectionManager.size();

            collectionManager.removeGreater(element);

            int removedCount = oldSize - collectionManager.size();

            if (removedCount > 0) {
                System.out.println("Удалено элементов с ID больше " + id + ": " + removedCount);
                System.out.println("Текущий размер коллекции: " + collectionManager.size());
            } else {
                System.out.println("Нет элементов с ID больше " + id);
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом.");
        } catch (Exception e) {
            System.out.println("Ошибка при выполнении команды: " + e.getMessage());
        }
    }
}