package bankAccount;

public class Bank {
    public static void main(String[] args){
        try{
            Account sAccount = new SavingsAccount("ACB",500,6.5);
            sAccount.deposit(50.5);
            sAccount.withdraw(150.55);
            displayInfor(sAccount);
            System.out.println("\n***********************");
            Account curAccount = new CurrentAccount("VCB",1500,1.5);
            curAccount.deposit(250.5);
            curAccount.withdraw(500);
            ((CurrentAccount) curAccount).clearCheck(700);
            displayInfor(curAccount);
            System.out.println("\n***********************");
            Account curPlusAccount = new CurrentPlusAccount("XYZ",2500,8.5,200,2.5);
            curPlusAccount.deposit(150);
            curPlusAccount.withdraw(1500);
            ((CurrentPlusAccount) curPlusAccount).clearCheck(1500);
            displayInfor(curPlusAccount);
        } catch(IllegalArgumentException ex){
            System.out.println(ex.getStackTrace());
        }

    }
    static void displayInfor(Account account){
        System.out.printf("ID %s have balance: $%.2f and monthly interest: $%.2f",account.getID(),
                account.getBalance(),account.monthlyInterest());
    }
}
