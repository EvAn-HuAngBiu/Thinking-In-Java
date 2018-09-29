package Chapter12;

/**
 * @author Evan
 * @date 2018/09/11 18:43
 */
public class ExceptionTest {
    public static void throwException() throws Exception {
        throw new Exception("Throw an exception");
    }

    public static void f1() throws Exception {
        throwException();
    }

    public static void main(String[] args) {
        try {
            f1();
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
            StackTraceElement[] traces = e.getStackTrace();
            System.out.println(traces.length);
            for (StackTraceElement ste : traces) {
                System.out.println(ste);
            }
        }
        System.out.println();

        try {
            f1();
        } catch (Exception e) {
            System.out.println("Original Exception object:");
            e.printStackTrace(System.out);
            Throwable t = e.fillInStackTrace();
            System.out.println("New Throwable object: ");
            t.printStackTrace(System.out);

        }
    }
}
