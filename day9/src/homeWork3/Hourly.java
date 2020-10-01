package homeWork3;

public class Hourly extends StaffMember{
    private int hour;
    private double payRate;
    public Hourly(){}
    public Hourly(String name, String address, long phone, String IS, int hour, double payRate) {
        super(name, address, phone, IS);
        this.hour = hour;
        this.payRate = payRate;
    }

    @Override
    public double pay() {
        return payRate*hour;
    }

    @Override
    public String toString() {
        return "Name: " + getName()
                + "\nAddress: " + getAddress()
                + "\nPhone: " + getPhone()
                + "\nInsurance number: " + getIS()
                + "\nWorking hour: " + hour
                + "\nHave to pay: " + this.pay()
                + "\n---------------------------";
    }
}
