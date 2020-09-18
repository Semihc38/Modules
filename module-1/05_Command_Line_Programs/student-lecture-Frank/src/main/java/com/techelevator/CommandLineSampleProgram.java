package com.techelevator;

import java.util.Scanner;

public class CommandLineSampleProgram {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Frank's Command Line Sample Program");
		
		//REad input from the keyboard 
		//Define a  Java Scanner object  to represents the keyboard in the program 
		//
		
		Scanner theKeyboard = new Scanner(System.in);
		
		String aLine=""; // Hold  a line of  input from the keyboard.
		System.out.println("\nplease enter a line press enter.");
		aLine=theKeyboard.nextLine(); // get a line from the keyboard. 
		System.out.println("The line you entered was: "+aLine);
		
		System.out.println("\nPlease enter a number: ");
		int aNumber=theKeyboard.nextInt();
		System.out.println("Your entered the number: " + aNumber + " Twice that is: "+ aNumber* 2);
		
		System.out.println("\nPlease enter another number: ");
		int bNumber=theKeyboard.nextInt();
		System.out.println("Your entered the number: " + bNumber + " Twice that is: "+ bNumber* 2);
		
		
		 // Hold  a line of  input from the keyboard.
		aLine=theKeyboard.nextLine();
		System.out.println("\nplease enter a line press enter.");
		aLine=theKeyboard.nextLine(); // get a line from the keyboard. 
		System.out.println("The line you entered was: "+aLine);
		
		
		int sum=0;
		while (true) {
			System.out.println("Please enter a number of end to stop:");
			aLine=theKeyboard.nextLine();
			if(aLine.equals("end")) {
				break;
			}
			sum=sum+Integer.parseInt(aLine);
		}
	   System.out.println("The sum of the numbers is: "+ sum);
	}

}
