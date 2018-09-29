package Chapter11;

import java.util.*;

/**
 * @author Evan
 * @date 2018/09/11 09:59
 */
public class IterableTest implements Iterable<String> {

    private String[] words;

    public IterableTest(String...args) {
        words = new String[args.length];
        System.arraycopy(args, 0, words, 0, args.length);
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < words.length;
            }

            @Override
            public String next() {
                return words[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        IterableTest itt = new IterableTest("This", "is", "a", "great", "idea", ".");
        for (String i : itt) {
            System.out.println(i);
        }

        for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }
}
