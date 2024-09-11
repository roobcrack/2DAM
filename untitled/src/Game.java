public class Game {
    public void play(){
        Dice dice = new Dice();

        if(dice.matchDice()){
            System.out.println("SAME");
        } else{
            System.out.println("DIFFERENT");
        }
    }
}
