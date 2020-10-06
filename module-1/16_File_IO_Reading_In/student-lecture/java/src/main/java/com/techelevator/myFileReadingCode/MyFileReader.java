package com.techelevator.myFileReadingCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyFileReader {
	

	//This program will read each line from the numbers.txt file
	// and display each number in the line sum of those numbers
	

	public static void main(String[] args) throws FileNotFoundException {
		
		// Define the input file and scanner object to read it - file is in the data folder of the project folder
		File myFile = new File("./data/numbers.txt");// assign a File object to numbers.txt
		//Check to be sure the File object is assign an existing file- terminate if not
		
		
		if(!myFile.exists()|| !myFile.isFile()) {
			
			System.out.println("path specified is not an existing file");
			System.exit(16);  // terminate program
		}
		
		
		Scanner theFile = new Scanner(myFile);// Assign the File object to a Scanner
		
		int lineTotal=0;// Hold the sum of the numbers in the line we read
		String theLine="";// Hold the line with the numbers from the file
		
		// Loop through the file on line at a time while there are lines in the file
		while(theFile.hasNextLine()) {
			//read a line from the file and store it in theLine
			theLine=theFile.nextLine();
			// break the line up into separate values based on the , separating the  value
			String[] theValues= theLine.split(",");
			// for each value in the line...
			for(int i=0; i<theValues.length; i++) {
										//	 convert the individual values from Strings to numerics so we can add them
										//	 add each value from the line to sum
										// 	 display the values in the line
			int aValue=Integer.parseInt(theValues[i]);
			
			lineTotal+=aValue;
			System.out.println("Input Line value[" +i+"] is: "+ aValue);
			}
			System.out.println("The sum of the values in the line is: "+ lineTotal);
			lineTotal=0;
			
		}
		theFile.close();
		
		
	//			
		// display the sum of the values
		// reset sum before looping again to be sure we only get the sum of the numbers in the line
		// close the file to avoid resource leak
		
	}
}