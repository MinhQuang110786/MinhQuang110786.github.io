package ex17;

public class Main {
    public static void main(String args[]){
        Account account1 = new Account();
        account1.setId(1);
        account1.setBalance(1000.0);
        account1.deposit(500.0);
        account1.widthDraw(200);
        System.out.println(account1.toString(5));
        System.out.println("************************");
        Account account2 = new Account(3,5000);
        account2.setAnnualInterestRate(6);
        account2.deposit(2000);
        account2.widthDraw(3000);
        System.out.println(account2.toString(8));
    }
}
