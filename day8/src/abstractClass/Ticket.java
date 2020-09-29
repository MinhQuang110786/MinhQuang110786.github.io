package abstractClass;

import java.util.ArrayList;
abstract public class Ticket {
    private int balance;
    protected boolean valid = true;
    protected ArrayList<Gate> gates;
    protected  int fee = 0;
    public Ticket(int balance){
        this.balance = balance;
        gates = new ArrayList<>();
    }
    public int getValue(){
        return balance;
    }
    abstract public void adjustValue();
    public void deduct(int fee){
        if(balance>fee)
            balance-=fee;
    }

    public int getFee(){
        return fee;
    }
    public void setOrigin(Gate gate){
        if(gate!=null){
            gates.add(gate);
        }
        else{
            valid = false;
        }
    }
    public ArrayList<Gate> getOrigin() {
        return gates;
    }
    abstract public boolean isValid();
}
