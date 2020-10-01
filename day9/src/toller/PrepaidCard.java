package toller;

public class PrepaidCard extends Ticket{
    public PrepaidCard(int balance) {
        super(balance);
    }

    @Override
    public void adjustValue(int amount) {
            deduct(amount);
    }

    @Override
    public boolean isValid() {
        return getValue()>0;
    }


}
