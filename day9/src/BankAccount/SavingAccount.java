package BankAccount;

public class SavingAccount extends Account{
    private final int MONTH_IN_YEAR = 12;
    private double annualInterest;
    public SavingAccount(String ID, double balance, double annualInterest) {
        super(ID, balance);
        this.annualInterest = annualInterest;
    }

    @Override
    public double monthlyInterest() {
        return getBalance()*annualInterest/100/MONTH_IN_YEAR;
    }



}
