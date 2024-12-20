package src.commands;

import java.util.List;

public class ExitCommand extends Command {
  public ExitCommand(List<String> commandParts) {
    super(commandParts);
  }

  @Override
  public boolean validate() {
    try {
      var commandParts = getCommandParts();
      boolean isValid = commandParts.size() == 2 && commandParts.get(0).equals("exit");
      Integer.parseInt(commandParts.get(1));
      return isValid;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public void execute() {
    var commandParts = getCommandParts();
    System.exit(Integer.parseInt(commandParts.get(1)));
  }

}
