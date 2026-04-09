package main.utils.commands;

import main.objects.Const;
import main.objects.HumanBeing;
import main.utils.CollectionManager;
import main.utils.Command;
import main.utils.HumanBeingReader;

import java.util.ArrayList;
import java.util.List;

public class FilterLessThanMinutesOfWaitingCommand implements Command {

    private final CollectionManager collectionManager;

    public FilterLessThanMinutesOfWaitingCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "filter_less_than_minutes_of_waiting";
    }

    @Override
    public String getDescription() {
        return "filter_less_than_minutes_of_waiting minutes : фильтр по minutesOfWaiting";
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Использование: filter_less_than_minutes_of_waiting minutes");
            System.out.println("Пример: filter_less_than_minutes_of_waiting 30");
            return;
        }

        try {
            int minutes = Integer.parseInt(args[1]);
            boolean found = false;
            List<HumanBeing> humanList = new ArrayList<>();
//            System.out.printf("%-4s | %-20s | %-12s | %-12s | %-30s | %-10s | %-14s | %-11s | %14s | %16s | %10s | %7s\n", Const.FILEHEADER);
            for (HumanBeing human : this.collectionManager) {
                if (human.getMinutesOfWaiting() < minutes) {
                    humanList.add(human);
                    // Sử dụng phương thức in format
//                    System.out.printf("%-4d | %-20s | %-12d | %-12d | %-30s | %-10b | %-14b | %-11.2f | %-14s | %-16d | %-10s | %-7b%n",
//                            human.getId(),
//                            human.getName(),
//                            human.getCoordinates().getX(),
//                            human.getCoordinates().getY(),
//                            human.getCreationDate(),
//                            human.isRealHero(),
//                            human.isHasToothpick(),
//                            human.getImpactSpeed(),
//                            human.getSoundtrackName(),
//                            human.getMinutesOfWaiting(),
//                            human.getWeaponType() != null ? human.getWeaponType() : "",
//                            human.getCar() != null ? human.getCar().isCool() : false
//                    );
//                    System.out.printf("%-4s | %-20s | %-12s | %-12s | %-30s | %-10s | %-14s | %-11s | %14s | %16s | %10s | %7s\n", HumanBeingReader.extractInfo(human).split(","));
                    found = true;
                }
            }

            if (found) {
                this.collectionManager.show(humanList);
            } else {
                System.out.println("Элементы с minutesOfWaiting < " + minutes + " не найдены.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: minutes должно быть целым числом.");
        }
    }
}