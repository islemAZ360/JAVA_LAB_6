package common.models;

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
    public static int port = 1234;
    public static String host = "localhost";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";

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

    public static String cat = """

                                                  |\\__/,|   (`\\
                                                _.|o o  |_   ) )
                                              -(((---(((--------
          
            ___  ________  ___      ___ ________          ___       ________  ________          ________    \s
           |\\  \\|\\   __  \\|\\  \\    /  /|\\   __  \\        |\\  \\     |\\   __  \\|\\   __  \\        |\\   ____\\   \s
           \\ \\  \\ \\  \\|\\  \\ \\  \\  /  / | \\  \\|\\  \\       \\ \\  \\    \\ \\  \\|\\  \\ \\  \\|\\ /_       \\ \\  \\___|   \s
         __ \\ \\  \\ \\   __  \\ \\  \\/  / / \\ \\   __  \\       \\ \\  \\    \\ \\   __  \\ \\   __  \\       \\ \\  \\____  \s
        |\\  \\\\_\\  \\ \\  \\ \\  \\ \\    / /   \\ \\  \\ \\  \\       \\ \\  \\____\\ \\  \\ \\  \\ \\  \\|\\  \\       \\ \\  ___  \\\s
        \\ \\________\\ \\__\\ \\__\\ \\__/ /     \\ \\__\\ \\__\\       \\ \\_______\\ \\__\\ \\__\\ \\_______\\       \\ \\_______\\
         \\|________|\\|__|\\|__|\\|__|/       \\|__|\\|__|        \\|_______|\\|__|\\|__|\\|_______|        \\|_______|
        """;

    public static String java = """
                   █████   █████████   █████   █████   █████████      █████         █████████   ███████████      ████████\s
                  ░░███   ███░░░░░███ ░░███   ░░███   ███░░░░░███    ░░███         ███░░░░░███ ░░███░░░░░███    ███░░░░███
                   ░███  ░███    ░███  ░███    ░███  ░███    ░███     ░███        ░███    ░███  ░███    ░███   ░███   ░░░\s
                   ░███  ░███████████  ░███    ░███  ░███████████     ░███        ░███████████  ░██████████    ░█████████\s
                   ░███  ░███░░░░░███  ░░███   ███   ░███░░░░░███     ░███        ░███░░░░░███  ░███░░░░░███   ░███░░░░███
             ███   ░███  ░███    ░███   ░░░█████░    ░███    ░███     ░███      █ ░███    ░███  ░███    ░███   ░███   ░███
            ░░████████   █████   █████    ░░███      █████   █████    ███████████ █████   █████ ███████████    ░░████████\s
             ░░░░░░░░   ░░░░░   ░░░░░      ░░░      ░░░░░   ░░░░░    ░░░░░░░░░░░ ░░░░░   ░░░░░ ░░░░░░░░░░░      ░░░░░░░░ \s
        """;

    public static String bears = """
           _     _      _     _      _     _      _     _       _     _      _     _      _     _    6
                  (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)     (c).-.(c)    (c).-.(c)    (c).-.(c)   \s
                   / ._. \\      / ._. \\      / ._. \\      / ._. \\       / ._. \\      / ._. \\      / ._. \\    \s
                 __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__   __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  \s
                (_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._) (_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._) \s
                   || J ||      || A ||      || V ||      || A ||       || L ||      || A ||      || B ||    \s
                 _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._   _.' `-' '._  _.' `-' '._  _.' `-' '._  \s
                (.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.) (.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.) \s
                 `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'   `-'     `-'  `-'     `-'  `-'     `-'  \s
    """;
}
