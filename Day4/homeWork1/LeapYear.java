package homeWork1;
import java.util.Scanner;
public class LeapYear {
    private int year;
    public LeapYear(){
        int y = 0;
        while(true){
            System.out.print("Enter your year>>");
            Scanner input = new Scanner(System.in);
            y = input.nextInt();
            if(y>0)
                break;
        }
        year = y;
    }

    public boolean checkLeap(){
        return (year%4==0 && year%100!=0) || year%400==0;
    }
    public int getYear(){
        return year;
    }
}
