package common;

import java.io.Serializable;

public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    private String commandName;
    private Object commandArgument;


    public Request(String commandName, Object commandArgument) {
        this.commandName = commandName;
        this.commandArgument = commandArgument;
    }


    public String getCommandName() {
        return commandName;
    }

    public Object getCommandArgument() {
        return commandArgument;
    }
}