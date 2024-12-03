package src.commands;

import java.util.List;

public class EchoCommand extends Command {
  public EchoCommand(List<String> commandParts) {
    super(commandParts);
  }

  @Override
  public boolean validate() {
    return commandParts.size() > 1 && commandParts.get(0).equals("echo");
  }

  @Override
  public void execute() {
    System.out.println(String.join(" ", commandParts.subList(1, commandParts.size())));
  }
}
