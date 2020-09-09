package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner input = new Scanner(System.in);
        //Declaration
        double P,annualRate, r,M;
        int year,n;
        System.out.println("Mortgage Payment Calculation");
        System.out.println("-----------------------------");

        //Input value
        do {
            System.out.print("Enter the principal>> ");
            P = input.nextDouble();
        }while(P<0);

        do{
            System.out.print("Enter the annual rate>> ");
            annualRate = input.nextDouble();
        }while(annualRate<0);

        do{
            System.out.print("Enter the loan year>> ");
            year = input.nextInt();
        }while(year<0);

        //Calculation monthly rate, period of month, monthly payment
        r = annualRate/100/12;
        n = year*12;
        M = P * r * Math.pow((1+r),n)/(Math.pow((1+r),n)-1);

        //Output the result
        System.out.printf("The monthly payment : $%.3f\n",M);
    }
}
