package src.commands;

import java.util.List;

public abstract class Command {
  List<String> commandParts;
  String name;

  public Command(List<String> commandParts) {
    this.commandParts = commandParts;
    this.name = commandParts.size() > 0 ? commandParts.get(0) : "";
  }

  abstract public boolean validate();

  abstract public void execute();

}
