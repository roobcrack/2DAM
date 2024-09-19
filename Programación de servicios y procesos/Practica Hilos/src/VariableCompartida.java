public class VariableCompartida extends Thread{
    private static int contador = 0;

    @Override
    public void run() {
        do{
            contador++;
        } while(contador < 1000000);
    }

    @Override
    public String toString() {
        return String.valueOf(contador);
    }
}
