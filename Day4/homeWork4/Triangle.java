package homeWork4;

import java.net.UnknownServiceException;

public class Triangle {
    private double side1,side2,side3;
    public Triangle(){
        while(true){
            side1 = UserInput.getInput();
            side2 = UserInput.getInput();
            side3 = UserInput.getInput();
            if(UserInput.checkValid(side1,side2,side3))
                break;
            System.out.println("Enter the sides again");
        }
    }
    public double getPerimeter(){
        return side1 + side2 + side3;
    }

    public double getArea(){
        double p =getPerimeter()/2;
        return Math.sqrt(p*(p-side1)*(p-side2)*(p-side3));
    }
}
