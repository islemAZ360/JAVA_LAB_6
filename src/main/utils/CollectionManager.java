package main.utils;

import common.models.HumanBeing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Predicate;

public class CollectionManager extends TreeSet<HumanBeing> {

    private final LocalDateTime creationTime = LocalDateTime.now();

    public CollectionManager() {
        super(Comparator.comparingLong(HumanBeing::getId));
    }

    public String getCollectionInfo() {
        return this.toString();
    }

    @Override
    public String toString() {
        return "Информация о коллекции:" +
                "\n>> Тип: " + this.getClass().getName() +
                "\n>> Дата инициализации: " + creationTime +
                "\n>> Количество элементов: " + size();
    }

    public String show() {
        return show(new ArrayList<>(this));
    }

    public String show(List<HumanBeing> listHuman) {
        if (listHuman == null || listHuman.isEmpty()) {
            return "Коллекция пуста";
        }

        StringBuilder sb = new StringBuilder();

        for (HumanBeing human : listHuman) {
            sb.append(human).append("\n");
        }

        return sb.toString().trim();
    }

    public HumanBeing getHumanBy(Predicate<HumanBeing> filter) {
        return this.stream()
                .filter(filter)
                .findFirst()
                .orElse(null);
    }

    public HumanBeing getHumanById(long id) {
        return getHumanBy(human -> human.getId() == id);
    }

    public boolean removeById(long id) {
        HumanBeing human = getHumanById(id);
        if (human != null) {
            return remove(human);
        }
        return false;
    }

    public boolean update(long id, HumanBeing newHuman) {
        HumanBeing oldHuman = getHumanById(id);
        if (oldHuman == null || newHuman == null) {
            return false;
        }

        try {
            oldHuman.setName(newHuman.getName());
            oldHuman.setCoordinates(newHuman.getCoordinates());
            oldHuman.setRealHero(newHuman.isRealHero());
            oldHuman.setHasToothpick(newHuman.isHasToothpick());
            oldHuman.setImpactSpeed(newHuman.getImpactSpeed());
            oldHuman.setSoundtrackName(newHuman.getSoundtrackName());
            oldHuman.setMinutesOfWaiting(newHuman.getMinutesOfWaiting());
            oldHuman.setWeaponType(newHuman.getWeaponType());
            oldHuman.setCar(newHuman.getCar());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public HumanBeing getMax() {
        if (isEmpty()) {
            return null;
        }

        return this.stream()
                .max(Comparator.comparingLong(HumanBeing::getId))
                .orElse(null);
    }

    public Long getMaxId() {
        HumanBeing max = getMax();
        return max == null ? null : max.getId();
    }

    public HumanBeing getMin() {
        if (isEmpty()) {
            return null;
        }

        return this.stream()
                .min(Comparator.comparingLong(HumanBeing::getId))
                .orElse(null);
    }

    public void removeGreater(HumanBeing element) {
        if (element == null) {
            return;
        }

        this.removeIf(human -> human.compareTo(element) > 0);
    }
}