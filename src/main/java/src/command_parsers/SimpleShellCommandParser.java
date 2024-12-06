package src.command_parsers;

import java.util.ArrayList;
import java.util.List;

public class SimpleShellCommandParser implements CommandParser {

  @Override
  public List<String> parse(String command) {
    List<String> commandParts = new ArrayList<>();
    char[] chars = new char[command.length()];
    int len = 0;
    int i = 0;
    while (i < command.length()) {
      char curr = command.charAt(i);
      if (curr == '\'' || curr == '"') {
        int j = i + 1;
        while (j < command.length() && command.charAt(j) != curr) {
          if (curr == '"' && command.charAt(j) == '\\' && (j + 1) < command.length() && (command.charAt(j + 1) == '$'
              || command.charAt(j + 1) == '\\' || command.charAt(j + 1) == '"' || command.charAt(j + 1) == '\n')) {
            if (command.charAt(j + 1) != '\n') {
              chars[len++] = command.charAt(j + 1);
            }
            j += 2;

          } else if (curr == '"' && command.charAt(j) == '\\' && (j + 2) < command.length()
              && command.charAt(j + 1) == '\\' && command.charAt(j + 2) == 'n') {

            j += 3;

          } else {
            chars[len++] = command.charAt(j);
            j++;
          }

        }
        i = j + 1;
      } else {
        if (curr == ' ') {
          if (len > 0)
            commandParts.add(new String(chars, 0, len));
          len = 0;
        } else if (curr == '\\') {
          if (i + 1 < command.length()) {
            chars[len++] = command.charAt(i + 1);
            i++;
          }
        } else {
          chars[len++] = curr;
        }
        i++;
      }
    }
    if (len > 0) {
      commandParts.add(new String(chars, 0, len));
    }

    return commandParts;
  }

}
