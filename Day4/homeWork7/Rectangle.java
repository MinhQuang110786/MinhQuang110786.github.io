package homeWork7;

public class Rectangle {
    private double x,y,length,width;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    Rectangle(double x, double y, double length, double width){
        this.x = x;
        this.y = y;
        this.length = length;
        this.width = width;
    }

    public String checkIntersect(Rectangle rec){
        Point point1 = new Point((this.getX()+this.getLength())/2,(this.getY()+this.getWidth())/2);
        Point point2 = new Point((rec.getX()+rec.getWidth()/2),(rec.getY()+rec.getLength()/2));
        String res = "";
        double lenX = Math.abs(point1.getX()-point2.getX());
        double lenY = Math.abs(point1.getY()-point2.getY());
        if(lenX>getLength()/2+rec.getLength()/2 || lenY >getWidth()/2+rec.getWidth()/2)
            res = "Rectangle 1 outside Rectangle 2";
        else if(lenX<=getLength()/2+rec.getLength()/2 && lenY <= this.getWidth()/2 + rec.getWidth()/2)
            res = "Rectangle 1 intersect Rectangle 2";
        else if(lenX<getLength()/2 && lenY<getWidth()/2)
            res = "Rectangle 1 contains Rectangle 2";
        else if(lenX<rec.getLength()/2 && lenY<rec.getWidth()/2)
            res = "Rectangle 2 contains Rectangle 1";
        return res;
    }

}
