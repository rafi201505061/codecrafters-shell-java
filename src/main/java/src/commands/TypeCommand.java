package src.commands;

import java.io.File;
import java.util.List;
import java.util.StringTokenizer;

public class TypeCommand extends Command {
  public TypeCommand(List<String> commandParts) {
    super(commandParts);
  }

  @Override
  public boolean validate() {
    return commandParts.size() == 2 && commandParts.get(0).equals("type");
  }

  private boolean searchPath() {
    String pathVariable = System.getenv("PATH");
    StringTokenizer stringTokenizer = new StringTokenizer(pathVariable, ":");
    while (stringTokenizer.hasMoreTokens()) {
      File dir = new File(stringTokenizer.nextToken());
      if (dir.isDirectory()) {
        for (File file : dir.listFiles()) {
          if (file.isFile() && file.canExecute() && file.getName().equals(commandParts.get(1))) {
            System.out.println(file.getName() + " is " + file.getAbsolutePath());
            return true;
          }
        }
      }
    }
    return false;
  }

  private void searchSupportedCommands() {
    try {
      CommandFactory factory = new ShellCommandFactory();
      Command command = factory.getCommand(commandParts.get(1));
      System.out.println(command.name + " is a shell builtin");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void execute() {
    if (!searchPath()) {
      searchSupportedCommands();
    }
  }

}
