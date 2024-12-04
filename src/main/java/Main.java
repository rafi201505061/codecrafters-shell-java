import java.util.List;
import java.util.Scanner;

import src.command_parsers.CommandParser;
import src.command_parsers.SimpleShellCommandParser;
import src.commands.Command;
import src.commands.CommandFactory;
import src.commands.GlobalCommandFactory;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CommandFactory commandFactory = new GlobalCommandFactory();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            scanner.close();
        }));
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            CommandParser commandParser = new SimpleShellCommandParser();
            List<String> commandParts = commandParser.parse(input);
            Command command = commandFactory.getCommand(commandParts);
            if (command == null) {
                System.out.println(commandParts.get(0) + ": not found");
            } else {
                if (command.validate()) {
                    command.execute();
                } else {
                    System.out.println(commandParts.get(0) + ": invalid params");
                }
            }
        }
    }
}
