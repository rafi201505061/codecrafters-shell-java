package src.commands;

import java.nio.file.Paths;
import java.util.List;

public class PwdCommand extends Command {
  public PwdCommand(List<String> commandParts) {
    super(commandParts);
  }

  @Override
  public boolean validate() {
    var commandParts = getCommandParts();
    return commandParts.size() == 1 && commandParts.get(0).equals("pwd");
  }

  @Override
  public void execute() {
    System.out.println(Paths.get("").toAbsolutePath());
  }

}
