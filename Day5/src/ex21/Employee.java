package ex21;

public class Employee extends Person{
    private static int count=0;
    private int id;
    private String department;

    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public Employee(String dept, String name, int year, int month, int day){
        super(name,year,month,day);
        count++;
        id = count;
        department = dept;
    }

    public String toString(){
        return "ID: " + getId() +
                "\n" + "Department: " + getDepartment() +
                "\n" + super.toString();
    }

}
