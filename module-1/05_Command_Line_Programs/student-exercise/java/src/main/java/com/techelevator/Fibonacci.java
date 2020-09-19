 package com.techelevator;

import java.util.Scanner;

public class Fibonacci {

	public static void main(String[] args) {

		
		Scanner scan=new Scanner(System.in);
		System.out.print("Please enter a number to find the fibonacci series: ");
		
		int aNumber=scan.nextInt();
		int num1=0;
		int num2=1;
		
		System.out.print("Fibonacci series is of "+aNumber+": " +num1+", "+ num2);
		
		for(int i= 2; i<aNumber; i++) {
			int sum=num1+num2;
		
			System.out.print(", "+ sum);
			num1=num2;
			num2=sum;
		}
		
	}
}

