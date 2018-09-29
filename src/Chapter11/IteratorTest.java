package Chapter11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author Evan
 * @date 2018/09/10 20:22
 */
public class IteratorTest {
    public static void main(String[] args) {
        ArrayList<Integer> ints = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ints.add(i);
        }
        Collections.shuffle(ints);
        System.out.println(ints);

        Iterator<Integer> it = ints.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + "\t");
//            it.remove();
        }
        System.out.println();
        ints.forEach(System.out::println);

        ArrayList<String> names = new ArrayList<>();
        names.add("Ada");
        names.add("Bob");
        names.add("David");
        names.add("Emma");
        names.add("Elizabeth");
        ListIterator<String> lit = names.listIterator();
        while (lit.hasNext()) {
            String s = lit.next();
            if (s.equals("David")) {
                lit.set("Joe");
                lit.previous();
            }
            System.out.println(s);
        }
    }
}
