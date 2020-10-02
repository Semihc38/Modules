package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSameFirstLast {
	
	
	SameFirstLast sameFirstLast=new SameFirstLast();

	@Test
	public void test_for_same_first_last() {
		int[]array= {1, 2, 3};
		assertEquals("it is not what is",false,sameFirstLast.isItTheSame(array));
		
	}
	@Test
	public void test_for_same_first_last2() {
		
		int[]array= {1, 2, 3, 1};
		assertEquals("it is not what is",true,sameFirstLast.isItTheSame(array));
	}
	@Test
	public void test_for_same_first_last3() {
		
		int[]array= {1, 2, 1};
		assertEquals("it is not what is",true,sameFirstLast.isItTheSame(array));
	}


}
