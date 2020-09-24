package Homework3;

public class ComparableCircle extends Circle implements Comparable<Circle>{
    public ComparableCircle(double radius){
        super(radius);
    }
    public ComparableCircle(double radius, String color, boolean filled){
        super(radius,color,filled);
    }
    @Override
    public int compareTo(Circle circle2){
        if(this.getArea()<circle2.getArea())
            return -1;
        else if(this.getArea() == circle2.getArea())
            return 0;
        else
            return 1;
    }
    public Circle getMax(Circle circle2){
        return this.compareTo(circle2)>-1?this:circle2;
    }
}
