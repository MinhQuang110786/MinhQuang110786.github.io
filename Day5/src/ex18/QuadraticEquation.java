package ex18;

public class QuadraticEquation {
    private double a,b,c;
    public QuadraticEquation(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public void solveEquation(){
        if(a==0){
            if(b==0){
                if(c==0)
                    System.out.println("The equation have infinite solutions");
                else
                    System.out.println("The equation have none solution");
            }
            else{
                System.out.println("The equation have one solution:" + (-1)*c/b);
            }
        }
        else{
            double Delta = b*b-4*a*c;
            if(Delta>0){
                System.out.println("The equation have 2 solutions");
                System.out.println("X1="+ ((-1)*b+Math.sqrt(Delta))/(2*a));
                System.out.println("X2="+ ((-1)*b-Math.sqrt(Delta))/(2*a));
            }
            else if(Delta==0){
                System.out.println("The equation have 1 solution");
                System.out.println("X="+(-1*b)/(2*a));
            }
            else{
                System.out.println("The equation have 2 image solution");
                System.out.println("X1="+(-1*b)/(2*a) + " + " + Math.sqrt(-1*Delta)/(2*a)+"i");
                System.out.println("X2="+(-1*b)/(2*a) + " - " + Math.sqrt(-1*Delta)/(2*a)+"i");
            }
        }
    }
}
