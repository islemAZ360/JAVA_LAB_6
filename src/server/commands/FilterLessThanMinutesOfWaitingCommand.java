package server.commands;

import common.Request;
import common.Response;
import common.models.HumanBeing;
import main.utils.CollectionManager;

import java.util.List;

public class FilterLessThanMinutesOfWaitingCommand implements Command {

    private final CollectionManager collectionManager;

    public FilterLessThanMinutesOfWaitingCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        if (request.getStringArgument() == null || request.getStringArgument().isBlank()) {
            return new Response("minutesOfWaiting не указан", false, null);
        }

        try {
            int minutes = Integer.parseInt(request.getStringArgument());

            List<HumanBeing> result = collectionManager.stream()
                    .filter(human -> human.getMinutesOfWaiting() < minutes)
                    .toList();

            if (result.isEmpty()) {
                return new Response(
                        "Элементы с minutesOfWaiting < " + minutes + " не найдены",
                        false,
                        null
                );
            }

            return new Response(
                    collectionManager.show(result),
                    true,
                    result
            );
        } catch (NumberFormatException e) {
            return new Response("Ошибка: minutes должно быть целым числом", false, null);
        }
    }
}