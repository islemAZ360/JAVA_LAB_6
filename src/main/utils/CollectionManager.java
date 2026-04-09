package main.utils;

import main.objects.Const;
import main.objects.HumanBeing;

import java.util.*;
import java.util.function.Predicate;

/**
 * Управляет коллекцией HumanBeing с автоматической сортировкой по ID.
 * Расширяет TreeSet, хранит время создания коллекции.
 */
public class CollectionManager extends TreeSet<HumanBeing> {
    private final java.time.LocalDateTime creationTime = java.time.LocalDateTime.now();

    /**
     * Создаёт коллекцию с компаратором для сортировки HumanBeing по ID
     */
    public CollectionManager() {
        super(new Comparator<HumanBeing>() {
            @Override
            public int compare(HumanBeing o1, HumanBeing o2) {
                return Long.compare(o1.getId(), o2.getId());
            }

            @Override
            public boolean equals(Object obj) {
                return super.equals(obj);
            }
        });
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public boolean add(HumanBeing humanBeing) {
        return super.add(humanBeing);
    }

    @Override
    public boolean addAll(Collection<? extends HumanBeing> c) {
        return super.addAll(c);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return super.removeAll(c);
    }

    @Override
    public void clear() {
        super.clear();
    }

    public String getCollectionInfo() {
        return this.toString();
    }

    /**
     * Возвращает информацию о коллекции: тип, дата создания, размер
     */
    public String toString() {
        return "Информации о коллекции:" +
                "\n>> Тип: " + this.getClass().getGenericSuperclass().getTypeName() +
                "\n>> Дата инициализации: " + this.creationTime +
                "\n>> Количество элементов: " + this.size();
    }

    /**
     * Выводит все элементы коллекции в виде таблицы
     */
    public void show() {
//        System.out.printf("%-4s | %-20s | %-11s | %-11s | %-30s | %-10s | %-14s | %-11s | %14s | %16s | %10s | %7s\n", Const.FILEHEADER);
        show(this.stream().toList());
//        for (HumanBeing human: this) {
//            System.out.printf("%-4s | %-20s | %-11s | %-11s | %-30s | %-10s | %-14s | %-11s | %14s | %16s | %10s | %7s\n", HumanBeingReader.extractInfo(human).split(","));
//        }
    }

    public void show(List<HumanBeing> listHuman) {
        System.out.printf("%-4s | %-20s | %-11s | %-11s | %-30s | %-10s | %-14s | %-11s | %14s | %16s | %10s | %7s\n", Const.FILEHEADER);

        for (HumanBeing human: listHuman) {
            System.out.printf("%-4s | %-20s | %-11s | %-11s | %-30s | %-10s | %-14s | %-11s | %14s | %16s | %10s | %7s\n", HumanBeingReader.extractInfo(human).split(","));
        }
    }


    //  Используем Stream чтобы фильтровать первый элемент, который удовлетворяет условиям
//  База
    /**
     * Находит первый элемент, удовлетворяющий условию фильтра
     * @param filter условие для поиска
     * @return найденный HumanBeing или null
     */
    public HumanBeing getHumanBy(Predicate<HumanBeing> filter) {
        return this.stream()
                .filter(filter)
                .findFirst()
                .orElse(null); // Возврат null при ненайденном элементе
    }

    /**
     * Находит человека по ID
     */
    public HumanBeing getHumanById(long id) {
        return this.getHumanBy(human -> human.getId() == id);
    }

//    public HumanBeing getHumanByName(String name) {
//        Redundant and long variant
//       return this.getHumanBy(human -> human.getName().toLowerCase().equals(name.toLowerCase());
//        return this.getHumanBy(human -> human.getName().equalsIgnoreCase(name));
//    }

    /**
     * Удаляет человека по ID
     * @return true если удалён, false если не найден
     */
    public boolean removeById(long id) {
        HumanBeing human = getHumanById(id);
        if (human != null) {
            return this.remove(human);
        }
        return false;
    }

    /**
     * Обновляет данные человека с указанным ID (сохраняет старый ID)
     * @return true если обновлён, false если ID не найден
     */
    public boolean update(long id, HumanBeing newHuman) {
        HumanBeing oldHuman = getHumanById(id);
        if (oldHuman == null) {
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
        } catch (Exception e) {
            System.err.println("bug from update method in CollectionManager");
        }
        return true;
    }

    /**
     * Возвращает человека с максимальным ID
     */
    public HumanBeing getMax() {
        if (this.isEmpty()) return null;
        return this.stream()
                .max(Comparator.comparingLong(HumanBeing::getId))
                .orElse(null);
    }

    /**
     * Возвращает максимальный ID в коллекции
     */
    public Long getMaxId() {
        return getMax().getId();
    }

    /**
     * Возвращает человека с минимальным ID
     */
    public HumanBeing getMin() {
        if (this.isEmpty()) return null;
        return this.stream()
                .min(Comparator.comparingLong(HumanBeing::getId))
                .orElse(null);
    }

    /**
     * Удаляет всех людей с ID больше, чем у указанного элемента
     */
    public void removeGreater(HumanBeing element) {
        java.util.ArrayList<HumanBeing> toRemove = new java.util.ArrayList<>();

        for (HumanBeing human : this) {
            if (human.compareTo(element) > 0) {
                toRemove.add(human);
            }
        }

        this.removeAll(toRemove);
    }
}

