import java.util.Random;

public class GasolineraGasolina extends Thread{
    private static int vehiculos = 0;
    private Random rd = new Random();

    public GasolineraGasolina(int vehiculos){
        this.vehiculos = vehiculos;
    }

    public int getVehiculos(){ return vehiculos; }

    @Override
    public void run(){
        try {
            Thread.sleep(rd.nextInt(2000)+500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        vehiculos--;
    }
}
