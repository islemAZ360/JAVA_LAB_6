package server.commands;

import common.Request;
import common.Response;
import common.models.HumanBeing;
import main.utils.CollectionManager;

import java.util.List;

public class FilterGreaterThanCarCommand implements Command {

    private final CollectionManager collectionManager;

    public FilterGreaterThanCarCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        if (request.getStringArgument() == null || request.getStringArgument().isBlank()) {
            return new Response("Значение car.cool не указано", false, null);
        }

        try {
            boolean filterValue = Boolean.parseBoolean(request.getStringArgument());

            List<HumanBeing> result = collectionManager.stream()
                    .filter(human -> {
                        boolean carCool = human.getCar() != null && human.getCar().isCool();
                        return carCool == filterValue;
                    })
                    .toList();

            if (result.isEmpty()) {
                return new Response(
                        "Элементы с car.cool = " + filterValue + " не найдены",
                        false,
                        null
                );
            }

            return new Response(
                    collectionManager.show(result),
                    true,
                    result
            );
        } catch (Exception e) {
            return new Response("Ошибка при filter_greater_than_car: " + e.getMessage(), false, null);
        }
    }
}