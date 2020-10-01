package toller;

public class OneWayTicket extends Ticket{
    private boolean valid = true;
    public OneWayTicket(int balance) {
        super(balance);
    }



    @Override
    public void adjustValue(int amount) {

    }

    @Override
    public boolean isValid() {
        return valid;
    }


    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return super.toString() + "\tstatus:" + valid;
    }
}
