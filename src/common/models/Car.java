package common.models;

import java.io.Serializable;


public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private LocalDateTime creationDate;
    private String name;

    private Boolean cool; //Поле не может быть null

    /**
     * Создаёт объект Car с проверкой на null
     * @param cool true/false - крутая ли машина
     */

    public Car(Boolean cool) {
        this.setCar(cool);
    }

    public boolean isCool() {
        return this.cool;
    }

    public void setCar(Boolean cool) {
        if (cool == null) throw new IllegalArgumentException("Cool can not be null!");
        this.cool = cool;
    }
}

