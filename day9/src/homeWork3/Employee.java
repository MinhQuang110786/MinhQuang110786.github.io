package homeWork3;

public class Employee extends StaffMember{
    private double salary;
    private double incentive;
    public Employee(){}
    public Employee(String name, String address, long phone, String IS, double salary) {
        super(name, address, phone, IS);
        this.salary = salary;
    }

    public void setIncentive(double incentive) {
        this.incentive = incentive;
    }

    @Override
    public double pay() {
        return salary+incentive;
    }
}
