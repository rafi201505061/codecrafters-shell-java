package src.commands;

import java.util.List;

public interface CommandFactory {
  public Command getCommand(List<String> commandParts);
}
