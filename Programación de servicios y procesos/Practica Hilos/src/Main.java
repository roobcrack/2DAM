public class Main extends Thread {
    public static void main(String[] args) {
        int numHilos = 8;
        Thread[] hilos = new Thread[numHilos];

        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new VariableCompartida();
            hilos[i].start();
        }
        for (int i = 0; i < numHilos; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
        System.out.println("Valor final del contador (sin sincronización): " + hilos[0].toString());
    }
}
