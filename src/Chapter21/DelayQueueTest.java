package Chapter21;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Evan
 * @date 2018/09/29 08:43
 */
public class DelayQueueTest {
    public static void main(String[] args) {
        DelayTask[] tasks = new DelayTask[10];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            tasks[i] = new DelayTask(random.nextInt(5000));
        }
        DelayQueue<DelayTask> queue = new DelayQueue<>(Arrays.asList(tasks));

        Thread t = new Thread(new DelayTaskConsumer(queue));
        t.start();
    }
}

class DelayTask implements Runnable, Delayed {
    private static int count = 0;
    private int id = count++;
    private long delayMilliseconds;
    private long trigger;

    public DelayTask(long delayMilliseconds) {
        this.delayMilliseconds = delayMilliseconds;
        trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(
                delayMilliseconds, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayTask that = (DelayTask) o;
        return Long.compare(this.trigger, that.trigger);
    }

    @Override
    public void run() {
        System.out.println(this);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(),
                TimeUnit.NANOSECONDS);
    }

    @Override
    public String toString() {
        return "DelayTask{" +
                "id=" + id +
                ", delayMilliseconds=" + delayMilliseconds +
                ", leftTime=" + getDelay(TimeUnit.MILLISECONDS) +
                "}";
    }
}

class DelayTaskConsumer implements Runnable {
    private DelayQueue<DelayTask> queue;

    public DelayTaskConsumer(DelayQueue<DelayTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                queue.take().run();
                if (queue.isEmpty()) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
        System.out.println("Finished all tasks");
    }
}