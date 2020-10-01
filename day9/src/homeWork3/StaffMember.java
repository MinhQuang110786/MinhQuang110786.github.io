package homeWork3;

public abstract class StaffMember {
    private String name;
    private String address;
    private long phone;
    private String IS;
    public StaffMember(){}
    public StaffMember(String name, String address, long phone, String IS) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.IS = IS;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public long getPhone() {
        return phone;
    }

    public String getIS() {
        return IS;
    }

    public abstract double pay();

    @Override
    public String toString() {
        return "Name: " + this.name
                + "\nAddress: " + this.address
                + "\nPhone: " + this.phone
                + "\nInsurance number: " + this.IS
                + "\nHave to pay: " + this.pay()
                +"\n----------------------------";
    }
}
