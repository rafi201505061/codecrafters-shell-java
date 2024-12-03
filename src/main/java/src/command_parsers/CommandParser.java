package src.command_parsers;

import java.util.List;

public interface CommandParser {
  public List<String> parse(String input);
}
