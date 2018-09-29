package Chapter14;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.math.BigInteger;

/**
 * @author Evan
 * @date 2018/09/13 07:37
 */
public class ClassTest {
    public static void main(String[] args) {
        Number n = new BigInteger("32769");
        Class<BigInteger> bigIntegerClass = BigInteger.class;
        BigInteger b = bigIntegerClass.cast(n);

        System.out.println(b);

        Class<ClassTest> thisClass = ClassTest.class;
        Method[] methods = thisClass.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m);
        }

        System.out.println("---------------------------------------");
        System.out.println("Super class methods: ");
        Class<? super ClassTest> superClass = thisClass.getSuperclass();
        Method[] superMethods = superClass.getDeclaredMethods();
        for (Method m : superMethods) {
            System.out.println(m);
        }

        System.out.println("---------------------------------------");
        System.out.println("Super constructors: ");
        Constructor[] superConstructors = superClass.getDeclaredConstructors();
        for (Constructor c : superConstructors) {
            System.out.println(c);
        }
    }
}
