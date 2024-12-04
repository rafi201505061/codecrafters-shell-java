package src;

import java.nio.file.Paths;

public class StaticReferences {
  public static String cwd = Paths.get("").toAbsolutePath().toString();
}
