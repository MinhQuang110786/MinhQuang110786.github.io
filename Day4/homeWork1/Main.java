package homeWork1;

public class Main {
    public static void main(String args[]){
        LeapYear year = new LeapYear();
        System.out.println(year.getYear() + " is " + (year.checkLeap()?"a leap year": "not a leap year"));
    }
}
