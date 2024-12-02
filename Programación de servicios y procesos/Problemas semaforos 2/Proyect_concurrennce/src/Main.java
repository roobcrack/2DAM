import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public static class CuentaCompartida {
        private double saldo = 1000;
        private int consultasActivas = 0;
        private final Semaphore operaciones = new Semaphore(1);
        private final Semaphore consultaMutex = new Semaphore(1);
        private final Semaphore bloq = new Semaphore(1);

        public boolean ingresar(double ingreso, int id) throws InterruptedException {
            bloq.acquire(1);
            operaciones.acquire(1);
            try {
                System.out.printf("(I) %d: Iniciando ingreso de %.2f€.%n", id, ingreso);
                Thread.sleep(500);
                saldo += ingreso;
                System.out.printf("(I) %d: Saldo actualizado tras ingreso: %.2f€.%n", id, saldo);
                return true;
            } finally {
                System.out.printf("(I) %d: Ingreso finalizado.%n", id);
                operaciones.release(1);
                bloq.release(1);
            }
        }

        public boolean retirar(double retiro, int id) throws InterruptedException {
            bloq.acquire(1);
            operaciones.acquire(1);
            try {
                System.out.printf("(R) %d: Iniciando retiro de %.2f€.%n", id, retiro);
                if (saldo >= retiro) {
                    Thread.sleep(500);
                    saldo -= retiro;
                    System.out.printf("(R) %d: Saldo actualizado tras retiro: %.2f€.%n", id, saldo);
                    return true;
                } else {
                    System.out.printf("(R) %d: Saldo insuficiente para retirar %.2f€. Saldo actual: %.2f€.%n", id, retiro, saldo);
                    return false;
                }
            } finally {
                System.out.printf("(R) %d: Retiro finalizado.%n", id);
                operaciones.release(1);
                bloq.release(1);
            }
        }

        public double consultar(int id) throws InterruptedException {
            bloq.acquire(1);
            consultaMutex.acquire(1);
            try {
                if (consultasActivas == 0) {
                    operaciones.acquire(1);
                }
                consultasActivas++;
            } finally {
                consultaMutex.release(1);
                bloq.release(1);
            }

            try {
                System.out.printf("(C) %d: Consultando saldo.%n", id);
                Thread.sleep(2000);
                System.out.printf("(C) %d: Saldo consultado: %.2f€.%n", id, saldo);
                return saldo;
            } finally {
                consultaMutex.acquire(1);
                try {
                    consultasActivas--;
                    if (consultasActivas == 0) {
                        operaciones.release(1);
                    }
                } finally {
                    consultaMutex.release(1);
                }
                System.out.printf("(C) %d: Consulta finalizada.%n", id);
            }
        }
    }

    public static abstract class Operacion implements Callable<Boolean> {
        protected final CuentaCompartida cuenta;
        protected final int id;

        public Operacion(CuentaCompartida cuenta, int id) {
            this.cuenta = cuenta;
            this.id = id;
        }
    }

    public static class Ingreso extends Operacion {
        private final double cantidad;

        public Ingreso(CuentaCompartida cuenta, int id, double cantidad) {
            super(cuenta, id);
            this.cantidad = cantidad;
        }

        @Override
        public Boolean call() throws Exception {
            return cuenta.ingresar(cantidad, id);
        }
    }

    public static class Retiro extends Operacion {
        private final double cantidad;

        public Retiro(CuentaCompartida cuenta, int id, double cantidad) {
            super(cuenta, id);
            this.cantidad = cantidad;
        }

        @Override
        public Boolean call() throws Exception {
            return cuenta.retirar(cantidad, id);
        }
    }

    public static class Consulta extends Operacion {

        public Consulta(CuentaCompartida cuenta, int id) {
            super(cuenta, id);
        }

        @Override
        public Boolean call() throws Exception {
            cuenta.consultar(id);
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Random rd = new Random();

        CuentaCompartida cuenta = new CuentaCompartida();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<Boolean>> futuros = new ArrayList<>();

        for (int i = 1; i <= 200; i++) {
            Thread.sleep(rd.nextInt(400));

            int selec = rd.nextInt(10);
            if (selec < 1) {
                futuros.add(executor.submit(new Ingreso(cuenta, i, rd.nextInt(500) + 100)));
            } else if (selec < 2) {
                futuros.add(executor.submit(new Retiro(cuenta, i, rd.nextInt(500) + 100)));
            } else {
                futuros.add(executor.submit(new Consulta(cuenta, i)));
            }
        }

        for (Future<Boolean> futuro : futuros) {
            try {
                futuro.get();
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        executor.shutdown();
    }
}
