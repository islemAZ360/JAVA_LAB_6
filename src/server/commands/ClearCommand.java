package server.commands;

import common.Request;
import common.Response;
import main.utils.CollectionManager;

public class ClearCommand implements Command {

    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        if (collectionManager.isEmpty()) {
            return new Response("Коллекция уже пуста", false, null);
        }

        int size = collectionManager.size();
        collectionManager.clear();

        return new Response(
                "Коллекция очищена. Удалено элементов: " + size,
                true,
                null
        );
    }
}