package HomeWork;
import java.util.Scanner;
public class Home7 {
    public static void main(String args[]){
        double x1,y1,width1,height1;
        double x2,y2,width2,height2;
        System.out.println("Enter the rectangle 1:");
        x1 = inputValue("Enter the x>>",true);
        y1 = inputValue("Enter the y>>",true);
        width1 = inputValue("Enter the width>>",false);
        height1 = inputValue("Enter the height>>",false);

        System.out.println("Enter the rectangle 2:");
        x2 = inputValue("Enter the x>>",true);
        y2 = inputValue("Enter the y>>",true);
        width2 = inputValue("Enter the width>>",false);
        height2 = inputValue("Enter the height>>",false);

        System.out.println(check(x1,y1,width1,height1,x2,y2,width2,height2));
    }
    public static double inputValue(String msg,boolean type){
        Scanner input = new Scanner(System.in);
        double value;
        if(type){
            System.out.print(msg);
            value = input.nextDouble();
        }
        else{
            while(true){
                System.out.print(msg);
                value = input.nextDouble();
                if(value>0)
                    break;
                System.out.println("Enter the value again (value greater than 0");
            }
        }
        return value;
    }

    public static String check(double x1, double y1, double width1, double height1, double x2, double y2, double width2, double height2){
        double px1 = x1 + width1/2;
        double py1 = y1 + height1/2;
        double px2 = x2 + width2/2;
        double py2 = y2 + height2/2;
        String res = "";
        double lenX = Math.abs(x1-x2);
        double lenY = Math.abs(y1-y2);
        if(lenX>width1/2+width2/2 || lenY >height1/2+height2/2)
            res = "Rectangle 1 outside Rectangle 2";
        else if(lenX<=width1/2+width2/2 && lenY <= height1/2 + height2/2)
            res = "Rectangle 1 intersect Rectangle 2";
        else if(lenX<width1/2 && lenY<height1/2)
            res = "Rectangle 1 contains Rectangle 2";
        else if(lenX<width2/2 && lenY<height2/2)
            res = "Rectangle 2 contains Rectangle 1";
        return res;
    }



}
