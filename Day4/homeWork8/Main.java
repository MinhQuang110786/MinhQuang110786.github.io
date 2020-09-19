package homeWork8;
import java.util.Scanner;
public class Main {
    public static void main(String args[]){
        int birthday = 0;
        Sets set1,set2,set3,set4,set5;
        set1 = new Sets(1);
        set2 = new Sets(2);
        set3 = new Sets(3);
        set4 = new Sets(4);
        set5 = new Sets(5);
        System.out.println("Display all set");
        set1.displaySet();
        System.out.println("****************");
        set2.displaySet();
        System.out.println("****************");
        set3.displaySet();
        System.out.println("****************");
        set4.displaySet();
        System.out.println("****************");
        set5.displaySet();
        System.out.println("****************");
        System.out.println("\n********************");
        birthday+=getQuestion(set1);
        birthday+=getQuestion(set2);
        birthday+=getQuestion(set3);
        birthday+=getQuestion(set4);
        birthday+=getQuestion(set5);
        System.out.println("Your birthday is: " + birthday);
    }
    public static int getQuestion(Sets s){
        Scanner input = new Scanner(System.in);
        System.out.print("Is your birthday belong to this set>>");
        String answer = input.nextLine();
        if(Character.toUpperCase(answer.charAt(0))=='Y'){
            return s.getSet().get(0);
        }
        else
            return 0;
    }
}
