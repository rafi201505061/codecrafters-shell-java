package src.command_parsers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class SimpleShellCommandParserTest {
  @Test
  void testParse() {
    SimpleShellCommandParser simpleShellCommandParser = new SimpleShellCommandParser();
    String s1 = "echo hello world";
    List<String> commands = simpleShellCommandParser.parse(s1);
    assertEquals(commands.size(), 3);
    assertEquals(commands.get(0), "echo");
    assertEquals(commands.get(1), "hello");
    assertEquals(commands.get(2), "world");
    String s2 = "echo 'hello world' hello gello 'world'";
    List<String> commands2 = simpleShellCommandParser.parse(s2);
    assertEquals(commands2.size(), 5);
    assertEquals(commands2.get(0), "echo");
    assertEquals(commands2.get(1), "hello world");
    assertEquals(commands2.get(2), "hello");
    assertEquals(commands2.get(3), "gello");
    assertEquals(commands2.get(4), "world");

  }
}
