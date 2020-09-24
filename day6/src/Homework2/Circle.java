package Homework2;

public class Circle extends GeometricObject implements Comparable<GeometricObject>{
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



    @Override
    public int compareTo(GeometricObject object) {
        Circle circle2 = (Circle)object;
        if(radius< circle2.getRadius())
            return -1;
        else if (radius == circle2.getRadius())
            return 0;
        else
            return 1;
    }

    @Override
    public GeometricObject max(GeometricObject ob2){
        return this.compareTo(ob2)>-1?this:ob2;
    }

    public void printCircle(){
        System.out.println(super.toString());
        System.out.println("Circle have radius: "+ radius + " with area: "+ getArea() + " perimeter: "+ getPerimeter());
    }
}
