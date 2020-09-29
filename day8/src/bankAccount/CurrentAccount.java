package bankAccount;

public class CurrentAccount extends Account{
    private double fee;

    public CurrentAccount(String ID, double balance, double fee) {
        super(ID, balance);
        this.fee = fee;
    }
    public void clearCheck(double cash) {
        withdraw(cash);
        balance-=fee;
    }
    @Override
    public double monthlyInterest(){
        return 0;
    }
}
