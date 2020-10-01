package homeWork4;

public class Truck extends  RegisteredMotorVehicle{
    private String type;

    public Truck(String tag, String registeredTo, String owner, double emptyWeight, double lastOdometer, String type) {
        super(tag, registeredTo, owner, emptyWeight, lastOdometer);
        this.type = type;
    }

    @Override
    public void printRegistration(){
        super.printRegistration();
        System.out.println("and annual fee: $" + getAnnualFee());
    }
    @Override
    public double getAnnualFee() {
        if(type.equals("Pickup"))
            return getEmptyWeight()*0.05;
        else if(type.equals("Camnhong"))
            return getEmptyWeight()*0.01;
        else
            return getEmptyWeight()*0.1;
    }
}
