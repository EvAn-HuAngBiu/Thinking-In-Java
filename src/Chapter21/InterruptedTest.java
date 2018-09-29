package Chapter21;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Evan
 * @date 2018/09/27 17:07
 */
public class InterruptedTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        ExecutorService es = Executors.newCachedThreadPool();

        Thread t1 = new Thread(new SimpleInterrupt());
        t1.start();
        t1.interrupt();
        // Wait for throwing exception
        Thread.sleep(1);
        // After throwing an exception, the status will be cleared (false)
        // But if you don't wait for throwing, it will keep interrupted flag (true)
        System.out.println("Is interrupted: " + t1.isInterrupted());

        Future<?> f1 = es.submit(new IOInterrupt(System.in));
        Thread.sleep(500);
        f1.cancel(true);
        System.out.println(f1.isCancelled());

        es.shutdownNow();
        Thread.sleep(500);
        System.out.println("------------------------------");

        FileChannel fc = new FileInputStream("src/Chapter21/DaemonsTest.java").
                getChannel();
        ExecutorService es2 = Executors.newCachedThreadPool();
        es2.execute(new NIOInterrupt(fc));

        Future<?> f2 = es2.submit(new NIOInterrupt(fc));

        es2.shutdown();
        Thread.sleep(1);
        f2.cancel(true);
    }
}

class SimpleInterrupt implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }

        System.out.println("SimpleInterrupt finished successfully");
    }
}

class IOInterrupt implements Runnable {
    private InputStream is;

    public IOInterrupt(InputStream is) {
        this.is = is;
    }

    @Override
    public void run() {
        try {
            System.out.println("Running IOInterrupt read() method");
            is.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Current thread has been interrupted");
            }
        }

        System.out.println("IOInterrupt finished successfully");
    }
}

class NIOInterrupt implements Runnable {
    private final FileChannel fc;

    public NIOInterrupt(FileChannel fc) {
        this.fc = fc;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().toString();
        try {
            System.out.println(name + ", Running NIOInterrupt read method");
            fc.read(ByteBuffer.allocate(1));
        } catch (ClosedByInterruptException e) {
            // When the pool shutdown, cause this exception
            System.out.println(name + ", Cause ClosedByInterruptException");
        } catch (AsynchronousCloseException e) {
            // When the resources close, cause this exception
            System.out.println(name + ", Cause AsynchronousCloseException");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        System.out.println(name + ", NIOInterrupt finished successfully");
    }
}