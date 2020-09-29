package abstractClass;

public class OneWayTicket extends Ticket{
    private boolean used = false;
    public OneWayTicket(){
        super(0);
    }
    @Override
    public void adjustValue(){
        int distance = gates.get(gates.size()-1).getDistance() - gates.get(0).getDistance();
        fee = Line.getFare(distance);
    }
    @Override
    public void setOrigin(Gate gate){
        super.setOrigin(gate);
        if(gate==null)
            used = true;
    }
    @Override
    public boolean isValid(){
        if(used)
            valid = false;
        return this.valid;
    }

}
