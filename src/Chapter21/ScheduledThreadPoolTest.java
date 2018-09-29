package Chapter21;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Evan
 * @date 2018/09/29 10:09
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        GreenHouseScheduler gh = new GreenHouseScheduler();
        gh.schedule(gh.new Terminate(), 0);
        Thread.sleep(100);
        System.out.println("------------------------------------");

        gh = new GreenHouseScheduler();
        gh.repeat(gh.new Bell(), 0, 1000);
        gh.repeat(gh.new ThermostatNight(), 10, 2000);
        gh.repeat(gh.new LightOn(), 15, 200);
        gh.repeat(gh.new LightOff(), 20, 400);
        gh.repeat(gh.new WaterOn(), 25, 600);
        gh.repeat(gh.new WaterOff(), 30, 800);
        gh.repeat(gh.new ThermostatDay(), 35, 1400);

        gh.schedule(gh.new Terminate(), 1000);
    }
}

class GreenHouseScheduler {
    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostat = "Day";

    ScheduledThreadPoolExecutor exec =
            new ScheduledThreadPoolExecutor(10);

    public GreenHouseScheduler() {
        System.out.println("System started!");
    }

    public synchronized String getThermostat() {
        return thermostat;
    }

    public synchronized void setThermostat(String thermostat) {
        this.thermostat = thermostat;
    }

    public void schedule(Runnable event, long delay) {
        exec.schedule(event, delay, TimeUnit.MILLISECONDS);
    }

    public void repeat(Runnable event, long initalDelay, long period) {
        exec.scheduleAtFixedRate(event, initalDelay, period, TimeUnit.MILLISECONDS);
    }

    @Override
    public String toString() {
        return "GreenHouseScheduler{" +
                "light=" + light +
                ", water=" + water +
                ", thermostat='" + thermostat + '\'' +
                '}';
    }

    class LightOn implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning on lights");
            light = true;
        }
    }

    class LightOff implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning off lights");
            light = false;
        }
    }

    class WaterOn implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning on water");
            water = true;
        }
    }

    class WaterOff implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning off water");
            water = false;
        }
    }

    class ThermostatNight implements Runnable {
        @Override
        public void run() {
            System.out.println("Thermostat to night setting");
            setThermostat("Night");
        }
    }

    class ThermostatDay implements Runnable {
        @Override
        public void run() {
            System.out.println("Thermostat to day setting");
            setThermostat("Day");
        }
    }

    class Bell implements Runnable {
        @Override
        public void run() {
            System.out.println("Bing!");
        }
    }

    class Terminate implements Runnable {
        @Override
        public void run() {
            System.out.println("Terminating");
            System.out.println("Current status: " + GreenHouseScheduler.this);
            exec.shutdownNow();
        }
    }
}