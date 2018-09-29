package Chapter16;

import java.util.*;

/**
 * @author Evan
 * @date 2018/09/18 09:56
 */
public class ArrayTest {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Random random = new Random(47);
        int[][][] thirdWayArray = new int[random.nextInt(7)][][];
        for (int i = 0 ; i < thirdWayArray.length; i++) {
            thirdWayArray[i] = new int[random.nextInt(5)][];
            for (int j = 0; j < thirdWayArray[i].length; j++) {
                thirdWayArray[i][j] = new int[random.nextInt(5)];
            }
        }

        System.out.println(Arrays.deepToString(thirdWayArray));

        List<String>[] strings = new List[5];
        strings[0] = new ArrayList<>();
        // Illegal assignment
        // strings[1] = new ArrayList<Integer>()

        Object[] objs = strings;
        // It's ok
        objs[1] = new ArrayList<Integer>();

        String[] names = {"Ada", "Bob", "Evan", "David", "Elizabeth", "George", "James", "ELizabeth"};
        System.out.println("Before sorting: \n" + Arrays.toString(names));

        Arrays.sort(names);
        System.out.println("After normal sorting: \n" + Arrays.toString(names));

        // Or use this
        // Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println("After IgnoreCase Sort: \n" + Arrays.toString(names));

        Integer[] ints = {4, 6, 3, 7, 1, 9, 2, 5, 8};
        System.out.println(Arrays.binarySearch(ints, 3));

        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (o1 > o2 ? -1 : (o1.equals(o2) ? 0 : 1));
            }
        };
        Arrays.sort(ints, cmp);
        System.out.println(Arrays.binarySearch(ints, 3));
        System.out.println(Arrays.binarySearch(ints, 3, cmp));
    }
}
