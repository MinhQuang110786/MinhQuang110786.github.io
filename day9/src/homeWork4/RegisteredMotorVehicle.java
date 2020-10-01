package homeWork4;
import java.util.Scanner;
public abstract class RegisteredMotorVehicle extends RegisteredVehicle{

    private double emptyWeight;
    private double lastOdometer;

    public RegisteredMotorVehicle(String tag, String registeredTo, String owner, double emptyWeight, double lastOdometer) {
        super(tag, registeredTo, owner);
        this.emptyWeight = emptyWeight;
        this.lastOdometer = lastOdometer;
    }

    public double getEmptyWeight() {
        return emptyWeight;
    }

    public double getLastOdometer() {
        return lastOdometer;
    }

    @Override
    public void reRegister() throws RegistrationException {
        super.reRegister();
        double odoMeter;
        Scanner input = new Scanner(System.in);
        System.out.print("The lastOdometer is>>");
        odoMeter = input.nextDouble();
        if(odoMeter<lastOdometer)
            throw new RegistrationException("Invalid odoMeter");
        lastOdometer = odoMeter;
    }

    @Override
    public void printRegistration(){
        super.printRegistration();
        System.out.println("with lasOdometer: " + lastOdometer + " and emptyWeight: " + emptyWeight);
    }

}
