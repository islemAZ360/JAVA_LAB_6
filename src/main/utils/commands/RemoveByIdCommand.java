package main.utils.commands;

import main.utils.CollectionManager;
import main.utils.Command;

public class RemoveByIdCommand implements Command {

    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент по ID";
    }

    @Override
    public void execute(String[] args) {

        if (args.length == 0) {
            System.out.println("Введите ID.");
            return;
        }
        try {
            long id = Long.parseLong(args[1]);
            boolean removed = collectionManager.removeById(id);
            if (removed) {
                System.out.println("Элемент удален.");
            } else {
                System.out.println("Элемент не найден.");
            }
        }
        catch(Exception e){
            System.err.println("Ошибка ID: ID должен быть числом.");

        }

    }
}