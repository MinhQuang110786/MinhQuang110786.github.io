package homeWork4;
import java.util.Scanner;
abstract public class RegisteredVehicle {
    private String tag;
    private String registeredTo;
    private String owner;

    public RegisteredVehicle(String tag, String registeredTo, String owner) {
        this.tag = tag;
        this.registeredTo = registeredTo;
        this.owner = owner;
    }

    public void reRegister() throws RegistrationException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the registered to user>>");
        registeredTo = input.nextLine();
        System.out.print("Enter the owner info>>");
        owner = input.nextLine();
    }

    public void printRegistration(){
        System.out.println("This vehicle with " + tag + " is register by " +
                registeredTo + " belong to " + owner);
    }

    public abstract double getAnnualFee();
}
