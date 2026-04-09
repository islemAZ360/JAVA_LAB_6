package main.utils.commands;

import main.utils.Command;
import main.utils.FileManager;
import main.utils.CollectionManager;
import main.utils.HumanBeingFileManager;

public class SaveCommand implements Command {

    private final CollectionManager collectionManager;
    private final HumanBeingFileManager humanBeingFileManager;

    public SaveCommand(CollectionManager collectionManager, HumanBeingFileManager humanBeingFileManager) {
        this.collectionManager = collectionManager;
        this.humanBeingFileManager = humanBeingFileManager;
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "save : сохранить коллекцию в файл";
    }

    @Override
    public void execute(String[] args) {
        if (collectionManager.isEmpty()) {
            System.out.println("Коллекция пуста. Файл будет очищен.");
        }

        humanBeingFileManager.saveAll(collectionManager);
    }
}