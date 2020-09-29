package interfaceClass;
import java.util.ArrayList;
public class OneWayTicket implements Payable{
    private boolean used = false;
    private ArrayList<Gate> gates;
    private int fee;
    private boolean valid = true;
    @Override
    public int getValue(){
        return 0;
    }
    @Override
    public void adjustValue(){
        int distance = gates.get(gates.size()-1).getDistance() - gates.get(0).getDistance();
        fee = Line.getFare(distance);
    }

    @Override
    public void setOrigin(Gate gate){
        if(gate!=null){
            gates.add(gate);
        }
        else{
            valid = false;
            used = true;
        }
    }
    @Override
    public void deduct(int fee){ }
    @Override
    public ArrayList<Gate> getOrigin(){
        return gates;
    }
    @Override
    public boolean isValid(){
        if(used)
            valid = false;
        return this.valid;
    }
}
