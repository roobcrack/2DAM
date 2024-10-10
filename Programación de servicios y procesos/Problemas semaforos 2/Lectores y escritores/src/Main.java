import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main extends Thread {
    private static int lectoresActivos = 0;
    private static int escritoresEsperando = 0;
    private static boolean escritorActivo = false;

    private static Semaphore mutex = new Semaphore(1);
    private static Semaphore semaforoLectores = new Semaphore(1);
    private static Semaphore semaforoEscritores = new Semaphore(1);

    private static Random random = new Random();

    private int tipo; // 0: lector, 1: escritor
    private int id;

    public Main(int tipo, int id) {
        this.tipo = tipo;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            if (tipo == 0) { // Lector
                System.out.printf("El lector %d ha llegado%n", id);

                mutex.acquire();
                while (escritorActivo || escritoresEsperando > 0) {
                    System.out.printf("El lector %d debe esperar%n", id);
                    mutex.release();
                    semaforoLectores.acquire();
                    mutex.acquire();
                }
                lectoresActivos++;
                if (lectoresActivos == 1) {
                    semaforoEscritores.acquire();
                }
                mutex.release();

                System.out.printf("El lector %d está accediendo a la base de datos%n", id);
                simularAcceso();

                mutex.acquire();
                lectoresActivos--;
                if (lectoresActivos == 0) {
                    semaforoEscritores.release();
                }
                System.out.printf("El lector %d ha terminado de acceder a la base de datos%n", id);
                mutex.release();

            } else { // Escritor
                System.out.printf("El escritor %d ha llegado%n", id);

                mutex.acquire();
                escritoresEsperando++;
                mutex.release();

                semaforoEscritores.acquire();
                mutex.acquire();
                escritoresEsperando--;
                escritorActivo = true;
                mutex.release();

                System.out.printf("El escritor %d está accediendo a la base de datos%n", id);
                simularAcceso();

                mutex.acquire();
                escritorActivo = false;
                System.out.printf("El escritor %d ha terminado de acceder a la base de datos%n", id);
                mutex.release();

                semaforoEscritores.release();
                semaforoLectores.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void simularAcceso() throws InterruptedException {
        int tiempo = random.nextInt(5) + 1;
        Thread.sleep(tiempo * 1000);
    }

    public static void main(String[] args) {
        int numHilos = 50;
        Thread[] hilos = new Thread[numHilos];

        for (int i = 0; i < numHilos; i++) {
            int tipo = random.nextInt(2); // 0: lector, 1: escritor
            hilos[i] = new Main(tipo, i + 1);
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
