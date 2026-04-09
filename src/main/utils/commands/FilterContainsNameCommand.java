package main.utils.commands;

import main.objects.HumanBeing;
import main.utils.CollectionManager;
import main.utils.Command;

import java.util.ArrayList;
import java.util.List;

public class FilterContainsNameCommand implements Command {

    private final CollectionManager collectionManager;

    public FilterContainsNameCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "filter_contains_name";
    }

    @Override
    public String getDescription() {
        return "filter_contains_name name : вывести элементы, имя которых содержит подстроку";
    }
    @Override
    public void execute(String[] args) {
        if (args.length < 2) { // Vì args[0] là tên command
            System.out.println("Использование: filter_contains_name name");
            System.out.println("Пример: filter_contains_name John");
            return;
        }

        String nameSubstring = args[1]; // args[1] mới là tên cần tìm
        boolean found = false;
        List<HumanBeing> listHuman = new ArrayList<>();

        for (HumanBeing human : this.collectionManager) {
            if (human.getName().toLowerCase().contains(nameSubstring.toLowerCase())) {
                listHuman.add(human);
                // Sử dụng phương thức show format giống trong CollectionManager
//                System.out.printf("%-4d | %-20s | %-11d | %-11d | %-30s | %-10b | %-14b | %-11.2f | %-14s | %-16d | %-10s | %-7b%n",
//                        human.getId(),
//                        human.getName(),
//                        human.getCoordinates().getX(),
//                        human.getCoordinates().getY(),
//                        human.getCreationDate(),
//                        human.isRealHero(),
//                        human.isHasToothpick(),
//                        human.getImpactSpeed(),
//                        human.getSoundtrackName(),
//                        human.getMinutesOfWaiting(),
//                        human.getWeaponType() != null ? human.getWeaponType() : "",
//                        human.getCar() != null ? human.getCar().isCool() : false
//                );
                found = true;
            }
        }
        if (found) {
            this.collectionManager.show(listHuman);
        } else {
            System.out.println("Элементы с именем, содержащим \"" + nameSubstring + "\", не найдены.");
        }
    }
}