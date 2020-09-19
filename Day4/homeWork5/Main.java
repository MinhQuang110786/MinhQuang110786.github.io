package homeWork5;
import java.util.Scanner;
public class Main {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        double x,y;
        System.out.println("Enter the coordinate of point");
        System.out.print("x>>");x = input.nextDouble();
        System.out.print("y>>");y = input.nextDouble();
        Point point = new Point(x,y);
        Rectangle rec = new Rectangle(new Point(0,0),10,5);
        System.out.println("Point("+point.getX()+","+point.getY()+") is " +
                (rec.checkInside(point)?"inside":"outside"));
    }
}
