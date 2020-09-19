package homeWork4;
import java.util.Scanner;
public class UserInput {
    public static double getInput(){
        double num;
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.print("Enter the side>>");
            num = input.nextDouble();
            if(num>0)
                break;
            System.out.println("Enter the side again>>");
        }
        return num;
    }
    public static boolean checkValid(double side1, double side2, double side3){
        return side1+side2>side3 && side2+side3>side1 && side3+side1>side2;
    }
}
