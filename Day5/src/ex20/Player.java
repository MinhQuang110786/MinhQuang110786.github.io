package ex20;
import java.util.Scanner;
public class Player {
    static final String[] selections = {"ROCK","PAPER","SCISSORS"};
    private int score;
    private String choice;
    private char type;
    public Player(char type){
        score = 0;
        this.type = type;

    }
    public void setSelection(){
        if(type=='H'){
            Scanner input = new Scanner(System.in);
            while(true){
                System.out.print("Enter your choice(0:ROCK;1:PAPER;2:SCISSORS)>>");
                int index = input.nextInt();
                if(index>=0&& index<=2){
                    choice = selections[index];
                    break;
                }
                System.out.println("Invalid choice, choose again!");
            }
        }else{
            int index = (int)(Math.random()*3);
            choice = selections[index];
        }
    }

    public String getSelection(){
        return choice;
    }
    public String compare(Player player2){
        String res ="";
        if(choice.equals(player2.choice)){

            res+="Game is draw";
        }

        else if(choice.equals("ROCK")){
            if(player2.choice.equals("PAPER")){

                player2.score++;
                res+=player2.type + " wins";
            }
            else{
                score++;
                res+=this.type + " wins";
            }
        }
        else if(choice.equals("PAPER")){
            if(player2.choice.equals("SCISSORS")){

                player2.score++;
                res+=player2.type + " wins";
            }
            else{
                score++;
                res+=this.type + " wins";
            }
        }
        else{
            if(player2.choice.equals("ROCK")){

                player2.score++;
                res+=player2.type + " wins";
            }
            else{
                score++;
                res+=this.type + " wins";
            }
        }
        return res;
    }
    public int getScore(){
        return score;
    }

}
