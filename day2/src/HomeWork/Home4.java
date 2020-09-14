package HomeWork;
import java.util.Scanner;
public class Home4 {
    public static void main(String[] args){
        double[] sides = new double[3];
        sides = inputSides();
        System.out.println("The perimeter of the triangle: " +  getPerimeter(sides));
    }
    public static double[] inputSides(){
        double[] sides = new double[3];
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.print("Enter the side 1>>");
            sides[0] = input.nextDouble();
            System.out.print("Enter the side 2>>");
            sides[1] = input.nextDouble();
            System.out.print("Enter the side 3>>");
            sides[2] = input.nextDouble();
            if(sides[0]+sides[1]>sides[2] && sides[1] + sides[2]>sides[0] && sides[2]+ sides[0]>sides[1]
            && sides[0]>0 && sides[1]>0 && sides[2]>0)
                break;
            System.out.println("Please enter the sides again (the sum of two sides is larger than the remain side length or sides is positive)");
        }
        return sides;
    }
    public static double getPerimeter(double[] sides){
        double sum = 0;
        for (double side: sides) {
            sum+=side;
        }
        return sum;
    }
}
