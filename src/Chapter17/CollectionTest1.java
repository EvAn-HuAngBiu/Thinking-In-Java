package Chapter17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Evan
 * @date 2018/09/19 18:57
 */
public class CollectionTest1 {
    public static void main(String[] args) {
        // use nCopies
        List<StringAddress> strList1 = new ArrayList<>(
                Collections.nCopies(3, new StringAddress("EAST XUJING")));
        strList1.forEach(System.out::println);
        // use fill
        // fill can only replace the element which exists in the collection
        // but cannot add new elements into the collection
        System.out.println("---------------------------------------------");
        List<StringAddress> strList2 = new ArrayList<>();
        Collections.fill(strList2, new StringAddress("GUANGLAN ROAD"));

        // UnsupportedOperationException
        // Cannot modify the collection
        List<String> strList3 = Arrays.asList("Ada", "Bob", "Evan");
        try {
            System.out.println(strList3.add("Jane"));
        } catch (UnsupportedOperationException e) {
            e.printStackTrace(System.out);
        }
        System.out.println(strList3.getClass().getSimpleName());

        // Put asList method to the constructor can get a normal collection with all operations
        List<String> strList4 = new ArrayList<>(Arrays.asList("Ada", "Bob", "Evan"));
        System.out.println(strList4.add("Jane"));


    }
}

class StringAddress {
    private String string;

    public StringAddress(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return super.toString() + ", StringAddress{" +
                "string='" + string + '\'' +
                '}';
    }
}