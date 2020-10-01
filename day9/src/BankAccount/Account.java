package BankAccount;

abstract public class Account {
    private String ID;
    private double balance;

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

    public abstract double monthlyInterest();
    public void deposit(double amount){
        balance+=amount;
    }
    public void deduct(double amount){
        if(balance<amount)
            throw new IllegalArgumentException("The balance is not enough");
        balance-=amount;
    }
    public String toString() {
        return getID() + "\t" + String.format("$%.2f",getBalance()) + "\t" + String.format("$%.2f",monthlyInterest());
    }
}
