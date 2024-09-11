import java.util.Random;

public class Dice {
    private Random random;

    public Dice(){
        this.random = new Random();
    }

    public int throwDice(){
        return random.nextInt(6)+1;
    }
    public boolean matchDice(){
        return throwDice()==throwDice();
    }
}
