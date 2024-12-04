package src.commands;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class ExecutableBinFactory implements CommandFactory {
  private final Map<String, String> commandToPathMap;

  public ExecutableBinFactory() {
    commandToPathMap = new HashMap<>();
    String pathVariable = System.getenv("PATH");
    StringTokenizer stringTokenizer = new StringTokenizer(pathVariable, ":");
    while (stringTokenizer.hasMoreTokens()) {
      File dir = new File(stringTokenizer.nextToken());
      if (dir.isDirectory()) {
        for (File file : dir.listFiles()) {
          if (file.isFile() && file.canExecute() && !commandToPathMap.containsKey(file.getName())) {
            commandToPathMap.put(file.getName(), file.getAbsolutePath());
          }
        }
      }
    }
  }

  @Override
  public Command getCommand(List<String> commandParts) {

    String commandName = commandParts.get(0);
    if (commandToPathMap.containsKey(commandName)) {
      return new ExecutableBinCommand(commandToPathMap.get(commandName), commandParts);
    } else {
      return null;
    }
  }

}
