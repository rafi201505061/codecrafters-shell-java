import java.util.Scanner;

import src.commands.Command;
import src.commands.CommandFactory;
import src.commands.ShellCommandFactory;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandFactory commandFactory = new ShellCommandFactory();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            scanner.close();
        }));
        while (true) {
            try {
                System.out.print("$ ");
                String input = scanner.nextLine();
                Command command = commandFactory.getCommand(input);
                if (command.validate()) {
                    command.execute();
                } else {
                    System.out.println("Invalid Command");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }
}
