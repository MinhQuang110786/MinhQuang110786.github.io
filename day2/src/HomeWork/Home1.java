package HomeWork;
import java.util.Scanner;
public class Home1 {
    public static void main(String[] args) {
        int year;
        year = inputYear();
        System.out.println(year + " is "+ (isLeapYear(year)?"a leap year":"not a leap year"));
    }
    public static boolean isLeapYear(int year){
        return year%4==0 && year%100!=0 || year%400==0;
    }
    public static int inputYear(){
        Scanner input = new Scanner(System.in);
        int year=0;
        while(true){
            System.out.print("Enter the year>>");
            year = input.nextInt();
            if(year>=0)
                break;
            System.out.println("Invalid input. Please enter the year positive");
        }
        return year;
    }
}

