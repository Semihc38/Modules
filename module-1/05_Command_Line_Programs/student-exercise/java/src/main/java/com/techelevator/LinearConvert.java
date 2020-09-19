package com.techelevator;

import java.util.Scanner;

public class LinearConvert {

	public static void main(String[] args) {

		Scanner scan =new Scanner(System.in);
		
		System.out.print("Please enter a value for feet: ");
		double feet=scan.nextDouble();
		System.out.println(feet+" feet is "+ feet*0.3048 +" meters.");
			
		System.out.print("\nPlease enter a value for meter: ");
		double meter=scan.nextDouble();
		System.out.println(meter+" meter is "+ meter* 3.2808399+" feet.");
	}

}
