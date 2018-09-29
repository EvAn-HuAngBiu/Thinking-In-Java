package Chapter12;

/**
 * @author Evan
 * @date 2018/09/11 20:28
 */
public class ExceptionTest2 {
    public static void f() throws VeryImportantException {
        throw new VeryImportantException();
    }

    public static void dispose() throws HoHumException {
        throw new HoHumException();
    }

    public static void main(String[] args) {
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("In catch clause");
//            return;
//            System.exit(0);   This statement will lead to ignore finally clause
        } finally {
            System.out.println("In finally clause");
        }

        try {
            try {
                f();
            } finally {
                dispose();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class VeryImportantException extends Exception {
    @Override
    public String toString() {
        return "A very important exception";
    }
}

class HoHumException extends Exception {
    @Override
    public String toString() {
        return "A trivial exception";
    }
}