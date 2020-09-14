package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner input = new Scanner(System.in);
        int n;
        System.out.print("Enter the number>>");
        n = input.nextInt();
        if(n%5==0 && n%3!=0)
            System.out.println("Fizz");
        else if(n%3==0 && n%5!=0)
            System.out.println("Buzz");
        else if(n%3==0 && n%5==0)
            System.out.println("FizzBuzz");
        else
            System.out.println(n);
    }
}
