package Homework2;

public class Main {
    public static void main(String args[]){
       Circle circle1 = new Circle(5.5,"blue",true);
       Circle circle2 = new Circle(4.5,"orange",false);
       circle1.printCircle();
       circle2.printCircle();
       System.out.println("The max circle is: ");
       Circle maxCircle = (Circle)circle1.max(circle2);
       maxCircle.printCircle();
       System.out.println("******************");
       Rectangle rec1 = new Rectangle("red",true,4.5,5.5);
       Rectangle rec2 = new Rectangle("blue",false,5.5,6.5);
       Rectangle maxRect = (Rectangle)rec1.max(rec2);
       System.out.print(maxRect.toString() + "\n" + "The max rectangle ("+maxRect.getWidth() + ","
       +maxRect.getHeight()+") have area: " + maxRect.getArea() + " and perimeter: " + maxRect.getPerimeter());
    }
}
