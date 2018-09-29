package Chapter21;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Evan
 * @date 2018/09/28 19:07
 */
public class PipedIO {
    public static void main(String[] args) throws IOException, InterruptedException {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(receiver);
        es.execute(sender);
        Thread.sleep(4000);
        es.shutdownNow();
    }
}

class Sender implements Runnable {
    private PipedWriter pw = new PipedWriter();

    public PipedWriter getPw() {
        return pw;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (char c = 'A'; c <= 'Z'; c++) {
                    pw.write(c);
                    Thread.sleep(500);
                }
            }
        } catch (IOException e ){
            System.out.println(e + " Sender write exception");
        } catch (InterruptedException e) {
            System.out.println(e + " Send InterruptedException");
        }
    }
}

class Receiver implements Runnable {
    private PipedReader pr;

    public Receiver(Sender sender) throws IOException {
        pr = new PipedReader(sender.getPw());
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Read: " + (char)pr.read());
            }
        } catch (IOException e) {
            System.out.println(e + " Receiver read exception");
        }
    }
}
