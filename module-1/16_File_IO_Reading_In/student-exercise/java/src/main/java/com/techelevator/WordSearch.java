package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WordSearch {

	public static void main(String[] args) throws IOException {
		
		
		searchWord(getInputFileFromUser(),wordToSearch());
	
		
	}
	static Scanner scan=new Scanner(System.in);
	private static File getInputFileFromUser() {
		
		
		System.out.println("Please enter the path");
		String path=scan.nextLine();
		File file =new File(path);
		
		if(file.exists() == false) {               
			System.out.println(path+" does not exist"); 
			System.exit(1);        
		} else if(file.isFile() == false) {        
			System.out.println(path+" is not a file");  
			System.exit(1); 
		}
		return file; 
		
		}
	private static String wordToSearch() {
		System.out.println("Please enter a word to search in the text.");
		String word=scan.nextLine();
		return word;
		
	}
	public static String searchWord(File file,String word) throws FileNotFoundException {
		System.out.println("Would like to search a word in case sensitive Yes / No?");
		String yesOrNo=scan.nextLine().toLowerCase().substring(0,1);
		
		Scanner input=new Scanner(file);
		int x=0;
		while(input.hasNextLine()) {
			
			if(yesOrNo.equals("n")){
				x++;
				String line=input.nextLine();
				if(line.toLowerCase().contains(word.toLowerCase())) {
					System.out.println(x+") "+ line);
				}
			}else {
				x++;
				String line=input.nextLine();
				if(line.contains(word)) {
					System.out.println(x+") "+ line);
			}
		}
		
	}
		input.close();
		return "";
	}
	
	}

