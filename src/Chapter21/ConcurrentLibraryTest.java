package Chapter21;

import java.util.concurrent.*;

/**
 * @author Evan
 * @date 2018/09/28 21:53
 */
public class ConcurrentLibraryTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(5);
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 1; i <= 5; i++) {
            es.execute(new Demo(i, cdl));
        }
        for (int i = 1; i <= 5; i++) {
            Thread.sleep(100);
            cdl.countDown();
        }
        Thread.sleep(100);
        es.shutdownNow();

        CyclicBarrier barrier = new CyclicBarrier(5);
        es = Executors.newCachedThreadPool();
        for (int i = 101; i <= 105; i++) {
            es.execute(new Demo2(i, barrier));
        }
        Thread.sleep(100);
        es.shutdownNow();
    }
}

class Demo implements Runnable {
    private int id;
    private CountDownLatch cdl;

    public Demo(int id, CountDownLatch cdl) {
        this.id = id;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Demo " + id + " is now running");
    }
}

class Demo2 implements Runnable {
    private int id;
    private CyclicBarrier barrier;

    public Demo2(int id, CyclicBarrier barrier) {
        this.id = id;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("Demo2 " + id + " is working");
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("Demo2 " + id + " finished");
    }
}