package server.commands;

import common.Command;
import common.Request;
import common.Response;
import common.StatusCode;
import common.models.HumanBeing;
import server.CollectionManager;

public class UpdateCommand implements Command {

    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "update id {element} : обновить элемент по ID";
    }

    @Override
    public Response execute(Request request) {
        if (request.getStringArgument() == null || request.getStringArgument().isBlank()) {
            return new Response("ID не указан", StatusCode.BAD_REQUEST, null);
        }

        if (request.getObjectArgument() == null) {
            return new Response("Новый объект не передан\nспользование: update id", StatusCode.BAD_REQUEST, null);
        }

        try {
            long id = Long.parseLong(request.getStringArgument());
            HumanBeing newHuman = (HumanBeing) request.getObjectArgument();

            boolean updated = collectionManager.update(id, newHuman);

            if (updated) {
                return new Response("Элемент обновлен", StatusCode.OK, null);
            } else {
                return new Response("Элемент с таким ID не найден", StatusCode.ID_INVALID, null);
            }
        } catch (NumberFormatException e) {
            return new Response("Ошибка: ID должен быть числом", StatusCode.ID_INVALID, null);
        } catch (ClassCastException e) {
            return new Response("Ошибка: передан объект неверного типа", StatusCode.BAD_REQUEST, null);
        } catch (Exception e) {
            return new Response("Ошибка при обновлении: " + e.getMessage(), StatusCode.SERVER_ERROR, null);
        }
    }
}
