package Chapter12;

/**
 * @author Evan
 * @date 2018/09/11 20:59
 */
public class ExceptionTest3 {
    public static void main(String[] args) {
        try {
            DerivedClass dc = new DerivedClass();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}

class BaseClass {
    public BaseClass() throws Exception {
        throw new Exception("Base exception");
    }
}

class DerivedClass extends BaseClass {
    public DerivedClass() throws Exception {
        System.out.println("In derived class");
    }

}