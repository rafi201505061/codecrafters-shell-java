import java.util.List;
import java.util.Scanner;

import src.command_parsers.CommandParser;
import src.command_parsers.SimpleShellCommandParser;
import src.commands.Command;
import src.commands.CommandBuilder;
import src.commands.CommandFactory;
import src.commands.GlobalCommandFactory;
import src.commands.ShellCommandBuilder;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            scanner.close();
        }));

        CommandFactory commandFactory = new GlobalCommandFactory();
        CommandParser commandParser = new SimpleShellCommandParser();
        CommandBuilder commandBuilder = new ShellCommandBuilder(scanner);
        while (true) {
            String input = commandBuilder.build();
            List<String> commandParts = commandParser.parse(input);
            Command command = commandFactory.getCommand(commandParts);
            if (command == null) {
                System.out.println(commandParts.get(0) + ": not found");
            } else {
                if (command.validate()) {
                    command.execute();
                } else {
                    System.out.println(command.getName() + ": invalid params");
                }
            }
        }
    }
}
