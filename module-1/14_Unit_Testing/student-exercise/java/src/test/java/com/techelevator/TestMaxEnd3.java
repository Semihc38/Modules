package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMaxEnd3 {
	
	
	MaxEnd3 maxEnd3=new MaxEnd3();

	@Test
	public void test_max_end_3() {
		
		int[]array1= {3, 3, 3};
		assertEquals("It is not what is ",array1,maxEnd3.makeArray(array1));
		int[]array2= {11, 11, 11};
		assertEquals("It is not what is ",array2,maxEnd3.makeArray(array2));
		int[]array3= {3, 3, 3};
		assertEquals("It is not what is ",array3,maxEnd3.makeArray(array3));
		
	}

}
