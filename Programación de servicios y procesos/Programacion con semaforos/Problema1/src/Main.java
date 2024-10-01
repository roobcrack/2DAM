import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main extends Thread {
    private static final Semaphore gasolinaSurtidores = new Semaphore(2);

    private static final Semaphore dieselSurtidores = new Semaphore(1);
    private final int tipo;
    private static final Random random = new Random();

    public Main(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public void run() {
        try {
            if (tipo == 0) {
                System.out.println("Coche de Gasolina ha llegado.");
                repostar(gasolinaSurtidores, "Gasolina");
            } else {
                System.out.println("Coche de Diésel ha llegado.");
                repostar(dieselSurtidores, "Diesel");
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void repostar(Semaphore surtidor, String tipoCombustible) throws InterruptedException {
        if (!surtidor.tryAcquire()) {
            System.out.println("Coche de " + tipoCombustible + " debe esperar, el surtidor está ocupado.");
            surtidor.acquire();
        }

        System.out.println("Coche de " + tipoCombustible + " está repostando.");
        Thread.sleep(random.nextInt(5000) + 1000);
        System.out.println("Coche de " + tipoCombustible + " ha terminado de repostar.");
        surtidor.release();
    }

    public static void main(String[] args) {
        Thread[] conductores = new Thread[20];

        for (int i = 0; i < 20; i++) {
            int tipo = random.nextInt(2);
            conductores[i] = new Main(tipo);
        }

        for (Thread conductor : conductores) {
            conductor.start();
        }

        for (Thread conductor : conductores) {
            try {
                conductor.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
