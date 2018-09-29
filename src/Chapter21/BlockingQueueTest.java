package Chapter21;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Evan
 * @date 2018/09/28 17:42
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(15);
        es.execute(new Consumer(queue));
        es.execute(new Producer(queue));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        es.shutdownNow();
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("Consumer thread start!");
        try {
            while (!Thread.interrupted()) {
                Integer i = queue.take();
                System.out.println("Got integer from queue : " + i);
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer thread interrupted");
        }
        System.out.println("Consumer exited");
    }
}

class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private Random random = new Random(System.currentTimeMillis());

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("Producer thread start!");
        try {
            while (!Thread.interrupted()) {
                queue.put(random.nextInt(20));
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Producer thread interrupted");
        }
        System.out.println("Producer exited");
    }
}