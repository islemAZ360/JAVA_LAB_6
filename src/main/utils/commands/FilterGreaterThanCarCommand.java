package main.utils.commands;

import main.objects.Car;
import main.objects.HumanBeing;
import main.utils.CollectionManager;
import main.utils.Command;

import java.util.ArrayList;
import java.util.List;

public class FilterGreaterThanCarCommand implements Command {

    private final CollectionManager collectionManager;

    public FilterGreaterThanCarCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "filter_greater_than_car";
    }

    @Override
    public String getDescription() {
        return "filter_greater_than_car [true/false] : вывести элементы, значение car.cool которых больше заданного";
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Использование: filter_greater_than_car [true/false]");
            System.out.println("Пример: filter_greater_than_car true");
            return;
        }

        boolean filterValue = Boolean.parseBoolean(args[1]);
        boolean found = false;
        List<HumanBeing> humanList = new ArrayList<>();

        for (HumanBeing human : this.collectionManager) {
            Car car = human.getCar();
            boolean carCool = (car != null) ? car.isCool() : false;

            // So sánh: true hay false
            if (carCool == filterValue) { // true > false
//                printHuman(human);
                humanList.add(human);
                found = true;
            }
        }

        if (found) {
            printHuman(humanList);
        } else {
            System.out.println("Элементы с car.cool is " + filterValue + " не найдены.");
        }
    }


    private void printHuman(List<HumanBeing> humanList) {
            this.collectionManager.show(humanList);
//        System.out.printf("%-4d | %-20s | %-12d | %-12d | %-30s | %-10b | %-14b | %-11.2f | %-14s | %-16d | %-10s | %-7b%n",
//                human.getId(),
//                human.getName(),
//                human.getCoordinates().getX(),
//                human.getCoordinates().getY(),
//                human.getCreationDate(),
//                human.isRealHero(),
//                human.isHasToothpick(),
//                human.getImpactSpeed(),
//                human.getSoundtrackName(),
//                human.getMinutesOfWaiting(),
//                human.getWeaponType() != null ? human.getWeaponType() : "",
//                human.getCar() != null ? human.getCar().isCool() : false
//        );
    }

}