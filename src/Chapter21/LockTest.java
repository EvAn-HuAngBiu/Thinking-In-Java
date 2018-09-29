package Chapter21;


/**
 * @author Evan
 * @date 2018/09/27 08:20
 */
public class LockTest implements Runnable {
    private int workId;
    private Sync sync;

    public LockTest(int workId, Sync sync) {
        this.workId = workId;
        this.sync = sync;
    }

    @Override
    public void run() {
        sync.test();
    }

    public static void main(String[] args) {
        Sync sync = new Sync();
        Thread t1 = new Thread(new LockTest(1, sync));
        Thread t2 = new Thread(new LockTest(2, sync));
        t1.start();
        t2.start();
        sync.call();

        Sync2 sync2 = new Sync2();
        Thread t3 = new Thread(sync2::increaseNumber);
        t3.start();
        Thread t4 = new Thread(() -> System.out.println(sync2.getI()));
        t4.start();

    }
}


class Sync {
    public synchronized void test() {
        System.out.println("In thread: " + Thread.currentThread() + ", Start");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
        call();
    }

    public synchronized void call() {
        System.out.println(Thread.currentThread() + ", Called");
    }
}

class Sync2 {
    private volatile Integer i = 0;

    public synchronized void increaseNumber() {
        // synchronized (i) {
        //     try {
        //         Thread.sleep(3500);
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        //     i++;
        // }
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i++;
    }

    public synchronized int getI() {
        // synchronized (i) {
        //     return i;
        // }
        return i;
    }
}