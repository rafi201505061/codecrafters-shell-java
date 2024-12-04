package src.commands;

import java.util.List;

import src.StaticReferences;

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
    System.out.println(StaticReferences.cwd);
  }

}
