package Chapter21;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author Evan
 * @date 2018/09/29 09:33
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        PrioritizedTask[] prioritizedTasks = new PrioritizedTask[10];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            prioritizedTasks[i] = new PrioritizedTask(i, random.nextInt(10));
        }
        PriorityBlockingQueue<PrioritizedTask> queue =
                new PriorityBlockingQueue<>(Arrays.asList(prioritizedTasks));

        exec.execute(new PrioritizedTaskConsumer(queue));
    }
}

class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
    private int id;
    private int priority;

    public PrioritizedTask(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    @Override
    public int compareTo(PrioritizedTask o) {
        return Integer.compare(priority, o.priority);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "PrioritizedTask{" +
                "id=" + id +
                ", priority=" + priority +
                '}';
    }
}

class PrioritizedTaskConsumer implements Runnable {
    private PriorityBlockingQueue<PrioritizedTask> queue;

    public PrioritizedTaskConsumer(PriorityBlockingQueue<PrioritizedTask> queue) {
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

        System.out.println("PriorityBlockingQueue finished successfully");
    }
}