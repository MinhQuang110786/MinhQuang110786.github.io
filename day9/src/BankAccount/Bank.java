package BankAccount;

public class Bank {
    public static void main(String[] args){
        Account savingAccount = new SavingAccount("B1",100000,12);
        System.out.println(savingAccount);
        savingAccount.deduct(5000);
        System.out.println(savingAccount);

        Account currentPlus = new CurrentPlusAccount("P1",5000,2.5,200,7.5);
        ((CurrentAccount) currentPlus).clearCheck(500);
        System.out.println(currentPlus);
    }
}
