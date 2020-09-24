package Homework3;

public class Main {
    public static void main(String args[]){
        ComparableCircle circle1 = new ComparableCircle(5.5,"blue",true);
        ComparableCircle circle2 = new ComparableCircle(5.5,"yellow",false);
        System.out.println("Printing the circle");
        circle1.printCircle();
        circle2.printCircle();
        System.out.println("****************");
        System.out.println("The max circle:");
        ComparableCircle maxCircle = (ComparableCircle)circle1.getMax(circle2);
        maxCircle.printCircle();
    }

}
