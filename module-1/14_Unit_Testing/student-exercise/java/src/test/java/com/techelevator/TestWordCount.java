package com.techelevator;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;


import org.junit.Test;

public class TestWordCount {
	
	WordCount testWordCount=new WordCount();

	@Test
	public void test_for_word_count1() {
		
		//condition1
		 Map<String,  Integer> map1 = new HashMap<>();
		 String[]array1= {"ba", "ba", "black", "sheep"};
		// {"ba" : 2, "black": 1, "sheep": 1 }
		 map1.put("ba",2);
		 map1.put("black",1);
		 map1.put("sheep",1);
		 assertEquals("They are not equal", map1,testWordCount.getCount(array1));
		 
		//condition2
		 String[]array2= {"a", "b", "a", "c", "b"};
			Map<String, Integer>map2=new HashMap<>();
			map2.put("a", 2);
			map2.put("b",2);
			map2.put("c",1);
			assertEquals("They are not equal",map2,testWordCount.getCount(array2));
			
			//condition3
			String[]array3=new String[] {};
			Map<String, Integer>map3=new HashMap<>();
			//map3.isEmpty();
			assertEquals("They are not equal",map3,testWordCount.getCount(array3));
			
			//condition4
			String[]array4= {"c", "b", "a"};
			Map<String, Integer>map4=new HashMap<>();
			map4.put("c",1);
			map4.put("b",1);
			map4.put("a",1);
			assertEquals("They are not equal",map4,testWordCount.getCount(array4));
	}
	
}
