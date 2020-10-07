package com.techelevator.myFileProcessingCode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MyFileProcessor {

	/*********************************************************************************
	 * This program will read each line from the numbers.txt file
	 * and write each number in the line and the sum of those numbers to a file
	 * @throws IOException 
	 *********************************************************************************/
	
	public static void main(String[] args) throws IOException {
		
		Scanner theKeyboard = new Scanner(System.in);
		// Define the input file and Scanner object to read it - file is in the data folder of the project folder
		File myFile = new File("./data/numbers.txt");  // Assign a File object to numbers.txt

		// Check to be sure the File Object is assigned an existing file - terminate if not
		if (!myFile.exists() || !myFile.isFile()) {
			System.out.println("path specified is not an existing file");
			System.exit(16);   // terminate program
		}
		
		Scanner theFile = new Scanner(myFile);         // Assign the File Object to a Scanner
		
		/*****************************************************************************************
		 * Define the object necessary to write to an disk file
		 * 
		 ****************************************************************************************/
		File diskFile= new  File("BigFile.Cody");
		PrintWriter outFile= null;
		
		//Check to see if the output file already exists and warm the user it might be overridden
		if(diskFile.exists()) {
	
		
			System.out.println(diskFile+ "already exists - will add new data to the end of it");
			//to add to end of an existing file - append to a file
			//Define a FileWriter object and tell it we want to append to the file
			//fileWriter)a-File-object,boolean) - boolean-treu- append, false over ride the file
			FileWriter aFileWriter= new FileWriter(diskFile,true); // true indicates we want to append the file
			//Define a BufferedWrite for the FileWriter to enable buffering for the file
			//BufferedWriter (a-File-Writer-object);
			BufferedWriter aBufferedWriter= new BufferedWriter(aFileWriter);
			// Define a PrintWriter object for the BufferedWriter
			 outFile= new PrintWriter(aBufferedWriter);
		}
		else {
			outFile=new PrintWriter(diskFile);
		}
//		if(diskFile.exists()) {
//			System.out.println("The output file: "+ diskFile+" already exists and will be overridden ");
//			System.out.println("Do you want to continue? (Y/N)");
//			String response=theKeyboard.nextLine();
//			if(response.equals("N")) {
//				System.out.println("Program terminating at user request");
//				System.exit(16); //Exit the program with a return 16 any number can be put instead of 16
//			}
//		}
		//PrintWriter destroy any existing file associated with the File Object
		int lineTotal  = 0;  // hold the sum of the numbers in the line we read
		String theLine = ""; // hold the line with the numbers from the file
		int linesWritten=0;
		
		// Loop through the file one line at a time while there are lines in the file
		while(theFile.hasNextLine()) {
			// Read a line from the file and store it in theLine
			theLine = theFile.nextLine();
			// Break the line up into separate values based on the , separating the value
			String[] theValues = theLine.split(",");
			//    For each value in the line...
			for(int i=0; i < theValues.length; i++) {
				// Convert the individual value from String to numeric so we can add them
				int aValue = Integer.parseInt(theValues[i]);
				// Add each value from the line to sum
				lineTotal += aValue;
				// Display the values in the line
			//	System.out.println("Input Line Value[" +i+"] is: " + aValue);
				outFile.println("Input Line Value[" +i+"] is: " + aValue); // write a line to the file
				linesWritten++;
			}
		//    write the sum of the values to the file
			//System.out.println("The sum of the values in the line is: " + lineTotal);
			outFile.println("The sum of the values in the line is: " + lineTotal);
		//    Reset sum before looping again to be sure we only get the sum of the numbers in the line
		    lineTotal = 0;
		    linesWritten++;
		}
		theFile.close();
		// Close the file to write any still in the memory buffer to disk and  avoid a resource leak
		outFile.close();
		System.out.println("***Program Complete***");
		System.out.println(linesWritten+" lines written");
	}
}