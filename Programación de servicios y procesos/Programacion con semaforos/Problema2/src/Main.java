import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main extends Thread {

    private static final Semaphore lavado = new Semaphore(2);
    private static final Semaphore enjuague = new Semaphore(1);
    private static final Semaphore secado = new Semaphore(2);
    private static final Random random = new Random();

}