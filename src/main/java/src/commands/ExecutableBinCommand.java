package src.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ExecutableBinCommand extends Command {
  private String path = "";

  public ExecutableBinCommand(String path, List<String> commandParts) {
    super(commandParts);
    this.path = path;
  }

  public String getPath() {
    return path;
  }

  @Override
  public boolean validate() {
    File file = new File(path);
    return file.canExecute() && file.isFile();
  }

  @Override
  public void execute() {
    var commandParts = getCommandParts();
    try {
      ProcessBuilder processBuilder = new ProcessBuilder(commandParts);
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
      process.waitFor();
    } catch (IOException | InterruptedException e) {
      System.out.println(getName() + ": not found");
    }
  }

}
