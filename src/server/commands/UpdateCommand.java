package server.commands;

import common.Request;
import common.Response;
import common.models.HumanBeing;
import main.utils.CollectionManager;

public class UpdateCommand implements Command {

    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        if (request.getStringArgument() == null || request.getStringArgument().isBlank()) {
            return new Response("ID не указан", false, null);
        }

        if (request.getObjectArgument() == null) {
            return new Response("Новый объект не передан", false, null);
        }

        try {
            long id = Long.parseLong(request.getStringArgument());
            HumanBeing newHuman = (HumanBeing) request.getObjectArgument();

            boolean updated = collectionManager.update(id, newHuman);

            if (updated) {
                return new Response("Элемент обновлен", true, null);
            } else {
                return new Response("Элемент с таким ID не найден", false, null);
            }
        } catch (NumberFormatException e) {
            return new Response("Ошибка: ID должен быть числом", false, null);
        } catch (ClassCastException e) {
            return new Response("Ошибка: передан объект неверного типа", false, null);
        } catch (Exception e) {
            return new Response("Ошибка при обновлении: " + e.getMessage(), false, null);
        }
    }
}