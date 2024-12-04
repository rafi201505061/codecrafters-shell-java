package src.commands;

import java.util.Arrays;
import java.util.List;

public class TypeCommand extends Command {
  public TypeCommand(List<String> commandParts) {
    super(commandParts);
  }

  @Override
  public boolean validate() {
    var commandParts = getCommandParts();
    return commandParts.size() == 2 && commandParts.get(0).equals("type");
  }

  @Override
  public void execute() {
    var commandParts = getCommandParts();
    CommandFactory factory = new BuiltinCommandFactory();
    String[] commandName = { commandParts.get(1) };
    Command command = factory.getCommand(Arrays.asList(commandName));
    if (command == null) {
      System.out.println(commandParts.get(0) + ": not found");
      return;
    }
    if (command instanceof ExecutableBinCommand) {
      System.out.println(command.getName() + " is " + ((ExecutableBinCommand) command).getPath());
    } else {
      System.out.println(command.getName() + " is a shell builtin");
    }
  }

}
