package server.commands;

import common.Request;
import common.Response;
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
    public Response execute(Request request) {
        try {
            humanBeingFileManager.saveAll(collectionManager);

            return new Response(
                    "Коллекция успешно сохранена в файл. Всего элементов: " + collectionManager.size(),
                    true,
                    null
            );
        } catch (Exception e) {
            return new Response("Ошибка при сохранении: " + e.getMessage(), false, null);
        }
    }
}