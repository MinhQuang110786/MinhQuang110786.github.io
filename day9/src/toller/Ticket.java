package toller;

abstract public class Ticket {
    private int value;
    private Gate origin;

    public Ticket(int balance) {
        this.value = balance;
    }

    public int getValue() {
        return value;
    }

    public abstract void adjustValue(int amount);
    public abstract boolean isValid();
    public void deduct(int amount){
        value-=amount;
    }
    public void setOrigin(Gate gate){
        if(gate==null){
            this.origin = null;
        }
        else
            this.origin = gate;
    }



    public Gate getOrigin() {
        return origin;
    }

    @Override
    public String toString() {
        return "Ticket value: " + value;
    }
}
