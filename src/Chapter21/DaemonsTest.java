package Chapter21;

/**
 * @author Evan
 * @date 2018/09/26 19:54
 */
public class DaemonsTest implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            System.out.println("Sleep() interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new DaemonsTest());
            daemon.setDaemon(true);
            daemon.start();
        }

        System.out.println("All daemons started");
        Thread.sleep(175);
    }
}
