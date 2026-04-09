package main.utils.commands;

import main.objects.HumanBeing;
import main.utils.CollectionManager;
import main.utils.Command;
import main.utils.InputManager;

import java.util.Arrays;

public class UpdateCommand implements Command {

    private final CollectionManager collectionManager;
    private final InputManager inputManager;

    public UpdateCommand(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "update id {element} : обновить элемент по ID";
    }

    @Override
    public void execute(String[] args) {

        if (args.length == 0) {
            System.out.println("Введите ID.");
            return;
        }

        if (args.length < 2) {
            System.out.println("Использование: update id");
            return;
        }

        HumanBeing newHuman;

        long id = Long.parseLong(args[1]); // args[1] is ID

        if(args.length == 12) {
            newHuman = inputManager.readHumanBeing(Arrays.copyOfRange(args, 2, args.length + 1));
        } else if (args.length == 2) {
            newHuman = this.inputManager.readHumanBeing();
        } else {
            throw new IllegalArgumentException("Entry arguments for HumanBeing are not correct.");
        }

        boolean updated = collectionManager.update(id, newHuman);

        if (updated) {
            System.out.println("Элемент обновлен.");
        } else {
            System.out.println("Элемент с таким ID не найден.");
        }
    }
}