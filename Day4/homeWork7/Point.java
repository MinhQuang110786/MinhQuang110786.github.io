package homeWork7;

public class Point {
    private double x,y;
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double Distance(Point p2){
        return Math.sqrt(Math.pow(getX()-p2.getX(),2)+Math.pow(getY()-p2.getY(),2));
    }
}
