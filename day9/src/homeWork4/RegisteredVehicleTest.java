package homeWork4;

public class RegisteredVehicleTest {
    public static void main(String[] args)  {
        RegisteredVehicle[] vehicles = new RegisteredVehicle[2];
        vehicles[0] = new Truck("P123","John","XYZ",1200,10000,"Pickup");
        vehicles[1] = new Truck("C456","Smith","ABC",1500,8000,"Camnhong");
        try {
            vehicles[0].reRegister();
            vehicles[1].reRegister();
        }catch(RegistrationException ex){
            System.out.println(ex.getMessage());
        }
        display(vehicles);

    }
    public static void display(RegisteredVehicle[] list){
        for(RegisteredVehicle vehicle:list)
            vehicle.printRegistration();
    }
}
