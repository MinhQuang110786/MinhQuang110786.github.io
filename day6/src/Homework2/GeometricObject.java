package Homework2;

import java.util.Date;

abstract  public class GeometricObject{
    private String color;
    private boolean filled;
    private java.util.Date dateCreated;
    public GeometricObject(){
        dateCreated = new java.util.Date();
    }

    public GeometricObject(String color, boolean filled) {
        dateCreated = new java.util.Date();
        this.color = color;
        this.filled = filled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString(){
        return "Object created "+ dateCreated.toString()+ " have color " + color + (isFilled()?" is filled": " not filled");
    }


    abstract public GeometricObject max(GeometricObject object);
}
