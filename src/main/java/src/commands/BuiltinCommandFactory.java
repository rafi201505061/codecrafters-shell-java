package src.commands;

import java.util.List;

public class BuiltinCommandFactory implements CommandFactory {

  @Override
  public Command getCommand(List<String> commandParts) {

    switch (commandParts.get(0)) {
      case "exit":
        return new ExitCommand(commandParts);
      case "echo":
        return new EchoCommand(commandParts);
      case "type":
        return new TypeCommand(commandParts);
      case "pwd":
        return new PwdCommand(commandParts);
      default:
        return null;
    }
  }

}
