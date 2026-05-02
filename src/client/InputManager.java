package client;
import common.HumanBeingBuilder;
import common.Request;
import common.Response;
import common.models.*;
import common.utils.BooleanBuilder;

import java.io.IOException;
import java.util.Scanner;

public class InputManager {

    private final Scanner scanner;
    private final RequestSender reqSender;
    private final Coordinates coordinatesChecker;
    private final HumanBeingBuilder humanBeingBuilder;
    private final BooleanBuilder booleanBuilder;

    public InputManager(Scanner scanner, RequestSender reqSender) {
        this.scanner = scanner;
        this.reqSender = reqSender;
        this.coordinatesChecker = new Coordinates();
        this.booleanBuilder = new BooleanBuilder (scanner);
        this.humanBeingBuilder = new HumanBeingBuilder(this.scanner, this.coordinatesChecker, this.booleanBuilder);
    }

    public HumanBeing readHumanbeing() {
        return this.humanBeingBuilder.readHumanBeing();
    }
    public HumanBeing readHumanbeing(String[] args) {
        return this.humanBeingBuilder.readHumanBeing(args);
    }

    public Boolean read_filter_greater_than_car_value() {
        return this.booleanBuilder.readBoolean("Введите значение фильтерации:");
    }

    public Response handleCommand(String commandName) throws IOException {
        Request req = null;

        switch (commandName) {
            case "add":
                req = new Request(
                    commandName,
                    null,
                    this.readHumanbeing());
                break;
            case "filter_greater_than_car":
                req = new Request(
                        commandName,
                        this.read_filter_greater_than_car_value().toString(),
                        null);
                break;
            case "show":
            case "info":
            case "help":
            case "save":
            default:
                req = new Request(
                    commandName,
                    null,
                    null);
                break;
        }
        return this.reqSender.sendRequest(req);
    }
}
