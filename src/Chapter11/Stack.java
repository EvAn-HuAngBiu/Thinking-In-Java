package Chapter11;

import java.util.LinkedList;

/**
 * @author Evan
 * @date 2018/09/10 21:13
 */
public class Stack<T> {
    private LinkedList<T> storage = new LinkedList<>();
    public void push(T t) {
        storage.addFirst(t);
    }

    public T peek() {
        return storage.getFirst();
    }

    public T pop() {
        return storage.removeFirst();
    }

    public boolean isEmpty() {
        return storage.isEmpty();
    }

    @Override
    public String toString() {
        return storage.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(5);
        stack.push(7);
        stack.push(9);

        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}
