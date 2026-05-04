package client;

import common.HumanBeingBuilder;
import common.Request;
import common.Response;
import common.StatusCode;
import common.models.*;
import common.utils.BooleanBuilder;
import common.utils.LongBuilder;

import java.io.IOException;
import java.util.Scanner;

public class InputManager {

    private final Scanner scanner;
    private final RequestSender reqSender;
    private final Coordinates coordinatesChecker;
    private final HumanBeingBuilder humanBeingBuilder;
    private final BooleanBuilder booleanBuilder;
    private final LongBuilder longBuilder;

    public InputManager(Scanner scanner, RequestSender reqSender) {
        this.scanner = scanner;
        this.reqSender = reqSender;
        this.coordinatesChecker = new Coordinates();
        this.booleanBuilder = new BooleanBuilder(scanner);
        this.humanBeingBuilder = new HumanBeingBuilder(this.scanner, this.coordinatesChecker, this.booleanBuilder);
        this.longBuilder = new LongBuilder(this.scanner);
    }

    public HumanBeing readHumanbeing() {
        return this.humanBeingBuilder.readHumanBeing();
    }

    public Boolean read_filter_greater_than_car_value() {
        return this.booleanBuilder.readBoolean("Введите значение фильтрации:");
    }

    public Long read_id_value() {
        return this.longBuilder.readLongId();
    }

    public Response handleCommand(String input) throws IOException {

        // 🔥 TÁCH COMMAND + ARG
        String[] parts = input.trim().split("\\s+", 2);
        String commandName = parts[0];
        String argument = parts.length > 1 ? parts[1] : null;

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

            case "add_if_max":
            case "add_if_min":
                Long id = this.read_id_value();

                req = new Request(commandName, id.toString(), null);
                Response resp = this.reqSender.sendRequest(req);

                if (resp.getStatusCode() == StatusCode.CONTINUE) {
                    System.out.println(resp.getMessage());

                    req = new Request(
                            commandName,
                            id.toString(),
                            this.readHumanbeing());
                } else {
                    return resp;
                }
                break;

            case "remove_by_id":
            case "remove_greater":
            case "filter_contains_name":
            case "filter_less_than_minutes_of_waiting":
                req = new Request(commandName, argument, null);
                break;
            case "update":
                req = new Request(commandName, argument, null);

                Response updateResp = this.reqSender.sendRequest(req);

                if (updateResp.getStatusCode() == StatusCode.CONTINUE) {
                    System.out.println(updateResp.getMessage());

                    req = new Request(
                            commandName,
                            argument,
                            this.readHumanbeing()
                    );
                } else {
                        return updateResp;
                }
                break;
            case "clear":
                req = new Request(commandName, null, null);

                Response clearResp = this.reqSender.sendRequest(req);

                if (clearResp.getStatusCode() == StatusCode.CONTINUE) {
                    System.out.println(clearResp.getMessage());

                    String answer = scanner.nextLine();

                    req = new Request(commandName, answer, null);
                } else {
                    return clearResp;
                }
                break;
            case "show":
            case "info":
            case "help":
                req = new Request(commandName, null, null);
                break;

            case "exit":
                System.out.println("Client stopped.");
                System.exit(0);
                return null;

            case "save":
                System.out.println("Команда save недоступна в клиенте.");
                return null;

            default:
                req = new Request(commandName, argument, null);
                break;
        }

        return this.reqSender.sendRequest(req);
    }
}