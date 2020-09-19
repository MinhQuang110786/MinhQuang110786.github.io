package homeWork2;
import java.util.Scanner;
public class IntegerInput {
    public static int getInput(){
        int num = -1;
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.print("Enter the integer between 1 and 11>>");
            num = input.nextInt();
            if(num>=0 && num<=11){
                System.out.println("Your input is valid");
                break;
            }
            System.out.println("Your input is not valid, please enter again");
        }
        return num;
    }
}
