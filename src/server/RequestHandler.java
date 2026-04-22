package server;

import common.Request;
import common.Response;
import server.commands.Command;

public class RequestHandler {

    private final CommandManager commandManager;

    public RequestHandler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public Response handle(Request request) {
        if (request == null) {
            return new Response("Запрос не может быть null", false, null);
        }

        String commandName = request.getCommandName();

        if (commandName == null || commandName.isBlank()) {
            return new Response("Имя команды не указано", false, null);
        }

        Command command = commandManager.getCommand(commandName);

        if (command == null) {
            return new Response("Команда не найдена: " + commandName, false, null);
        }

        try {
            return command.execute(request);
        } catch (Exception e) {
            return new Response("Ошибка при выполнении команды: " + e.getMessage(), false, null);
        }
    }
}