package com.techelevator;

import java.util.Scanner;

public class TempConvert {

	public static void main(String[] args) {

		
		Scanner input =new Scanner(System.in);
		System.out.print("Please enter Celsius temperature: ");
		int celsius=input.nextInt();
		System.out.println(celsius+ " Celsius equals "+ (int)((celsius * 1.8) + 32)+" Fahrenheit.");
		System.out.print("\nPlease enter Fahrenheit temperature: ");
		int fahrenheit=input.nextInt();
		System.out.println(fahrenheit+" Fahrenheit equals "+ (int)((fahrenheit -  32) / 1.8)+" Celsius.");
	}
}
