package bankAccount;

public class SavingsAccount extends Account{
    private double annualInterest;

    public SavingsAccount(String ID, double balance, double annualInterest) {
        super(ID, balance);
        this.annualInterest = annualInterest;
    }
    @Override
    public void withdraw(double cash){
        if(balance>=cash)
            balance-=cash;
    }
    @Override
    public double monthlyInterest(){
        return balance * annualInterest/100/12;
    }
}
