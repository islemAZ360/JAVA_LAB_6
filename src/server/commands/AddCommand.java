package server.commands;

import common.Command;
import common.Request;
import common.Response;
import common.models.HumanBeing;
import server.CollectionManager;
import client.InputManager;

import java.util.Arrays;

/**
 * Команда add {element}: добавляет новый элемент в коллекцию.
 * Элемент читается с помощью ElementReader.
 * Lưu ý: Command này chỉ thêm vào collection, không tự động save vào file.
 * User phải gọi save riêng để lưu.
 */
public class AddCommand implements Command {
    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "add {element} : добавить новый элемент в коллекцию (не сохраняет в файл)";
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