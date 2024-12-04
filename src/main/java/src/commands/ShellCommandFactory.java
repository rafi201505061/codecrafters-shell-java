package src.commands;

import java.util.List;

import src.command_parsers.CommandParser;
import src.command_parsers.SimpleShellCommandParser;
import src.exceptions.InvalidCommandException;

public class ShellCommandFactory implements CommandFactory {

  @Override
  public Command getCommand(String input) throws InvalidCommandException {
    CommandParser commandParser = new SimpleShellCommandParser();
    List<String> commandParts = commandParser.parse(input);
    switch (commandParts.get(0)) {
      case "exit":
        return new ExitCommand(commandParts);
      case "echo":
        return new EchoCommand(commandParts);
      case "type":
        return new TypeCommand(commandParts);
      default:
        throw new InvalidCommandException(commandParts.get(0) + ": not found");
    }
  }

}
