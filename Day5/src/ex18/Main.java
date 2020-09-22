package ex18;
import java.util.Scanner;
public class Main {
    public static void main(String args[]){
        System.out.println("Enter the factor of quadratic solution>>");
        double a, b, c;
        Scanner input = new Scanner(System.in);
        a = input.nextDouble();
        b = input.nextDouble();
        c = input.nextDouble();
        QuadraticEquation equation = new QuadraticEquation(a,b,c);
        equation.solveEquation();
    }
}
