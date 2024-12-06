package src.commands;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class ShellCommandBuilder implements CommandBuilder {
  Scanner scanner;
  Deque<Character> queue;

  public ShellCommandBuilder(Scanner scanner) {
    this.scanner = scanner;
    queue = new ArrayDeque<>();
  }

  private void check(String str) {
    boolean escape = false;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (c == '\\') {
        if (i > 0 && str.charAt(i - 1) == '\\')
          escape = false;
        else {
          escape = true;
        }
        continue;
      }
      if ((c == '\'' || c == '"') && escape) {
        if (queue.isEmpty()) {
          queue.addLast(c);
        } else if (c == queue.peekLast()) {
          queue.pollLast();
        }
      }
    }

  }

  @Override
  public String build() {
    System.out.print("$ ");
    StringBuilder sb = new StringBuilder();
    do {
      String input = scanner.nextLine();
      check(input);
      sb.append(input);

      if (!queue.isEmpty()) {
        sb.append('\n');
        if (queue.peekLast() == '\'') {
          System.out.print("quote> ");
        } else {
          System.out.print("dquote> ");
        }
      }

    } while (!queue.isEmpty());

    return sb.toString();
  }

}
