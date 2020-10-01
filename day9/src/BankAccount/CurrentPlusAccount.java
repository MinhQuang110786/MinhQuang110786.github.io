package BankAccount;

public class CurrentPlusAccount extends CurrentAccount{
    private final int MONTH_IN_YEAR =12;
    private double minimumBalance;
    private double annualInterest;
    public CurrentPlusAccount(String ID, double balance, double charge, double minimumBalance, double annualInterest) {
        super(ID, balance, charge);
        this.minimumBalance = minimumBalance;
        this.annualInterest = annualInterest;
    }

    @Override
    public double monthlyInterest(){
        if(getBalance()>=minimumBalance)
            return getBalance()*annualInterest/100/MONTH_IN_YEAR;
        else
            return 0;
    }
}
