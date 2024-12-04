package src.commands;

import java.util.List;

public class GlobalCommandFactory implements CommandFactory {

  @Override
  public Command getCommand(List<String> commandParts) {
    CommandFactory builtInCommandFactory = new BuiltinCommandFactory();
    CommandFactory executableBinFactory = new ExecutableBinFactory();
    Command command = builtInCommandFactory.getCommand(commandParts);
    if (command == null) {
      command = executableBinFactory.getCommand(commandParts);
    }
    return command;
  }

}
