package ex17;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {
    private int id;
    private double balance;
    private double annualInterestRate = 5;
    private Date createdDate;

    public Account(){
        createdDate = new Date();
    }
    public Account(int id, double balance){
        createdDate = new Date();
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public double getMonthlyInterestRate(){
        return annualInterestRate/12/100;
    }

    public double getMonthlyInterest(int year){
        int n = year*12;
        return balance*getMonthlyInterestRate()*(Math.pow(1+getMonthlyInterestRate(),n))/
                (Math.pow(1+getMonthlyInterestRate(),n)-1);
    }
    public void widthDraw(double cash){
        if(balance>=cash)
            balance -= cash;
    }
    public void deposit(double cash){
        balance+=cash;
    }

    public String toString(int year){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return "Account "+ id + " created on " + formatter.format(createdDate) +
                " have balance: $" + balance + " and monthly interest: $"+ String.format("%.5g%n",getMonthlyInterest(year));
    }
}
