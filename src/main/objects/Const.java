package main.objects;

/**
 * Содержит константы, используемые в приложении:
 * - граничные значения для полей объектов
 * - пути к файлам данных и скриптов
 * - заголовки CSV файлов
 * - названия методов для работы с объектами
 */
public final class Const {
    public static final int MAXVALUEX = 128;
    public static final int MINVALUEX = -162;
    public static final long MAXVALUEY = 440L;
    public static final double MINVALUEIMPACTSPEED = -432d;
    public static final String FILEPATH = "data/data.csv";
    public static final String SCRIPTFILEPATH = "scripts/scripts.csv";

//  relative
//  getId, getName, getCoordinates, getCreationDate, isRealHero, isHasToothpick, getImpactSpeed, getSoundtrackName, getMinutesOfWaiting, getWeaponType, getCar
//  public static String[] PREFIXES = {"get", "is"};
    public static String[] FILEHEADER = {
        "Id",
        "Name",
        "CoordinateX",
        "CoordinateY",
        "CreationDate",
        "IsRealHero",
        "IsHasToothpick",
        "ImpactSpeed",
        "SoundtrackName",
        "MinutesOfWaiting",
        "WeaponType",
        "CarCool"
    };

//    absolute
    public static String[] FILEHEADERMETHODS = {
        "getId",
        "getName",
        "getCoordinates",
        "getCreationDate",
        "isRealHero",
        "isHasToothpick",
        "getImpactSpeed",
        "getSoundtrackName",
        "getMinutesOfWaiting",
        "getWeaponType",
        "getCar"
    };
}
