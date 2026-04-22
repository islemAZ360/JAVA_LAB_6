package server.commands;

import common.Request;
import common.Response;
import main.utils.CollectionManager;

public class ShowCommand implements Command {

    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        String result = collectionManager.show();
        return new Response(result, true, null);
    }
}