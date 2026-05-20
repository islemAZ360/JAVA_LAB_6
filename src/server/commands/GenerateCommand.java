package server.commands;

import common.Command;
import common.Request;
import common.Response;
import common.StatusCode;
import common.models.Car;
import common.models.Coordinates;
import common.models.HumanBeing;
import common.models.WeaponType;
import server.CollectionManager;

import java.util.Random;

public class GenerateCommand implements Command {

    private final CollectionManager collectionManager;
    private final Random random = new Random(); // Генератор случайных чисел

    public GenerateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "generate";
    }

    @Override
    public String getDescription() {
        return "generate {count} : создать и добавить N случайных элементов в коллекцию";
    }

    @Override
    public Response execute(Request request) {
        if (request.getStringArgument() == null || request.getStringArgument().isBlank()) {
            return new Response("Ошибка: Укажите количество элементов (например: generate 5)", StatusCode.BAD_REQUEST, null);
        }

        try {
            int count = Integer.parseInt(request.getStringArgument());

            if (count <= 0) {
                return new Response("Количество должно быть больше 0", StatusCode.BAD_REQUEST, null);
            }

            // Генерация случайных объектов
            for (int i = 0; i < count; i++) {
                // Генерация данных с учетом ограничений вашего проекта
                String name = "RandomHuman_" + random.nextInt(10000);
                int x = random.nextInt(200) - 160; // Больше чем -162
                long y = random.nextInt(440);      // Меньше чем 440
                Coordinates coords = new Coordinates(x, y);

                boolean realHero = random.nextBoolean();
                boolean hasToothpick = random.nextBoolean();
                double impactSpeed = random.nextDouble() * 1000 - 400; // Больше чем -432
                String soundtrack = "Track_" + random.nextInt(100);
                int minutesOfWaiting = random.nextInt(1000);

                WeaponType[] weapons = WeaponType.values();
                WeaponType weapon = weapons[random.nextInt(weapons.length)];

                Car car = new Car(random.nextBoolean());

                // Создание объекта и добавление его в коллекцию
                HumanBeing randomHuman = new HumanBeing(
                        name, coords, realHero, hasToothpick, impactSpeed,
                        soundtrack, minutesOfWaiting, weapon, car
                );

                collectionManager.add(randomHuman);
            }

            return new Response(
                    "Успешно сгенерировано и добавлено " + count + " элементов.",
                    StatusCode.OK,
                    null
            );

        } catch (NumberFormatException e) {
            return new Response("Ошибка: Количество должно быть целым числом.", StatusCode.FORMAT_INVALID, null);
        } catch (Exception e) {
            return new Response("Ошибка при генерации: " + e.getMessage(), StatusCode.SERVER_ERROR, null);
        }
    }
}