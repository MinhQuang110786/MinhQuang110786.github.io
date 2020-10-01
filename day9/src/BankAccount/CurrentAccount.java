package BankAccount;

public class CurrentAccount extends Account{
    private double charge;
    public CurrentAccount(String ID, double balance, double charge) {
        super(ID, balance);
        this.charge = charge;
    }
    public void clearCheck(double amount){
        deduct(amount+charge);
    }

    @Override
    public double monthlyInterest() {
        return 0;
    }
}
