package br.com.involves.view;

import br.com.involves.control.CommandControl;
import br.com.involves.control.enumaration.CommandEnum;
import br.com.involves.util.Messages;

import java.util.Scanner;

public class Main {

    private static CommandControl commandControl;
    private static Scanner scanner;

    public static void main(String[] args) {
        commandControl = new CommandControl();
        scanner = new Scanner(System.in);

        loadFile();
        executeCommands();

        System.out.println(Messages.getMessage(Messages.THANK_YOU));
    }

    private static void executeCommands() {
        CommandEnum commandEnum = CommandEnum.COUNT;
        while (!commandEnum.equals(CommandEnum.EXIT)) {
            System.out.println(Messages.getMessage(Messages.COMMANDS_AVAILABLE));
            String command = trimCommand(scanner.nextLine());
            try {
                commandEnum = CommandEnum.getCommandEnum(command);
                String[] parameters = command.replace(commandEnum.getCommand(), "").trim().split(" ");
                commandControl.executeCommand(commandEnum, parameters);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void loadFile() {
        boolean listLoaded = false;

        while (!listLoaded) {
            try {
                System.out.println(Messages.getMessage(Messages.TYPE_FILE_PATH));
                String path = scanner.nextLine();
                commandControl.loadFile(path);
                listLoaded = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String trimCommand(String command) {
        String[] splitCommand = command.split(" ");
        String result = "";
        for (String splitedCommand : splitCommand) {
            if (!splitedCommand.isEmpty()) {
                result += splitedCommand.trim();
                result += " ";
            }
        }

        return result.trim();
    }
}
