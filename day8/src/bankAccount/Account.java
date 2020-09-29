package bankAccount;

abstract public class Account {
    private String ID;
    protected double balance;
    public Account(String ID, double balance) {
        this.ID = ID;
        this.balance = balance;
    }

    public String getID() {
        return ID;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double cash){
        balance+=cash;
    }

    public void withdraw(double cash){
        if(balance<cash)
            throw new IllegalArgumentException();
        else
            balance-=cash;
    }

    abstract double monthlyInterest();
}
