package bankAccount;

public class CurrentPlusAccount extends Account{
    double annualInterest;
    double fee;
    double minBalance;
    public CurrentPlusAccount(String ID, double balance,double annualInterest,double minBalance, double fee) {
        super(ID, balance);
        this.annualInterest = annualInterest;
        this.fee = fee;
    }

    public void clearCheck(int cash){
        withdraw(cash);
        balance-=fee;
    }
    @Override
    public double monthlyInterest(){
        if(balance>minBalance)
            return balance*annualInterest/1200;
        else
            return 0;

    }
}
