package br.com.involves.control.enumaration;

import br.com.involves.util.Messages;

public enum CommandEnum {
    COUNT("count *"),
    DISTINCT("count distinct"),
    FILTER("filter"),
    EXIT("exit"),
    ;

    private String command;

    CommandEnum(String command) {
        this.command = command;
    }

    public static CommandEnum getCommandEnum(String command) throws ClassNotFoundException {
        for (CommandEnum commandEnum: CommandEnum.values()) {
            if(command.contains(commandEnum.getCommand())) {
                return commandEnum;
            }
        }
        throw new ClassNotFoundException(Messages.getMessage(Messages.COMMAND_NOT_FOUND));
    }

    public String getCommand() {
        return command;
    }
}
