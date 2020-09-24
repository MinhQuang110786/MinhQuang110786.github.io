package Homework3;

public class Circle extends GeometricObject {
    private double radius;
    public Circle(double radius){
        super();
        this.radius = radius;
    }
    public Circle(double radius, String color, boolean filled){
        super(color, filled);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea(){
        return Math.PI * radius * radius;
    }
    public double getPerimeter(){
        return Math.PI*2*radius;
    }

    public void printCircle(){
        System.out.println(super.toString());
        System.out.println("Circle have radius: "+ radius + " with area: "+ getArea() + " perimeter: "+ getPerimeter());
    }
}
