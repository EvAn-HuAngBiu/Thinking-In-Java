package Chapter11;

import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author Evan
 * @date 2018/09/11 08:24
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        Random random = new Random(47);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < 10; i++) {
            pq.offer(random.nextInt(20));
        }
        System.out.println(pq);

        Iterator<Integer> it = pq.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
            it.remove();
        }
        System.out.println("\n" + pq);
    }
}
