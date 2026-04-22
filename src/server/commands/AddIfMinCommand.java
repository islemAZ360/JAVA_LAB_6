package server.commands;

import common.Request;
import common.Response;
import common.models.HumanBeing;
import main.utils.CollectionManager;

public class AddIfMinCommand implements Command {

    private final CollectionManager collectionManager;

    public AddIfMinCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        if (request.getObjectArgument() == null) {
            return new Response("Объект не передан", false, null);
        }

        try {
            HumanBeing newHuman = (HumanBeing) request.getObjectArgument();

            if (collectionManager.isEmpty()) {
                collectionManager.add(newHuman);
                return new Response(
                        "Коллекция пуста. Элемент добавлен. ID: " + newHuman.getId(),
                        true,
                        null
                );
            }

            long minId = collectionManager.getMin().getId();

            if (newHuman.getId() < minId) {
                collectionManager.add(newHuman);
                return new Response(
                        "Элемент добавлен, так как его ID меньше минимального. ID: " + newHuman.getId(),
                        true,
                        null
                );
            }

            return new Response(
                    "Элемент не добавлен: его ID не меньше минимального ID в коллекции (" + minId + ")",
                    false,
                    null
            );
        } catch (ClassCastException e) {
            return new Response("Ошибка: передан объект неверного типа", false, null);
        } catch (Exception e) {
            return new Response("Ошибка при add_if_min: " + e.getMessage(), false, null);
        }
    }
}