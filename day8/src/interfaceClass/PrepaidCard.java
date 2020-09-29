package interfaceClass;
import java.util.ArrayList;
public class PrepaidCard implements Payable{
    private int fee;
    private boolean valid = true;
    private int balance;
    private ArrayList<Gate> gates;
    public PrepaidCard(int balance){
        this.balance = balance;
    }
    @Override
    public int getValue(){
        return balance;
    }
    @Override
    public void adjustValue(){
        int distance = gates.get(gates.size()-1).getDistance() - gates.get(0).getDistance();
        fee = Line.getFare(distance);
        if(balance<fee)
            valid = false;
        deduct(fee);
    }
    @Override
    public void deduct(int fee){
        balance-=fee;
    }
    @Override
    public void setOrigin(Gate gate){
        if(gate!=null){
            gates.add(gate);
        }
        else{
            valid = false;
        }
    }
    @Override
    public ArrayList<Gate> getOrigin(){
        return gates;
    }
    @Override
    public boolean isValid(){
        return this.valid;
    }

}
