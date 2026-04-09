package main.utils.commands;

import main.utils.Command;
import main.utils.CommandManager;

/**
 * Команда help: выводит справку по доступным командам.
 */
public class HelpCommand implements Command {
    private final CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }

    @Override
    public void execute(String[] args) {
        commandManager.getCommands().values().forEach(cmd ->
                System.out.println(cmd.getDescription())
        );
    }
}
