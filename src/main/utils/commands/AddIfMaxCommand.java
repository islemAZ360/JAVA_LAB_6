package main.utils.commands;

import main.objects.HumanBeing;
import main.utils.CollectionManager;
import main.utils.Command;
import main.utils.InputManager;

import java.util.Arrays;

/**
 * Команда добавляет новый элемент в коллекцию, только если его ID превышает
 * максимальный ID текущей коллекции.
 */

public class AddIfMaxCommand implements Command {
    private final CollectionManager collectionManager;
    private final InputManager inputManager;

    public AddIfMaxCommand(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getDescription() {
        return "add_if_max : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

    /**
     * Выполняет команду add_if_max
     * @param args аргументы команды: args[1] = ID нового элемента
     */
    @Override
    public void execute(String[] args) {
        // Kiểm tra argument
        if (args.length < 2) {
            System.out.println("Ошибка: не указан ID. Использование: add_if_max {id}");
            return;
        }

        // Проверка аргументов
        long newId;
        try {
            newId = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть целым числом");
            return;
        }

        // Проверка валидности ID (должен быть > 0)
        if (newId <= 0) {
            System.out.println("Ошибка: ID должен быть положительным числом");
            return;
        }

        // Получение текущего максимального ID
        Long maxId = null;
        if (!collectionManager.isEmpty()) {
            maxId = collectionManager.getMaxId();
        }

        // Случай пустой коллекции
        if (maxId == null) {
            System.out.println("Коллекция пуста. Можно добавить элемент с любым ID.");
        }
        // Сравнение нового ID с максимальным
        else if (newId <= maxId) {
            System.out.println("ID " + newId + " не превышает максимальный ID в коллекции (" + maxId + ").");
            System.out.println("Элемент не будет добавлен.");
            return;
        } else {
            System.out.println("ID " + newId + " больше максимального ID (" + maxId + "). Можно добавлять элемент.");
        }

        // Проверка, существует ли уже элемент с таким ID
        if (collectionManager.getHumanById(newId) != null) {
            System.out.println("Ошибка: элемент с ID " + newId + " уже существует в коллекции!");
            return;
        }

        HumanBeing newHuman;

        if(args.length == 12) {
            newHuman = this.inputManager.createHumanBeingWithId(newId,Arrays.copyOfRange(args, 2, args.length + 1));
        } else if (args.length == 2) {
            // Если все условия пройдены, приступаем к вводу данных
            System.out.println("Введите данные для нового элемента с ID = " + newId + ":");
            newHuman = this.inputManager.createHumanBeingWithId(newId);
        } else {
            throw new IllegalArgumentException("Entry arguments for HumanBeing are not correct.");
        }

        // Добавление в коллекцию
        collectionManager.add(newHuman);
        System.out.println("Элемент с ID " + newId + " успешно добавлен в коллекцию.");
    }
}