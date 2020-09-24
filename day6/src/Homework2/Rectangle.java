package Homework2;

public class Rectangle extends GeometricObject implements Comparable<GeometricObject>{
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle(String color, boolean filled, double width, double height) {
        super(color, filled);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea(){
        return width*height;
    }
    public double getPerimeter(){
        return 2*(width+height);
    }
    @Override
    public int compareTo(GeometricObject ob2){
        Rectangle rec2 = (Rectangle)ob2;
        if(getArea()<rec2.getArea())
            return -1;
        else if(getArea()==rec2.getArea())
            return 0;
        else
            return 1;
    }

    @Override
    public GeometricObject max(GeometricObject ob2){
        return this.compareTo(ob2)>-1?this:ob2;
    }
}
