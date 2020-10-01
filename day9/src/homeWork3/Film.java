package homeWork3;
import java.util.Scanner;
public class Film {
    public static void main(String[] args){
        StaffMember[] staffList = new StaffMember[4];
        staffList[0] = input(new Volunteer());
        staffList[1] = input(new Employee());
        staffList[2] = input(new Executive());
        staffList[3] = input(new Hourly());
        Staff list = new Staff(staffList);
        list.payday();

    }
    public static StaffMember input(StaffMember staff){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter staff name>>");
        String name = input.nextLine();
        System.out.print("Enter staff address>>");
        String address = input.nextLine();
        System.out.print("Enter staff number>> ");
        Long number = input.nextLong();
        input.nextLine();
        System.out.print("Enter the staff insurance>>");
        String IS = input.nextLine();
        if(staff instanceof Volunteer)
            staff = new Volunteer(name,address,number,IS);
        else if(staff instanceof Employee){
            System.out.print("Enter the salary>>");
            double salary = input.nextDouble();
            staff = new Employee(name,address,number,IS,salary);
        }
        else if(staff instanceof Executive){
            System.out.print("Enter the salary>>");
            double salary = input.nextDouble();
            staff = new Executive(name,address,number,IS,salary);
        }
        else{
            System.out.print("Enter the pay rate>>");
            double payRate = input.nextDouble();
            System.out.print("Enter the working hour>>");
            int hour = input.nextInt();
            staff = new Hourly(name,address,number,IS,hour,payRate);
        }
        return staff;
    }
}
