package Chapter18;

import java.nio.file.*;

/**
 * @author Evan
 * @date 2018/09/21 16:57
 */
public class PathTest {
    public static void main(String[] args) {
        Path base = Paths.get(".");
        System.out.println(base.toAbsolutePath());

        System.out.println("------------------------------------------------");
        Path relative = Paths.get("src/Chapter17");
        System.out.println(relative);
        System.out.println(relative.toAbsolutePath());

        System.out.println("------------------------------------------------");
        Path absolute = Paths.get("F:", "JAVA", "Thinking in Java", "src", "Chapter18");
        System.out.println(absolute);
        System.out.println("Is absolute path: " + absolute.isAbsolute());

        System.out.println("------------------------------------------------");
        // Use an exist Path to initialize a new Path
        Path resolved = base.resolve("src");
        System.out.println(resolved.toAbsolutePath());

        System.out.println("------------------------------------------------");
        // Use normalize to eliminate redundant name
        System.out.println(resolved.toAbsolutePath().normalize());

        System.out.println("------------------------------------------------");
        // Use resolveSibling will create a new Path with same parent Path
        // e.g. this example use exist Path with src/Chapter17 to create src/Chapter16
        Path resolveSibling = relative.resolveSibling("Chapter16");
        System.out.println(resolveSibling.toAbsolutePath());

        System.out.println("------------------------------------------------");
        System.out.println(absolute.getParent());
        System.out.println(absolute.getRoot());
        System.out.println(absolute.getFileName());

        System.out.println("------------------------------------------------");

    }
}
