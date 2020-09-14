package HomeWork;

public class Home3 {
    public static void main(String args[]){
        int number = getRandomNumber();
        System.out.println("The approritate month is: " + month(number));
    }
    public static int getRandomNumber(){
        return (int)Math.floor(Math.random()*12+1);
    }
    public static String month(int num){
        String res="";
        switch(num){
            case 1:
                res =  "Tháng một";break;
            case 2:
                res = "Tháng hai";break;
            case 3:
                res = "Tháng ba";break;
            case 4:
                res = "Tháng bốn";break;
            case 5:
                res = "Tháng năm";break;
            case 6:
                res = "Tháng sáu";break;
            case 7:
                res = "Tháng bảy";break;
            case 8:
                res = "Tháng tám";break;
            case 9:
                res = "Tháng chín";break;
            case 10:
                res = "Tháng mười";break;
            case 11:
                res = "Tháng mười một";break;
            case 12:
                res = "Tháng mười hai";break;
        }
        return res;
    }
}
