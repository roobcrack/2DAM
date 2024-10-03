import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main extends Thread {
    private static Random random = new Random();
    private static int contadorVehiculos = 0;
    private int idVehiculo;
    private static Semaphore semaforoLavado = new Semaphore(2);
    private static Semaphore semaforoEnjuague = new Semaphore(1);
    private static Semaphore semaforoSecado = new Semaphore(2);
    private static Semaphore mutex = new Semaphore(1);

    public Main() {
        try {
            mutex.acquire(1);
            contadorVehiculos++;
            idVehiculo = contadorVehiculos;
            mutex.release(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            // Lavado
            System.out.println("Vehiculo " + idVehiculo + " esta esperando para lavado");
            semaforoLavado.acquire(1);
            System.out.println("Vehiculo " + idVehiculo + " esta siendo lavado");
            simularTiempo();
            System.out.println("Vehiculo "+ idVehiculo + " ha terminado de lavarse");
            semaforoLavado.release(1);

            // Enjuague
            System.out.println("Vehiculo " + idVehiculo + " esta esperando para ser enjuagado");
            semaforoLavado.acquire(1);
            System.out.println("Vehiculo " + idVehiculo + " esta siendo enjuagado");
            simularTiempo();
            System.out.println("Vehiculo "+ idVehiculo + " ha terminado de enjuagarse");
            semaforoLavado.release(1);

            // Secado
            System.out.println("Vehiculo " + idVehiculo + " esta esperando para ser secado");
            semaforoLavado.acquire(1);
            System.out.println("Vehiculo " + idVehiculo + " esta siendo secado");
            simularTiempo();
            System.out.println("Vehiculo "+ idVehiculo + " ha terminado de secarse");
            semaforoLavado.release(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void simularTiempo() throws InterruptedException {
        Thread.sleep(random.nextInt(5) * 1000);
    }

    public static void main(String[] args) {
        int numVehiculos = 30;
        Thread[] vehiculos = new Thread[numVehiculos];

        for (int i = 0; i < numVehiculos; i++) {
            vehiculos[i] = new Main();
            vehiculos[i].start();
        }

        for (int i = 0; i < numVehiculos; i++) {
            try {
                vehiculos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
