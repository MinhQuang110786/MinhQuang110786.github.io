package ex16;
import java.util.Scanner;
public class Main {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the stock code>>");
        String code = input.nextLine();
        System.out.print("Enter the stock name>>");
        String name = input.nextLine();
        Stock stock = new Stock(code,name);
        stock.setPreviousClosingPrice(34.5);
        stock.setCurrentPrice(34.35);
        System.out.printf("The percentage between closing and current stock: %.2f%%\n",stock.getChangePercent());
    }
}
