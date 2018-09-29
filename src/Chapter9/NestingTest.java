package Chapter9;

/**
 * @author Evan
 * @date 2018/09/08 20:10
 */
public class NestingTest {
    public static void main(String[] args) {
        A a = new A();
//        A.DImp2 di2 = a.getD();
//        a.getD().f();
        a.receiveD(a.getD());
    }
}

class A {
    interface IB {
        void f();
    }

    public class BImp implements IB {
        public void f() {
        }
    }

    private class BImp2 implements IB {
        public void f() {
        }
    }

    public interface IC {
        void f();
    }

    class CImp implements IC {
        public void f() {
        }
    }

    private class CImp2 implements IC {
        public void f() {
        }
    }

    private interface D {
        void f();
    }

    private class DImp implements D {
        public void f() {
        }
    }

    public class DImp2 implements D {
        public void f() {
        }
    }

    public D getD() {
        return new DImp2();
    }

    public void receiveD(D d) {
    }
}
