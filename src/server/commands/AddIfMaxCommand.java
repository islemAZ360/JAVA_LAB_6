package server.commands;

import common.*;
import common.models.HumanBeing;
import server.CollectionManager;

import java.util.Arrays;

/**
 * Команда добавляет новый элемент в коллекцию, только если его ID превышает
 * максимальный ID текущей коллекции.
 */

public class AddIfMaxCommand implements Command {

    private final CollectionManager collectionManager;

    public AddIfMaxCommand(CollectionManager collectionManager) { this.collectionManager = collectionManager;}

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getDescription() {
        return "add_if_max : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

    /**
     * Выполняет команду add_if_max
     * @param request аргументы команды: args[1] = ID нового элемента
     */
    @Override
    public Response execute(Request request) {
        if (request.getObjectArgument() == null) {
            return new Response("Объект не передан", StatusCode.BAD_REQUEST, null);
        }

        try {
            HumanBeing newHuman = (HumanBeing) request.getObjectArgument();

            if (collectionManager.isEmpty()) {
                collectionManager.add(newHuman);
                return new Response(
                        "Коллекция пуста. Элемент добавлен. ID: " + newHuman.getId(),
                        StatusCode.OK,
                        null
                );
            }

            Long maxId = collectionManager.getMaxId();

            if (newHuman.getId() > maxId) {
                collectionManager.add(newHuman);
                return new Response(
                        "Элемент добавлен, так как его ID больше максимального. ID: " + newHuman.getId(),
                        StatusCode.OK,
                        null
                );
            }

            return new Response(
                    "Элемент не добавлен: его ID не превышает максимальный ID в коллекции (" + maxId + ")",
                    StatusCode.ID_INVALID,
                    null
            );
        } catch (ClassCastException e) {
            return new Response("Ошибка: передан объект неверного типа", StatusCode.BAD_REQUEST, null);
        } catch (Exception e) {
            return new Response("Ошибка при add_if_max: " + e.getMessage(), StatusCode.SERVER_ERROR, null);
        }
    }
}
