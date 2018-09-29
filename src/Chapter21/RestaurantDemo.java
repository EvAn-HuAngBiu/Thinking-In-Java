package Chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Evan
 * @date 2018/09/28 16:35
 */
public class RestaurantDemo {
    Meal meal;
    Waiter waiter = new Waiter(this);
    Chef chef = new Chef(this);
    ExecutorService es = Executors.newCachedThreadPool();

    public RestaurantDemo() {
        es.execute(waiter);
        es.execute(chef);
    }

    public static void main(String[] args) {
        new RestaurantDemo();
    }

}

class Meal {
    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal " + orderNum;
    }
}

class Waiter implements Runnable {
    private RestaurantDemo restaurantDemo;

    public Waiter(RestaurantDemo restaurantDemo) {
        this.restaurantDemo = restaurantDemo;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Lock a waiter object so as to call Waiter.wait()
                // Synchronized block whether check meal object is null
                // The operation of writing meal object only take place in Waiter object
                // Others will not write meal object, only read
                synchronized (this) {
                    while (restaurantDemo.meal == null) {
                        wait();
                    }
                }
                System.out.println("Waiter got " + restaurantDemo.meal);
                synchronized (restaurantDemo.chef) {
                    restaurantDemo.meal = null;
                    restaurantDemo.chef.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Waiter interrupted");
        }
    }
}

class Chef implements Runnable {
    private RestaurantDemo restaurantDemo;
    private int count = 0;

    public Chef(RestaurantDemo restaurantDemo) {
        this.restaurantDemo = restaurantDemo;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurantDemo.meal != null) {
                        wait();
                    }
                }
                if (++count == 10) {
                    System.out.println("Out of food, closing");
                    restaurantDemo.es.shutdownNow();
                }
                System.out.print("Order up! ");
                // Only Waiter object write meal, must keep synchronized
                synchronized (restaurantDemo.waiter) {
                    restaurantDemo.meal = new Meal(count);
                    restaurantDemo.waiter.notifyAll();
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Chef interrupted");
        }
    }
}
