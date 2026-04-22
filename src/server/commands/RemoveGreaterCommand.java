package server.commands;

import common.Request;
import common.Response;
import common.models.HumanBeing;
import main.utils.CollectionManager;

public class RemoveGreaterCommand implements Command {

    private final CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        if (request.getStringArgument() == null || request.getStringArgument().isBlank()) {
            return new Response("ID не указан", false, null);
        }

        try {
            long id = Long.parseLong(request.getStringArgument());
            HumanBeing element = collectionManager.getHumanById(id);

            if (element == null) {
                return new Response("Элемент с ID " + id + " не найден", false, null);
            }

            int oldSize = collectionManager.size();
            collectionManager.removeGreater(element);
            int removedCount = oldSize - collectionManager.size();

            if (removedCount > 0) {
                return new Response(
                        "Удалено элементов с ID больше " + id + ": " + removedCount,
                        true,
                        null
                );
            } else {
                return new Response("Нет элементов с ID больше " + id, false, null);
            }
        } catch (NumberFormatException e) {
            return new Response("Ошибка: ID должен быть числом", false, null);
        } catch (Exception e) {
            return new Response("Ошибка при remove_greater: " + e.getMessage(), false, null);
        }
    }
}