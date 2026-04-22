package server.commands;

import common.Request;
import common.Response;
import main.utils.CollectionManager;

public class RemoveByIdCommand implements Command {

    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {

        if (request.getStringArgument() == null) {
            return new Response("ID не указан", false, null);
        }

        try {
            long id = Long.parseLong(request.getStringArgument().toString());

            boolean removed = collectionManager.removeById(id);

            if (removed) {
                return new Response("Элемент удален", true, null);
            } else {
                return new Response("Элемент не найден", false, null);
            }

        } catch (Exception e) {
            return new Response("Ошибка: ID должен быть числом", false, null);
        }
    }
}