package src.commands;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

import src.StaticReferences;

public class CdCommand extends Command {
  public CdCommand(List<String> commandParts) {
    super(commandParts);
  }

  @Override
  public boolean validate() {
    var commandParts = getCommandParts();
    return commandParts.size() >= 1 && commandParts.get(0).equals("cd");
  }

  @Override
  public void execute() {
    var commandParts = getCommandParts();
    String path = commandParts.size() >= 2 ? commandParts.get(1) : "~";
    String homeDirectory = System.getenv("HOME");
    String workingDirectory = Paths.get("").toAbsolutePath().toString();
    String root = path.startsWith("~") ? homeDirectory : workingDirectory;
    Deque<String> stack = new ArrayDeque<>();
    StringTokenizer stringTokenizer = new StringTokenizer(root, "/");
    while (stringTokenizer.hasMoreTokens()) {
      stack.addLast(stringTokenizer.nextToken());
    }
    StringTokenizer pathTokenizer = new StringTokenizer(path, "/");
    while (pathTokenizer.hasMoreTokens()) {
      String token = pathTokenizer.nextToken();
      if (stack.equals("."))
        continue;
      if (token.equals("..") && !stack.isEmpty()) {
        stack.pollLast();
        continue;
      }
      stack.addLast(token);
    }
    String absolutePath = String.join("/", stack);
    File file = new File(absolutePath);
    if (!file.exists()) {
      System.out.println("cd: no such file or directory: " + absolutePath);
    } else {
      StaticReferences.cwd = absolutePath;
    }
  }

}
