package Chapter21;

/**
 * @author Evan
 * @date 2018/09/28 10:26
 */
public class ThreadCooperationTest {
    public static void main(String[] args) {
        Runnable r = new WaitThread(5);
        Thread thread = new Thread(r);
        thread.start();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // wait, notify, notifyAll methods must be placed into a synchronized block
        // synchronized block must lock the same element or type
        synchronized (r) {
            r.notify();
        }
    }

}

class WaitThread implements Runnable {
    private int id;

    public WaitThread(int id) {
        this.id = id;
    }

    public void begin() {
        System.out.println("Start WaitThread " + id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
        System.out.println("Thread " + id + " begins wait");
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread " + id + " finished waiting");
    }

    @Override
    public void run() {
        begin();

    }
}
