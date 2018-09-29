package Chapter19;

import java.util.EnumSet;

/**
 * @author Evan
 * @date 2018/09/24 08:29
 */
public class EnumTest2 {
    public enum Direction {
        // Each method inside the enum object has been explained as a class
        // But when it exists in the enum object, it's still a enum instance
        EAST {
            String getInfo() {
                return "EAST SIDE";
            }
        }, WEST {
            String getInfo() {
                return "WEST SIDE";
            }
        }, NORTH {
            String getInfo() {
                return "NORTH SIDE";
            }
        }, SOUTH {
            String getInfo() {
                return "SOUTH SIDE";
            }
        };

        abstract String getInfo();
    }

    public static void main(String[] args) {
        EnumSet<Direction> set = EnumSet.allOf(Direction.class);
        System.out.println(set);

        set = EnumSet.noneOf(Direction.class);
        System.out.println(set);

        EnumSet<Direction> set1 = EnumSet.of(Direction.EAST, Direction.NORTH);
        set = EnumSet.complementOf(set1);
        System.out.println(set);

        set = EnumSet.range(Direction.EAST, Direction.NORTH);
        System.out.println(set);

        System.out.println("-----------------------------------------");
        for (Direction d : set) {
            System.out.println(d.getInfo());
        }


    }
}
