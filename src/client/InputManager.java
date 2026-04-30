package client;
import common.HumanBeingBuilder;
import common.Request;
import common.Response;
import common.models.*;

import java.io.IOException;
import java.util.Scanner;

public class InputManager {

    private final Scanner scanner;
    private final RequestSender reqSender;
    private final Coordinates coordinatesChecker;
    private final HumanBeingBuilder humanBeingBuilder;

    public InputManager(Scanner scanner, RequestSender reqSender) {
        this.scanner = scanner;
        this.reqSender = reqSender;
        this.coordinatesChecker = new Coordinates();
        this.humanBeingBuilder = new HumanBeingBuilder(this.scanner, this.coordinatesChecker);
    }

    public HumanBeing readHumanbeing() {
        return this.humanBeingBuilder.readHumanBeing();
    }
    public HumanBeing readHumanbeing(String[] args) {
        return this.humanBeingBuilder.readHumanBeing(args);
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
            case "show":
            case "info":
            case "help":
            case "save":
                req = new Request(
                    commandName,
                    null,
                    null);
                break;
        }

        return (Response) this.reqSender.sendRequest(req);
    }
}
