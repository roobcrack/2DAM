import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rd = new Random(2);
        int gasolina = 0;
        int diesel = 0;

        for(int i=0; i<20; i++){
            int numero = rd.nextInt();
            if(numero==1) gasolina++;
            else diesel++;
        }
        System.out.println(gasolina);
        System.out.println(diesel);
    }
}