package homeWork5;

import org.jetbrains.annotations.NotNull;

public class Rectangle {
    private double width,height;
    private Point origin;
    public Rectangle(Point p, double width, double height) {
        origin = p;
        this.width = width;
        this.height = height;
    }
    public boolean checkInside(@NotNull Point p){
        return p.getX()<=origin.getX()+width && p.getY()<=origin.getY()+height;
    }
}
