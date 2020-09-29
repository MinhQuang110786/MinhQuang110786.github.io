package abstractClass;

public class PrepaidCard extends Ticket{
    public PrepaidCard(int balance){
        super(balance);
    }
    @Override
    public void adjustValue(){
        int distance = gates.get(gates.size()-1).getDistance() - gates.get(0).getDistance();
        fee = Line.getFare(distance);
        if(getValue()<fee)
            valid = false;
        deduct(fee);
    }

    @Override
    public boolean isValid(){
        return this.valid;
    }
}
