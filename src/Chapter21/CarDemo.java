package Chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Evan
 * @date 2018/09/28 17:10
 */
public class CarDemo {
    public static void main(String[] args) {
        Car car = new Car();
        ExecutorService ex = Executors.newCachedThreadPool();
        ex.execute(new WaxOff(car));
        ex.execute(new WaxOn(car));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ex.shutdownNow();
    }
}

class Car {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean waxOn = false;

    public void waxed() {
        lock.lock();
        try {
            waxOn = true;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void buffed() {
        lock.lock();
        try {
            waxOn = false;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void waitForWaxing() throws InterruptedException {
        lock.lock();
        try {
            while (waxOn == false) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public void waitForBuffing() throws InterruptedException {
        lock.lock();
        try {
            while (waxOn == true) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }
}

class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.print("Wax on! ");
                Thread.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("Wax on Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }
}

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.print("Wax off! ");
                Thread.sleep(200);
                car.buffed();
                car.waitForWaxing();
            }
        } catch (InterruptedException e) {
            System.out.println("Wax off Exiting via interrupt");
        }
        System.out.println("Ending Wax Off task");
    }
}