package src.commands;

import src.exceptions.InvalidCommandException;

public interface CommandFactory {
  public Command getCommand(String input) throws InvalidCommandException;
}
