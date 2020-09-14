package HomeWork;
import java.util.Scanner;
public class Home5 {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        double x,y;
        System.out.print("Enter the coordinate x>>");
        x = input.nextDouble();
        System.out.print("Enter the coordinate y>>");
        y = input.nextDouble();
        System.out.println("(" + x + "," + y +") is " + (checkPoint(x,y)?"inside":"not inside") + " the rectangle (0,0) width 10 length 10 width 5");
    }

    public static boolean checkPoint(double x, double y){
        boolean inside = false;
        if(x>=0 && x<=10  && y>=0 && y<=5)
            inside = true;
        return inside;
    }
}
