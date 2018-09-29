package Chapter17;

import java.util.Arrays;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Evan
 * @date 2018/09/20 08:09
 */
public class CollectionTest2 {
    public static void main(String[] args) {
        // The element in TreeSet must be comparable
        TreeSet<TreeSetTest> ts = new TreeSet<>();
        TreeSetTest[] treeSetTests = {
                new TreeSetTest(),
                new TreeSetTest(),
                new TreeSetTest(),
                new TreeSetTest(),
                new TreeSetTest(),
                new TreeSetTest(),
        };
        ts.add(treeSetTests[5]);
        ts.add(treeSetTests[3]);
        ts.add(treeSetTests[4]);
        ts.add(treeSetTests[2]);
        ts.add(treeSetTests[0]);
        ts.add(treeSetTests[1]);

        System.out.println(ts);

        // HashSet need nothing as the hashCode method exists in the Object class
        HashSet<TreeSetTest> hs = new HashSet<>(Arrays.asList(treeSetTests));
        System.out.println(hs);

        SortedSet<TreeSetTest> ss = ts;
        System.out.println(ts.first());
        System.out.println(ts.last());
    }
}

class TreeSetTest implements Comparable<TreeSetTest> {
    public static int i = 0;
    private int count = i++;

    @Override
    public String toString() {
        return "TreeSetTest{" +
                "count=" + count +
                '}';
    }

    @Override
    public int compareTo(TreeSetTest o) {
        return (Integer.compare(this.count, o.count));
    }
}
