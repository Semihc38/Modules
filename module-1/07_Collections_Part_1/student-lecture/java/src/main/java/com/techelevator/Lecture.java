package com.techelevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lecture {

	public static void main(String[] args) {
		System.out.println("####################");
		System.out.println("       LISTS");
		System.out.println("####################");
		
		List<String>someJavaStudents= new ArrayList<>();
		someJavaStudents.add("Semih");
		someJavaStudents.add("Jason");
		someJavaStudents.add("Semih");
		someJavaStudents.add("Yoav");
		someJavaStudents.add("Cody");
		


		System.out.println("####################");
		System.out.println("Lists are ordered");
		System.out.println("####################");
		
		for(int i=0; i<someJavaStudents.size(); i++) {
			System.out.println("element #"+i+":"+ someJavaStudents.get(i));
		}


		System.out.println("####################");
		System.out.println("Lists allow duplicates");
		System.out.println("####################");
		someJavaStudents.add("Yoav");
		someJavaStudents.remove("Semih");

		for(int i=0; i<someJavaStudents.size(); i++) {
			System.out.println("element #"+i+":"+ someJavaStudents.get(i));
		}
		
		
		System.out.println("####################");
		System.out.println("Lists allow elements to be inserted in the middle");
		System.out.println("####################");


		System.out.println("####################");
		System.out.println("Lists allow elements to be removed by index");
		System.out.println("####################");


		System.out.println("####################");
		System.out.println("Find out if something is already in the List");
		System.out.println("####################");
		if(someJavaStudents.contains("Semih")) {
			System.out.println(true);
		}else {
		System.out.println(false);
		}
		


		System.out.println("####################");
		System.out.println("Find index of item in List");
		System.out.println("####################");


		System.out.println("####################");
		System.out.println("Lists can be turned into an array");
		System.out.println("####################");


		System.out.println("####################");
		System.out.println("Lists can be sorted");
		System.out.println("####################");


		System.out.println("####################");
		System.out.println("Lists can be reversed too");
		System.out.println("####################");


		System.out.println("####################");
		System.out.println("       FOREACH");
		System.out.println("####################");
	
	
	
	}
}