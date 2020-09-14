package HomeWork;
import java.util.Scanner;
public class Home2 {
    public static void main(String args[]){
        int number;
        number = inputYear();
        System.out.println("Năm đó là năm : " + checkYear(number));
    }
    public static String checkYear(int number){
        String res="";
        switch(number){
            case 0:
                res =  "Tý";break;
            case 1:
                res = "Sửu";break;
            case 2:
                res = "Dần";break;
            case 3:
                res = "Mão";break;
            case 4:
                res = "Thìn";break;
            case 5:
                res = "Tỵ";break;
            case 6:
                res = "Ngọ";break;
            case 7:
                res = "Mùi";break;
            case 8:
                res = "Thân";break;
            case 9:
                res = "Dậu";break;
            case 10:
                res = "Tuất";break;
            case 11:
                res = "Hợi";break;
        }
        return res;
    }
    public static int inputYear(){
        int number=0;
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.print("Enter the number>>");
            number = input.nextInt();
            if(number>=1 && number<=11)
                break;
            System.out.println("Invalid input. Please enter the number between 1 and 11");
        }
        return number;
    }
}
