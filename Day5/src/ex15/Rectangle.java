package ex15;

public class Rectangle {
    private double centerX;
    private double centerY;
    private double width=1.0;
    private double height=1.0;
    public Rectangle(double centerX,double centerY,double width,double height){
        this.centerX = centerX;
        this.centerY = centerY;
        this.width = width;
        this.height = height;
    }
    public double getArea(){
        return width*height;
    }
    public double getPerimeter(){
        return 2*(width+height);
    }
    public String checkInterfere(Rectangle rec2){
        String res="";
        if(Math.abs(centerX-rec2.centerX)<=(width-rec2.width)/2 && Math.abs(centerY-rec2.centerY)<=(height-rec2.height)/2){
            res+="Rectangle 2 inside Rectangle 1";
        }else if(Math.abs(centerX-rec2.centerX)<=(rec2.width-width)/2 && Math.abs(centerY-rec2.centerY)<=(rec2.height)/2-height){
            res+="Rectangle 2 inside Rectangle 1";
        }else if(Math.abs(centerX-rec2.centerX)<=(width+rec2.width)/2 && Math.abs(centerY-rec2.centerY)<=(height+rec2.height)/2){
            res+="Rectangle 1 interfere with Rectangle 2";
        } else
        {
            res+="Rectangle1 outside Rectangle 2";
        }
        return res;
    }
}
