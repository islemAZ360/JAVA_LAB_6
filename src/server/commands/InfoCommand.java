package server.commands;

import common.Request;
import common.Response;
import main.utils.CollectionManager;

public class InfoCommand implements Command {

    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        return new Response(
                collectionManager.getCollectionInfo(),
                true,
                null
        );
    }
}