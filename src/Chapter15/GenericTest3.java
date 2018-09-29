package Chapter15;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Evan
 * @date 2018/09/15 10:18
 */
public class GenericTest3 {
    public static void main(String[] args) {
        Fruit[] fruits = new Apple[5];
        fruits[0] = new Apple();
        fruits[1] = new Jonathan();

        try {
            fruits[0] = new Fruit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple());
        apples.add(new Jonathan());
        List<? extends Fruit> fruitList = new ArrayList<>(apples);
        fruitList.forEach(e -> System.out.println(e.getClass().getSimpleName()));
        System.out.println(fruitList.contains(new Apple()));
        System.out.println(fruitList.indexOf(fruitList.get(1)));

        List<? super Apple> appleList = new ArrayList<>();
        appleList.add(new Jonathan());

        Holder1<?> holder1 = new Holder1<>();
        //holder1.setVal(new Holder1<>());
    }
}

class Fruit {}
class Apple extends Fruit {}
class Jonathan extends Apple {}
class Banana extends Fruit {}

class Holder1<T> {
    private T val;

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }
}