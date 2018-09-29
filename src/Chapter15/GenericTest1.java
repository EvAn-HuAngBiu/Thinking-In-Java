package Chapter15;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Evan
 * @date 2018/09/14 18:07
 */
public class GenericTest1 {
    public static <T> List<T> generateList(int count, T obj) {
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(obj);
        }
        return list;
    }

    public static void printList(List<? extends List> list) {
        list.forEach(e -> System.out.print(e + "    "));
        System.out.println();
    }

    public static void main(String[] args) throws NoSuchMethodException {
        List<Integer> list = generateList(5, 10);
        System.out.println(list);
        list.add(15);
        System.out.println(list);
//        Cannot use generic type to method argument
//        printList(generateList(3, 15));

        Class<GenericTest1> thisClass = GenericTest1.class;
        Method[] methods = thisClass.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        System.out.println("-----------------------------------------");
        System.out.println(Arrays.toString(methods[1].getTypeParameters()));

        TestListClass<Integer> intClass = new TestListClass<>();
        intClass.insertToList(15);
//        intClass.insertToList("String");  // Not allowed

        // Using reflection can insert an irrelevant value to a list
        Class<TestListClass> listClass = TestListClass.class;
        Method method = listClass.getMethod("insertToList", Object.class);
        try {
            method.invoke(intClass, "Hello");
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(intClass.getList());
    }
}

class TestListClass<T> {
    private List<T> list = new ArrayList<>();

    public void insertToList(T obj) {
        list.add(obj);
    }

    public List<T> getList() {
        return list;
    }
}