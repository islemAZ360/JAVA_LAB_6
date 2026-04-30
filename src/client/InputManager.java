package client;
import common.HumanBeingBuilder;
import common.models.*;

import java.util.Scanner;

public class InputManager {

    private final Scanner scanner;
    private final Coordinates coordinatesChecker;
    private final HumanBeingBuilder humanBeingBuilder;

    public InputManager(Scanner scanner) {
        this.scanner = scanner;
        this.coordinatesChecker = new Coordinates();
        this.humanBeingBuilder = new HumanBeingBuilder(this.scanner, this.coordinatesChecker);
    }

    public HumanBeing readHumanbeing() {
        return this.humanBeingBuilder.readHumanBeing();
    }
    public HumanBeing readHumanbeing(String[] args) {
        return this.humanBeingBuilder.readHumanBeing(args);
    }
}
