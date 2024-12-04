package src.commands;

import java.util.List;

public class TypeCommand extends Command {
  public TypeCommand(List<String> commandParts) {
    super(commandParts);
  }

  @Override
  public boolean validate() {
    return commandParts.size() == 2 && commandParts.get(0).equals("type");
  }

  @Override
  public void execute() {
    try {
      CommandFactory factory = new ShellCommandFactory();
      Command command = factory.getCommand(commandParts.get(1));
      System.out.println(command.name + " \033[31mis a shell builtin\033[0m]");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
