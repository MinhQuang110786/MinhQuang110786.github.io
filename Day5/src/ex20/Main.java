package ex20;

public class Main {
    public static void main(String args[]){
        Player human,computer;
        human = new Player('H');
        computer = new Player('C');
        for(int i=0;i<3;i++){
            human.setSelection();
            computer.setSelection();
            System.out.println("Your selection: " + human.getSelection());
            System.out.println("Computer selection: " + computer.getSelection());
            System.out.println(human.compare(computer));
            System.out.println("**********************");
        }
        System.out.println("Your score: " + human.getScore());
        System.out.println("Computer score: " + computer.getScore());
        System.out.println(human.getScore()>computer.getScore()?"human wins":human.getScore()== computer.getScore()?
                "Game draw":"Computer wins");
    }
}
