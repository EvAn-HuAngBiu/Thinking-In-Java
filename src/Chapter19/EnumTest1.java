package Chapter19;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Evan
 * @date 2018/09/24 07:21
 */
public class EnumTest1 {
    public enum Direction {
        WEST("West side"), EAST("East side"), NORTH("North side"), SOUTH("South side");

        private String description;

        Direction(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "{Name = '" + name() + "\'" + ", " +
                    "ID = '" + ordinal() + "\'" + ", " +
                    "Describtion = '" + description + "\'}";
        }
    }

    private static Random rand = new Random(System.currentTimeMillis());

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    private static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }

    public static void main(String[] args) {
        for (Direction d : Direction.values()) {
            System.out.println(d);
        }

        System.out.println("--------------------------------");
        Class<Direction> clazz = Direction.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }

        // Decompile
        System.out.println("--------------------------------");
        try {
            Process p = new ProcessBuilder("javap",
                    "\"F:\\JAVA\\Thinking in JAVA\\out\\production\\Thinking in JAVA" +
                            "\\Chapter19\\EnumTest1$Direction\"").start();
            Scanner in = new Scanner(p.getInputStream());
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        // Compare
        // Enum class define Comparable interface and set compareTo method final
        System.out.println("--------------------------------");
        System.out.println(Direction.NORTH.compareTo(Direction.SOUTH));

        // Generate different Enums
        System.out.println("--------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(random(Direction.class));
        }
    }
}
