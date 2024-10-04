import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main extends Thread {
    private static final int capacidad = 10;
    private static int space = 0;

    private static Semaphore semaforoCintaLlena = new Semaphore(capacidad);
    private static Semaphore semaforoCintaVacia = new Semaphore(0);
    private static Semaphore mutex = new Semaphore(1);

    private static int contadorProductores = 0;
    private static int contadorConsumidores = 0;

    private static Random random = new Random();

    private int tipo;
    private int id;

    public Main(int tipo) {
        this.tipo = tipo;
        if (tipo == 0) {
            try {
                mutex.acquire(1);
                contadorProductores++;
                id = contadorProductores;
                mutex.release(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                mutex.acquire(1);
                contadorConsumidores++;
                id = contadorConsumidores;
                mutex.release(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try {
            if (tipo == 0) {
                    sleepTime();

                    System.out.println("El productor " + id + " ha producido un dulce");

                    semaforoCintaLlena.acquire(1);
                    mutex.acquire();

                    if (space < capacidad) {
                        space++;
                    } else {
                        System.out.println("El productor " + id + " debe esperar, la cinta transportadora está llena");
                    }

                    mutex.release(1);
                    semaforoCintaVacia.release(1);

            } else {
                    semaforoCintaVacia.acquire(1);
                    mutex.acquire();

                    if (space > 0) {
                        space--;
                    } else {
                        System.out.println("El consumidor " + id + " debe esperar, no hay dulces en la cinta transportadora");
                    }

                    mutex.release();
                    semaforoCintaLlena.release(1);
                    sleepTime();

                    System.out.println("El consumidor " + id + " ha terminado de empaquetar un dulce");
                }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sleepTime() throws InterruptedException {
        Thread.sleep(random.nextInt(100) * 1000);
    }

    public static void main(String[] args) {
        int numHilos = 50;
        Thread[] hilos = new Thread[numHilos];

        for (int i = 0; i < numHilos; i++) {
            int tipo = random.nextInt(2);
            hilos[i] = new Main(tipo);
            hilos[i].start();
        }

        for (int i = 0; i < numHilos; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
