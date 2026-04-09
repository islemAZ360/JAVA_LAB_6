package main.utils.commands;

import main.objects.HumanBeing;
import main.utils.CollectionManager;
import main.utils.Command;
import main.utils.InputManager;

import java.util.Arrays;

public class AddIfMinCommand implements Command {

    private final CollectionManager collectionManager;
    private final InputManager inputManager;

    public AddIfMinCommand(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public String getName() {
        return "add_if_min";
    }

    @Override
    public String getDescription() {
        return "add_if_min {element} : добавить элемент, если его значение меньше минимального";
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Ошибка: не указан ID. Использование: add_if_min {id}");
            return;
        }

        long newId;
        try {
            newId = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть целым числом");
            return;
        }

        if (newId <= 0) {
            System.out.println("Ошибка: ID должен быть положительным числом");
            return;
        }

        Long minId = null;
        if (!collectionManager.isEmpty()) {
            minId = collectionManager.getMin().getId();
        }

        if (minId == null) {
            System.out.println("Коллекция пуста. Можно добавить элемент с любым ID.");
        }

        else if (newId >= minId) {
            System.out.println("ID " + newId + " не меньше минимального ID в коллекции (" + minId + ").");
            System.out.println("Элемент не будет добавлен.");
            return;
        } else {
            System.out.println("ID " + newId + " меньше минимального ID (" + minId + "). Можно добавлять элемент.");
        }

        if (collectionManager.getHumanById(newId) != null) {
            System.out.println("Ошибка: элемент с ID " + newId + " уже существует в коллекции!");
            return;
        }

        HumanBeing newHuman;

        if(args.length == 12) {
            newHuman = this.inputManager.createHumanBeingWithId(newId, Arrays.copyOfRange(args, 2, args.length + 1));
        } else if (args.length == 2) {
            // Если все условия пройдены, приступаем к вводу данных
            System.out.println("Введите данные для нового элемента с ID = " + newId + ":");
            newHuman = this.inputManager.createHumanBeingWithId(newId);
        } else {
            throw new IllegalArgumentException("Entry arguments for HumanBeing are not correct.");
        }

        collectionManager.add(newHuman);
        System.out.println("Элемент с ID " + newId + " успешно добавлен в коллекцию.");
    }
}