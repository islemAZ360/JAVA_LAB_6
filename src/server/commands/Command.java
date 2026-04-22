package server.commands;

import common.Request;
import common.Response;

public interface Command {
    Response execute(Request request);
}