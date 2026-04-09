package main.utils.commands;

import main.utils.CollectionManager;
import main.utils.Command;

/**
 * Команда show: выводит все элементы коллекции.
 */
public class ShowCommand implements Command {
    private final main.utils.CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "show : вывести все элементы коллекции в строковом представлении";
    }

    @Override
    public void execute(String[] args) {
        collectionManager.show();
    }
}