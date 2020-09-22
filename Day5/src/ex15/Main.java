package ex15;

public class Main {
    public static void main(String args[]){
        Rectangle rec1 = new Rectangle(5,5,40,5);
        Rectangle rec2 = new Rectangle(7,15,35.9,3.5);
        System.out.println("The area and perimeter of rec1: "+ rec1.getArea() +" and " + rec1.getPerimeter());
        System.out.println("The area and perimeter of rec2: "+ rec2.getArea() +" and " + rec2.getPerimeter());
        System.out.println(rec1.checkInterfere(rec2));

    }
}
