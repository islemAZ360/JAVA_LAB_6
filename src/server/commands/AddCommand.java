package server.commands;

import common.Request;
import common.Response;
import common.models.HumanBeing;
import main.utils.CollectionManager;

public class AddCommand implements Command {

    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {

        if (request.getObjectArgument() == null) {
            return new Response("Объект не передан", false, null);
        }

        try {
            HumanBeing human = (HumanBeing) request.getObjectArgument();

            boolean added = collectionManager.add(human);

            if (added) {
                return new Response(
                        "Элемент добавлен. ID: " + human.getId(),
                        true,
                        null
                );
            } else {
                return new Response("Не удалось добавить элемент", false, null);
            }

        } catch (Exception e) {
            return new Response("Ошибка при добавлении: " + e.getMessage(), false, null);
        }
    }
}