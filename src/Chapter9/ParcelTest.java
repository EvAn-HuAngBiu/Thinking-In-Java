package Chapter9;

/**
 * @author Evan
 * @date 2018/09/09 11:46
 */
public class ParcelTest {
    public Wrapping wrapping(int x) {
        return new Wrapping(x) {
            @Override
            public int value() {
                return super.value() * 5;
            }
        };
    }

    public Wrapping wrapping2(int x, final String s) {
        return new Wrapping(x) {
            private String name;

            {
                // this part is like constructor
                // initialize variables
                name = s;
            }

            public String getValue() {
                return "Name = " + name + ", Value = " + super.value();
            }

        };
    }

    public static void main(String[] args) {
        ParcelTest pt = new ParcelTest();
        Wrapping w1 = pt.wrapping(5);
        System.out.println(w1.value());

        Wrapping w2 = pt.wrapping2(10, "Joe");
        System.out.println(w2.getValue());
    }
}

class Wrapping {
    public int x;

    public Wrapping(int x) {
        this.x = x;
    }

    public int value() {
        return x;
    }

    public String getValue() {
        return null;
    }

    public static class InnerStaticClass {
        private int id;
        private static int count;

        static {
            count++;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}