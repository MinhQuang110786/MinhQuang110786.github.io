package homeWork7;
import java.util.Scanner;
public class Main {
    public static void main(String args[]){
        Rectangle rec1,rec2;
        System.out.println("Enter the rectangle 1>>");
        rec1 = inputRectangle();
        System.out.println("Enter the rectangle 2>>");
        rec2 = inputRectangle();
        System.out.println(rec1.checkIntersect(rec2));
    }

    public static Rectangle inputRectangle(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the coordinate:");
        double x,y;
        x = input.nextDouble();
        y = input.nextDouble();
        System.out.print("Enter the length and the width:");
        double length, width;
        while(true){
            length = input.nextDouble();
            width = input.nextDouble();
            if(length>0 && width>0)
                break;
            System.out.println("Length and width must positive, enter again>>");
        }

        return new Rectangle(x,y,length,width);
    }
}
