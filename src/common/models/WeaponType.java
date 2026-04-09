package common.models;

import java.io.Serializable;


public enum WeaponType implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private LocalDateTime creationDate;
    private String name;

    HAMMER,
    RIFLE,
    KNIFE,
    MACHINE_GUN;
}
