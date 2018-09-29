package Chapter11;

import java.util.*;

/**
 * @author Evan
 * @date 2018/09/10 08:44
 */
public class CollectionToolsTest {
    public static void main1(String[] args) {
        Collection<Integer> c1 = Arrays.asList(3, 4, 5, 6, 7);
        System.out.println(c1);
//        Unsupported operation, asList method will create a limited-length array
//        Cannot add or delete element from the collection created by asList
//        c1.add(8);

        List<Snow> snow1 = Arrays.asList(new Snow(), new Crusty(), new Slush());
//        Same as this one before JAVA 8
//        List<Snow> snow2 = Arrays.<Snow>asList(new Light(), new Heavy());
        List<Snow> snow2 = Arrays.asList(new Light(), new Heavy());

        // Or use this
        List<Snow> snow3 = new ArrayList<>();
        Collections.addAll(snow3, new Light(), new Heavy());
    }

    public static void main(String[] args) {
        ArrayList<Integer> ints = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            ints.add(i);
        }
        System.out.println(ints);

        // contain method
        System.out.println(ints.contains(3));
        // remove method
        // Attention: if you just pass an integer to remove method,
        // it will delete the element which has index of the integer you passed.
        // If you want to delete an integer reference, use Integer object instead.
        System.out.println(ints.remove(Integer.valueOf(2)));
        System.out.println(ints.remove(Integer.valueOf(6)));
        // subList method
        List<Integer> subInts = ints.subList(1, 3);
        System.out.println(subInts);
        // containAll method
        System.out.println(ints.containsAll(subInts));
        // retainAll method
        System.out.println(ints.retainAll(subInts));
        System.out.println(ints);

        // toArray method, and you can initialize a target array first and then,
        // use target array as the argument in the method
        Integer[] arr = ints.toArray(new Integer[0]);
        System.out.println(Arrays.toString(arr));
    }
}

class Snow {

}

class Powder extends Snow {

}

class Light extends Powder {

}

class Heavy extends Powder {

}

class Crusty extends Snow {

}

class Slush extends Snow {

}