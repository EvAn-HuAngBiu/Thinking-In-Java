package Chapter21;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Evan
 * @date 2018/09/26 16:46
 */
public class ThreadTest {
    private static long timeSpan = 0;
    private static volatile boolean stopFlag = false;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es1 = Executors.newCachedThreadPool();

        // Normal thread
        Runnable r1 = () -> {
            while (!stopFlag) {
                timeSpan++;
            }
        };
        es1.execute(r1);
        Thread.sleep(500);
        stopFlag = true;
        System.out.println(timeSpan);

        // Thread with return value
        Callable<ArrayList<Integer>> c1 = () -> {
            Random r = new Random(System.currentTimeMillis());
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                arrayList.add(r.nextInt(20));
            }
            return arrayList;
        };
        Future<ArrayList<Integer>> result = es1.submit(c1);
        System.out.println(result.get());

        // Exception inside thread
        // try-catch block in main cannot catch this exception
        // Runnable r2 = () -> {
        //    throw new RuntimeException("Exception sample");
        // };
        // es1.execute(r2);

        // shutdown method will prevent new task to be added into the pool
        // The tasks already in the thread are not affected and they will continue to run
        es1.shutdown();

        System.out.println("----------------------------------------------------");
        Thread t = new Thread(() -> {
            throw new RuntimeException("Exception sample");
        });
        // This method will set default handler
        // Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        t.setUncaughtExceptionHandler(new ExceptionHandler());
        t.start();

    }
}

class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Current thread: " + t);
        e.printStackTrace(System.out);
    }
}
