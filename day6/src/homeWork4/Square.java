package homeWork4;

public class Square extends GeometricObject implements Colorable{
    private double size;
    public Square(double size){
        this.size = size;
    }
    public Square(double size, String color, boolean filled){
        super(color, filled);
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getArea(){
        return size*size;
    }
    public double getPerimeter(){
        return size*4;
    }
    @Override
    public void howtoColor(){
        System.out.println("Four side is color "+ this.getColor() );
    }
}
