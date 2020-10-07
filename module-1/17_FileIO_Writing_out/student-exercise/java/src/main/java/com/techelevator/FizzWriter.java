package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FizzWriter {

	public static void main(String[] args) throws IOException {
		
		File file=new File("FizzBuzz.txt");
		FileWriter fw=new FileWriter(file);
		
		for(int i=1; i<=300;i++)
		{
			if(i%3==0 && i%5==0) {
				fw.write("FizzBuzz\n");
			}
			else if(i%3==0 ) {
				fw.write("Fizz\n");
			}
			else if(i%5==0) {
				fw.write("Buzz\n");
			}
			else{
				fw.write(i+"\n");
			}
		}
		fw.close();
		
	}

}
