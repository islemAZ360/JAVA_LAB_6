package server.commands;

import common.Request;
import common.Response;

public class HelpCommand implements Command {

    @Override
    public Response execute(Request request) {
        return new Response(
                "help : вывести справку по доступным командам\n" +
                        "show : вывести все элементы коллекции",
                true,
                null
        );
    }
}