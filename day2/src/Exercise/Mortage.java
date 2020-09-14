package Exercise;
import java.util.Scanner;
public class Mortage {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        double principal,annualRate,mortgage,monthlyRate;
        int year,periods;
        principal = readInput("Enter the principal>>",1000,1000000,"Invalid input, enter the value " +
                "between 1000 and 1000000");


        annualRate = readInput("Enter the annualRate>>",0,30,"Invalid input, enter the value between 0 and 30");

        year = (int)readInput("Enter the year>>",0,30,"Invalid input, enter the value between 0 and 30");
        monthlyRate = annualRate/12/1000;
        periods = year*12;
        mortgage = principal * Math.pow((1+monthlyRate),periods)/(Math.pow((1+monthlyRate),periods)-1);
        System.out.printf("Your monthly payment: $%.2f\n",mortgage);

    }

    public static double readInput(String msg, double min, double max, String err){
        Scanner input = new Scanner(System.in);
        double value;
        while(true){
            System.out.print(msg);
            value = input.nextDouble();
            if(value>=min && value<=max)
                break;
            System.out.println("Your input not valid. Your principal must between 1 and 1000");
        }
        return value;
    }

}
