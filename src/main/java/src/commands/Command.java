package src.commands;

import java.util.List;

public abstract class Command {
  List<String> commandParts;

  public Command(List<String> commandParts) {
    this.commandParts = commandParts;
  }

  abstract public boolean validate();

  abstract public void execute();

}
