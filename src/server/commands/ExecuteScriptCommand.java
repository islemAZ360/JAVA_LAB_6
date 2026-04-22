package server.commands;

import common.Request;
import common.Response;

public class ExecuteScriptCommand implements Command {

    @Override
    public Response execute(Request request) {
        return new Response(
                "Команда execute_script пока не реализована для client-server версии",
                false,
                null
        );
    }
}