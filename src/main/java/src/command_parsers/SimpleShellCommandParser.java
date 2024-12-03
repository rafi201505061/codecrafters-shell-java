package src.command_parsers;

import java.util.ArrayList;
import java.util.List;

public class SimpleShellCommandParser implements CommandParser {

  @Override
  public List<String> parse(String command) {
    List<String> commandParts = new ArrayList<>();
    char[] chars = new char[command.length()];
    int len = 0;
    boolean isQuoteEncountered = false, isSpaceEncountered = false;
    for (int i = 0; i < command.length(); i++) {
      char curr = command.charAt(i);
      if (curr == '\'' || curr == '"') {
        if (isQuoteEncountered) {
          commandParts.add(new String(chars, 0, len));
          len = 0;
        }
        isQuoteEncountered = !isQuoteEncountered;
        continue;
      }
      if (isQuoteEncountered) {
        chars[len++] = curr;
        continue;
      }
      if (curr == ' ') {
        if (isSpaceEncountered) {
          continue;
        }
        isSpaceEncountered = true;
        if (len > 0) {
          commandParts.add(new String(chars, 0, len));
        }
        len = 0;

      } else {
        isSpaceEncountered = false;
        chars[len++] = curr;
      }
    }
    if (len > 0) {
      commandParts.add(new String(chars, 0, len));
    }

    return commandParts;
  }

}
