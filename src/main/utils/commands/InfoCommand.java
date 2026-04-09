package main.utils.commands;

import main.utils.CollectionManager;
import main.utils.Command;

/**
 * Команда info: выводит информацию о коллекции.
 */
public class InfoCommand implements Command {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "info : вывести информацию о коллекции (тип, дата инициализации, количество элементов)";
    }

    @Override
    public void execute(String[] args) {
        System.out.println(collectionManager.getCollectionInfo());
    }
}
