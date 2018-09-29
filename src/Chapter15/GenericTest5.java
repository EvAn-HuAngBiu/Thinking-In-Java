package Chapter15;

import java.util.Collections;
import java.util.List;

/**
 * @author Evan
 * @date 2018/09/16 10:58
 */
public class GenericTest5 {
    static void testA(Setter s1, Setter s2, SelfBoundSetter sbs) {
        s1.set(s2);
//        s1.set(sbs);
        sbs.set(s1);
    }

    static <S, T extends Iterable<? extends S>> void printAllElements(T list) {
        for (S l : list) {
            System.out.print(l + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> list = Collections.nCopies(5, 1);
        printAllElements(list);
    }
}

interface SelfBoundSetter<T extends SelfBoundSetter<T>> {
    void set(T arg);
}

interface Setter extends SelfBoundSetter<Setter> {

}

