package homeWork3;

public class Volunteer extends StaffMember{
    public Volunteer(){}
    public Volunteer(String name, String address, long phone, String IS) {
        super(name, address, phone, IS);
    }

    @Override
    public double pay() {
        return 0;
    }
}
