package server.commands;

import common.Request;
import common.Response;
import common.models.HumanBeing;
import main.utils.CollectionManager;

public class AddIfMaxCommand implements Command {

    private final CollectionManager collectionManager;

    public AddIfMaxCommand(CollectionManager collectionManager) {
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

            Long maxId = collectionManager.getMaxId();

            if (newHuman.getId() > maxId) {
                collectionManager.add(newHuman);
                return new Response(
                        "Элемент добавлен, так как его ID больше максимального. ID: " + newHuman.getId(),
                        true,
                        null
                );
            }

            return new Response(
                    "Элемент не добавлен: его ID не превышает максимальный ID в коллекции (" + maxId + ")",
                    false,
                    null
            );
        } catch (ClassCastException e) {
            return new Response("Ошибка: передан объект неверного типа", false, null);
        } catch (Exception e) {
            return new Response("Ошибка при add_if_max: " + e.getMessage(), false, null);
        }
    }
}