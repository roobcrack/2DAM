import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rd = new Random();
        Thread[] gasolineras = new Thread[3];
        int gasolina=0, diesel=0;

        for(int i=0; i<20; i++){
            if(rd.nextInt(2)==1)
                gasolina++;
            else
                diesel++;
        }
        gasolineras[0] = new GasolineraGasolina(gasolina);
        gasolineras[1] = new GasolineraGasolina(gasolina);
        gasolineras[2] = new GasolineraDiesel(diesel);
        
        do{
            
        } while(gasolineras[0].getId())
    }
}

