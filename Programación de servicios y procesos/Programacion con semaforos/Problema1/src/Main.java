import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main extends Thread {
    private static Random random = new Random();
    private static int[] contadores = {0, 0}; // Contadores para identificar coches de Gasolina y Diesel
    private int tipo; // Tipo de combustible: 0 para gasolina, 1 para diésel

    private static int enEsperaGasolina = 0, enEsperaDiesel = 0;
    private static int repostandoGasolina = 0, repostandoDiesel = 0;

    private static Semaphore mutex = new Semaphore(1); // Mutex para proteger variables compartidas
    private static Semaphore controlGasolina = new Semaphore(0); // Control para coches de gasolina
    private static Semaphore controlDiesel = new Semaphore(0); // Control para coches de diésel

    public Main(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public void run() {
        try {
            // Proteger el contador y mostrar mensaje de llegada
            mutex.acquire();
            contadores[tipo]++; // Incrementar contador por tipo de vehículo
            int id = contadores[tipo]; // Obtener el ID después de incrementar
            System.out.printf("El conductor %d con coche de %s ha llegado%n", id, tipo == 0 ? "Gasolina" : "Diésel");
            mutex.release();

            if (tipo == 0) { // Gasolina

                mutex.acquire();
                while (repostandoGasolina >= 2) { // Solo 2 surtidores para Gasolina
                    enEsperaGasolina++;
                    System.out.printf("El conductor %d con coche de Gasolina debe esperar, el surtidor está ocupado%n", id);
                    mutex.release();
                    controlGasolina.acquire(); // Esperar hasta que se libere un surtidor
                    mutex.acquire();
                    enEsperaGasolina--;
                }

                repostandoGasolina++;
                System.out.printf("El conductor %d con coche de Gasolina está repostando%n", id);
                mutex.release();

                // Simular el tiempo de repostaje
                int tiempoRepostaje = random.nextInt(5) * 1000;
                Thread.sleep(tiempoRepostaje);

                mutex.acquire();
                repostandoGasolina--;
                System.out.printf("El conductor %d con coche de Gasolina ha terminado de repostar%n", id);
                if (enEsperaGasolina > 0) {
                    controlGasolina.release(); // Avisar a otro coche que puede repostar
                }
                mutex.release();

            } else { // Diésel

                mutex.acquire();
                while (repostandoDiesel >= 1) { // Solo 1 surtidor para Diésel
                    enEsperaDiesel++;
                    System.out.printf("El conductor %d con coche de Diésel debe esperar, el surtidor está ocupado%n", id);
                    mutex.release();
                    controlDiesel.acquire(); // Esperar hasta que se libere un surtidor
                    mutex.acquire();
                    enEsperaDiesel--;
                }

                repostandoDiesel++;
                System.out.printf("El conductor %d con coche de Diésel está repostando%n", id);
                mutex.release();

                // Simular el tiempo de repostaje
                int tiempoRepostaje = random.nextInt(5) * 1000;
                Thread.sleep(tiempoRepostaje);

                mutex.acquire();
                repostandoDiesel--;
                System.out.printf("El conductor %d con coche de Diésel ha terminado de repostar%n", id);
                if (enEsperaDiesel > 0) {
                    controlDiesel.release(); // Avisar a otro coche que puede repostar
                }
                mutex.release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int numHilos = 20; // Número de conductores que llegan
        Thread[] hilos = new Thread[numHilos];
        Random random = new Random();

        // Crear y ejecutar los hilos
        for (int i = 0; i < numHilos; i++) {
            int tipo = random.nextInt(2); // Tipo aleatorio (0 para gasolina, 1 para diésel)
            hilos[i] = new Main(tipo);
            hilos[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (int i = 0; i < numHilos; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
