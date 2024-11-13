import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

class CuentaCompartida {
    protected int saldo;
    protected Semaphore semaforo = new Semaphore(1);

    public CuentaCompartida(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public int getSaldo() {
        return saldo;
    }

    protected void actualizarSaldo(int cantidad) {
        saldo += cantidad;
    }
}

class Ingresar extends CuentaCompartida implements Callable<String> {
    private int cantidad;
    private int id;

    public Ingresar(CuentaCompartida cuenta, int cantidad, int id) {
        super(cuenta.getSaldo());
        this.cantidad = cantidad;
        this.id = id;
    }

    @Override
    public String call() throws InterruptedException {
        System.out.printf("Hilo %d: Iniciando ingreso de %d unidades.%n", id, cantidad);
        semaforo.acquire(); // Adquirir el semáforo para la operación
        Thread.sleep(500); // Simular el tiempo de ingreso
        actualizarSaldo(cantidad);
        semaforo.release(); // Liberar el semáforo
        System.out.printf("Hilo %d: Ingreso de %d unidades completado. Saldo actual: %d%n", id, cantidad, getSaldo());
        return "Ingreso exitoso";
    }
}

class Retirar extends CuentaCompartida implements Callable<String> {
    private int cantidad;
    private int id;

    public Retirar(CuentaCompartida cuenta, int cantidad, int id) {
        super(cuenta.getSaldo());
        this.cantidad = cantidad;
        this.id = id;
    }

    @Override
    public String call() throws InterruptedException {
        System.out.printf("Hilo %d: Iniciando retiro de %d unidades.%n", id, cantidad);
        semaforo.acquire(); // Adquirir el semáforo para la operación
        Thread.sleep(500); // Simular el tiempo de retiro
        if (getSaldo() >= cantidad) {
            actualizarSaldo(-cantidad);
            System.out.printf("Hilo %d: Retiro de %d unidades completado. Saldo actual: %d%n", id, cantidad, getSaldo());
            semaforo.release(); // Liberar el semáforo
            return "Retiro exitoso";
        } else {
            semaforo.release(); // Liberar el semáforo
            System.out.printf("Hilo %d: No se pudo retirar %d unidades. Saldo insuficiente: %d%n", id, cantidad, getSaldo());
            return "Retiro fallido: saldo insuficiente";
        }
    }
}

class Consultar extends CuentaCompartida implements Callable<String> {
    private int id;

    public Consultar(CuentaCompartida cuenta, int id) {
        super(cuenta.getSaldo());
        this.id = id;
    }

    @Override
    public String call() throws InterruptedException {
        System.out.printf("Hilo %d: Consultando saldo.%n", id);
        Thread.sleep(2000); // Simular el tiempo de consulta
        System.out.printf("Hilo %d: Saldo actual: %d%n", id, getSaldo());
        return "Consulta exitosa";
    }
}

public class Main {
    public static void main(String[] args) {
        int saldoInicial = 1000; // Saldo inicial de la cuenta
        CuentaCompartida cuenta = new CuentaCompartida(saldoInicial);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Random random = new Random();
        Future<String>[] resultados = new Future[200];

        for (int i = 0; i < 200; i++) {
            int tipoOperacion = random.nextInt(10); // 0-9, 10% ingreso, 10% retiro, 80% consulta
            int id = i + 1; // ID del hilo
            try {
                if (tipoOperacion < 2) { // 20% de operaciones de ingreso o retiro
                    int cantidad = random.nextInt(100) + 1; // Cantidad entre 1 y 100
                    if (tipoOperacion == 0) {
                        resultados[i] = executor.submit(new Ingresar(cuenta, cantidad, id));
                    } else {
                        resultados[i] = executor.submit(new Retirar(cuenta, cantidad, id));
                    }
                } else { // 80% consultas
                    resultados[i] = executor.submit(new Consultar(cuenta, id));
                }
                Thread.sleep(400); // Esperar 0.4 segundos antes de crear el siguiente hilo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Esperar los resultados de las operaciones
        for (int i = 0; i < 200; i++) {
            try {
                if (resultados[i] != null) {
                    String resultado = resultados[i].get();
                    System.out.printf("Resultado del hilo %d: %s%n", i + 1, resultado);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown(); // Cerrar el executor
    }
}
