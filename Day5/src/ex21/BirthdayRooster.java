package ex21;
import java.util.*;
public class BirthdayRooster {
    public static void main(String args[]){
        ArrayList<Employee> list = userInput();
        list.forEach(employee->{
            int year = employee.getBirthDate().get(Calendar.YEAR);
            int month = employee.getBirthDate().get(Calendar.MONTH)+1;
            int day = employee.getBirthDate().get(Calendar.DATE)+1;
            String weekDay="";
            switch(Date.dayOfWeek(day,month,year)){
                case 0: weekDay = "Saturday"; break;
                case 1: weekDay = "Sunday"; break;
                case 2: weekDay = "Monday"; break;
                case 3: weekDay = "Tuesday"; break;
                case 4: weekDay = "Wednesday"; break;
                case 5: weekDay = "Thursday"; break;
                case 6: weekDay = "Friday"; break;
            }
            System.out.println(employee+" (" + weekDay + ")");

        });

    }
    public static ArrayList<Employee> userInput(){
        ArrayList<Employee> list = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int ct=0;
        while(true){
            ct++;
            System.out.println("Enter the information of employee " +ct);
            System.out.print("Enter the department>>");
            String dept = input.nextLine();
            if(dept.charAt(0)=='q')
                break;
            else{
                System.out.print("Enter the name>>");
                String name = input.nextLine();
                System.out.print("Enter the birthday>>");
                int year = input.nextInt();
                int month = input.nextInt();
                int day = input.nextInt();
                list.add(new Employee(dept,name,year,month-1,day));
                input.nextLine();
            }

        }
        return list;
    }
}
