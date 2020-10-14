package com.techelevator;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Module1CodingAssessment {

	public static void main(String[] args) throws IOException {
		MovieRental movieRental=new MovieRental(null, null, false);

		BufferedReader br=new BufferedReader(
				new FileReader("MovieInput.csv"));
	String line;
	while((line=br.readLine())!=null) {
		double dbl=movieRental.getRentalPrice((line.split("\\,")[1]),
				(line.split("\\,")[2]));
		String str=String.valueOf(dbl);
		line=line+" , "+ str;
		System.out.println(line);	
		}
	br.close();
	
	}
	
}
