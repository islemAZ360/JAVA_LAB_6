package server.commands;

import common.Request;
import common.Response;
import common.models.HumanBeing;
import main.utils.CollectionManager;

import java.util.List;

public class FilterContainsNameCommand implements Command {

    private final CollectionManager collectionManager;

    public FilterContainsNameCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        if (request.getStringArgument() == null || request.getStringArgument().isBlank()) {
            return new Response("Подстрока имени не указана", false, null);
        }

        String nameSubstring = request.getStringArgument();

        List<HumanBeing> result = collectionManager.stream()
                .filter(human -> human.getName().toLowerCase().contains(nameSubstring.toLowerCase()))
                .toList();

        if (result.isEmpty()) {
            return new Response(
                    "Элементы с именем, содержащим \"" + nameSubstring + "\", не найдены",
                    false,
                    null
            );
        }

        return new Response(
                collectionManager.show(result),
                true,
                result
        );
    }
}