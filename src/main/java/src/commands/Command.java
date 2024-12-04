package src.commands;

import java.util.List;

public abstract class Command {
  @SuppressWarnings("unused")
  private List<String> commandParts;
  private String name;

  public Command(List<String> commandParts) {
    this.commandParts = commandParts;
    this.name = commandParts.size() > 0 ? commandParts.get(0) : "";
  }

  public String getName() {
    return name;
  }

  public List<String> getCommandParts() {
    return commandParts;
  }

  abstract public boolean validate();

  abstract public void execute();

}
