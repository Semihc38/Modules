package com.techelevator;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Module1CodingAssessment {

	public static void main(String[] args) throws IOException {
		MovieRental movieRental=new MovieRental(null, null, false);

		BufferedReader br=new BufferedReader(
				new FileReader("MovieInput.csv"));
	Scanner input=new Scanner(br);
	String line;
	List<MovieRental> movieList=new ArrayList<>();
	while((line=br.readLine())!=null) {
		String[] splitLine = line.split("\\|");
//		movieList.add(splitLine[0],splitLine[1],Boolean.parseBoolean(splitLine[2]));
			
		}
	}

}
