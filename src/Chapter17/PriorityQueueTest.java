package Chapter17;

import java.util.*;

/**
 * @author Evan
 * @date 2018/09/20 08:39
 */
public class PriorityQueueTest extends PriorityQueue<PriorityQueueTest.ToDoItem> {
    static class ToDoItem implements Comparable<ToDoItem> {
        private char primary;
        private int secondary;
        private String item;

        public ToDoItem(char primary, int secondary, String item) {
            this.primary = primary;
            this.secondary = secondary;
            this.item = item;
        }

        @Override
        public int compareTo(ToDoItem o) {
            if (primary > o.primary) {
                return 1;
            }
            if (primary == o.primary) {
                if (secondary > o.secondary) {
                    return 1;
                } else if (secondary == o.secondary) {
                    return 0;
                }
            }
            return -1;
        }

        @Override
        public String toString() {
            return Character.toString(primary) + secondary + ": " + item;
        }

    }

    public void add(String td, char pri, int sec) {
        super.add(new ToDoItem(pri, sec, td));
    }

    public static void main(String[] args) {
        PriorityQueueTest pq = new PriorityQueueTest();
        pq.add("Empty trash", 'C', 4);
        pq.add("Feed dog", 'A', 2);
        pq.add("Feed bird", 'B', 7);
        pq.add("Mow lawn", 'C', 3);
        pq.add("Water lawn", 'A', 1);
        pq.add("Feed cat", 'B', 1);

        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }

        List bases = Collections.checkedList(new ArrayList(), Base.class);
        bases.add(new Base());
        bases.add(new Derived());
        System.out.println(bases);

        List derived = Collections.checkedList(new ArrayList(), Derived.class);
        derived.add(new Derived());
        // ClassCastException
        // derived.add(new Base());
        System.out.println(derived);

        List<Integer> list = Arrays.asList(9, 3, 7, 4, 1, 5, 8);

        // Use default comparator
        System.out.print("Use default comparator: ");
        System.out.println(Collections.max(list));

        // Change the comparator to print the min value by max method
        System.out.print("Use given comparator: ");
        System.out.println(Collections.max(list, (o1, o2) -> -Integer.compare(o1, o2)));

        // Print index of sub-List
        System.out.print("Sub-list index: ");
        System.out.println(Collections.indexOfSubList(list, Arrays.asList(4, 1, 5)));

        // compare first then reverse
        list.sort(Collections.reverseOrder());
        System.out.println(list);

        // rotate some distance
        Collections.rotate(list, 3);
        System.out.println(list);

        List<String> stringList = new ArrayList<>(Arrays.asList("Ada", "Bob", "Evan"));
        Iterator<String> it = stringList.iterator();
        stringList.add("Jane");
        try {
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        } catch (ConcurrentModificationException e) {
            e.printStackTrace(System.out);
        }
    }
}

class Base {
    public static int i = 0;
    private int count = i++;

    @Override
    public String toString() {
        return "Base{" +
                "count=" + count +
                '}';
    }
}

class Derived extends Base {
    public static int i = 0;
    private int count = i++;

    @Override
    public String toString() {
        return "Derived{" +
                "count=" + count +
                '}';
    }
}