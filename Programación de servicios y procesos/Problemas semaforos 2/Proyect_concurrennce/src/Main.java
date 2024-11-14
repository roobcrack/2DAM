import java.util.Random;
import java.util.concurrent.*;

class CuentaCompartida {
    private int saldo;
    private final Semaphore semaforoExclusivo = new Semaphore(1);
    private final Semaphore semaforoConsulta = new Semaphore(1);
    private int consultasActivas = 0;

    public CuentaCompartida(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public int getSaldo() {
        return saldo;
    }

    protected void actualizarSaldo(int cantidad) {
        saldo += cantidad;
    }

    public void iniciarConsulta() throws InterruptedException {
        semaforoConsulta.acquire();
        synchronized (this) {
            consultasActivas++;
            if (consultasActivas == 1) {
                semaforoExclusivo.acquire();
            }
        }
        semaforoConsulta.release();
    }

    public void finalizarConsulta() throws InterruptedException {
        synchronized (this) {
            consultasActivas--;
            if (consultasActivas == 0) {
                semaforoExclusivo.release();
            }
        }
    }

    public void iniciarOperacionExclusiva() throws InterruptedException {
        semaforoExclusivo.acquire();
    }

    public void finalizarOperacionExclusiva() {
        semaforoExclusivo.release();
    }
}

class Ingresar implements Callable<String> {
    private final CuentaCompartida cuenta;
    private final int cantidad;
    private final int id;

    public Ingresar(CuentaCompartida cuenta, int cantidad, int id) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.id = id;
    }

    @Override
    public String call() throws InterruptedException {
        System.out.printf("Hilo %d: Iniciando ingreso de %d unidades.%n", id, cantidad);
        cuenta.iniciarOperacionExclusiva();
        Thread.sleep(500); // Simular tiempo de ingreso
        cuenta.actualizarSaldo(cantidad);
        System.out.printf("Hilo %d: Ingreso de %d unidades completado. Saldo actual: %d%n", id, cantidad, cuenta.getSaldo());
        cuenta.finalizarOperacionExclusiva();
        return "Ingreso exitoso";
    }
}

class Retirar implements Callable<String> {
    private final CuentaCompartida cuenta;
    private final int cantidad;
    private final int id;

    public Retirar(CuentaCompartida cuenta, int cantidad, int id) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.id = id;
    }

    @Override
    public String call() throws InterruptedException {
        System.out.printf("Hilo %d: Iniciando retiro de %d unidades.%n", id, cantidad);
        cuenta.iniciarOperacionExclusiva();
        Thread.sleep(500); // Simular tiempo de retiro
        if (cuenta.getSaldo() >= cantidad) {
            cuenta.actualizarSaldo(-cantidad);
            System.out.printf("Hilo %d: Retiro de %d unidades completado. Saldo actual: %d%n", id, cantidad, cuenta.getSaldo());
            cuenta.finalizarOperacionExclusiva();
            return "Retiro exitoso";
        } else {
            System.out.printf("Hilo %d: No se pudo retirar %d unidades. Saldo insuficiente: %d%n", id, cantidad, cuenta.getSaldo());
            cuenta.finalizarOperacionExclusiva();
            return "Retiro fallido: saldo insuficiente";
        }
    }
}

class Consultar implements Callable<String> {
    private final CuentaCompartida cuenta;
    private final int id;

    public Consultar(CuentaCompartida cuenta, int id) {
        this.cuenta = cuenta;
        this.id = id;
    }

    @Override
    public String call() throws InterruptedException {
        System.out.printf("Hilo %d: Consultando saldo.%n", id);
        cuenta.iniciarConsulta();
        Thread.sleep(2000); // Simular tiempo de consulta
        System.out.printf("Hilo %d: Saldo actual: %d%n", id, cuenta.getSaldo());
        cuenta.finalizarConsulta();
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
            int tipoOperacion = random.nextInt(10); // 0-9, 20% ingreso/retiro, 80% consulta
            int id = i + 1;
            try {
                if (tipoOperacion < 2) { // 20% operaciones de ingreso/retiro
                    int cantidad = random.nextInt(100) + 1;
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

        executor.shutdown();
    }
}
