package com.techelevator.example.scanner;

import java.io.File;

//This program will read bank routing transfer numbers from a file and check their validity
// we trust the logic for validing is correct 
// we are focusing on reading the file
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExampleRTNValidator {
	// 
	private static final int[] CHECKSUM_WEIGHTS = new int[] { 3, 7, 1, 3, 7, 1, 3, 7, 1 };

	
	public static void main(String[] args) throws FileNotFoundException   {
                                          // throws - Tell the compiler I know this checked exception can happen
		printApplicationBanner();
		// get the path for the file to be processed from the user
		
		File inputFile = getInputFileFromUser();   // inputFile is a File object for an existing file
//		File inputFile = new File("rtn.txt");      // Hard-coding filename instead of asking user
	//	try(Scanner fileScanner = new Scanner(inputFile)) {  // Putting the Scanner inside a try statement causes it to be automatically close
		/*If the scanner is defined outside try statement  close the file when witn fileScanner.close();
		 * 		 * Since we don't know how many lines are in a file
		 * We create a loop to get a line and process it
		 * as long file has lines to be processed
		 * 
		 * while loop is a perfect loop for this
		 * 
		 * while(FileObject.hasNextLine()) - loop while there are lines we haven't processed
		 */
			Scanner fileScanner = new Scanner(inputFile)	;
			while(fileScanner.hasNextLine()) {         // Loop while the file has another line
				String line = fileScanner.nextLine();  //     Read the next line into the variable line
				String rtn = line.substring(0, 9);     //     Get the first 9 chars of the line read  ignore anything after 9 
				System.out.print("RTN : " + rtn);      //     Display the routing number to the user
				if(checksumIsValid(rtn) == false) {    //     If the routing number if invalid...
					System.out.println(" - Invalid");  //          print an Invalid message
				}
					else {                             //     if the routing number is valid...
						System.out.println(" - Valid");//          print the Valid message
				}                                      //     end of If
			}                                          // End of the while()
	fileScanner.close();								// close the file when  done to avoid a resource leak 	
	}                 // End of program
//     ++}
	
	//method to display a welcome banner to user
	//it is static because it is being called a static method -main()
	private static void printApplicationBanner() {
		System.out.println("******************");
		System.out.println("RTN Validator 9000");
		System.out.println("******************");
		System.out.println();
	}
	
	
	//  ask the user for the path to the input file to be use
	// Create a valid File object for a file and return it
	// it is static becuase it is being called by a static method -main()
	@SuppressWarnings("resource")   // Don't give me warning about resource issues
	
	private static File getInputFileFromUser() {
		Scanner userInput = new Scanner(System.in);                // Define a keyboard object
		System.out.print("Please enter path to input file >>> ");  // Prompt the user
		String path = userInput.nextLine();                        // Get a line from the keyboard
		
		System.out.println("Path entered: "+ path + "\n");
		
		File inputFile = new File(path);        // Define a File object for the input file
//         FileObj.methods()		

		if(inputFile.exists() == false) {               // checks for the existence of a file
			System.out.println(path+" does not exist"); // If not - message the user
			System.exit(1); // Ends the program         // Terminate the program with a return code 1
		} else if(inputFile.isFile() == false) {        // If it does exist, check to see if it's a file
			System.out.println(path+" is not a file");  // if not a file - message the user
			System.exit(1); // Ends the program         // terminate program with a return code 1
		}
		return inputFile;      // we have a File object that exists and IS a file - so return it
	}
	//return  end the current methdo and return tto the callign method 
	// System.exti()- terminates the process and returns to operation sytsem with a return code
	// return codes may be check by operation system processes and react to them

	private static boolean checksumIsValid(String routingNumber) {
		int checksum = 0;
		for(int i = 0; i < 9; i++) {
			int digit = Integer.parseInt(routingNumber.substring(i, i+1));
			checksum += digit * CHECKSUM_WEIGHTS[i];
		}
		return checksum % 10 == 0;
	}
}
