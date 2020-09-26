package com.techelevator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Lecture {

	public static void main(String[] args) {

		System.out.println("####################");
		System.out.println("        MAPS");
		System.out.println("####################");
		System.out.println();
		
	//Map is a collection class that allows the storage of key-value pair.
		//used it to associate one value (the key) to another value(the value)
		//aka Associative Array that is a map collection 
		// kinds of maps : Hash Map is unordered Map entries may be in any order 
		// to define a May : Map<key -data-type\, value-dataType name=new HashMap<>
		
		Map<String,String> residence=new HashMap<>();
		residence.put("Semih","Lorain");
		residence.put("Brandon","Sandusky");
		residence.put("Cody","Akron");
		residence.put("Boris","Twinsburg");
		residence.put("Vince","Mentor");
		residence.put("Gabby","S.Euclid");
		
		
		
		Set<String> theKeys=residence.keySet();
		
		System.out.println(theKeys);
		for(String name:theKeys) {
			System.out.println(name);
		}
		
		
		


	}

}
