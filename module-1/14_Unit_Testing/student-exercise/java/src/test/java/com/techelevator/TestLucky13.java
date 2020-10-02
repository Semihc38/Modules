package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLucky13 {
	Lucky13 lucky13=new Lucky13();
	@Test
	public void test_lucky_13() {
		int []array1= {0, 2, 4};
		assertEquals("It is not what ",true,lucky13.getLucky(array1));
		int []array2= {1, 2, 3};
		assertEquals("It is not what ",false,lucky13.getLucky(array2));
		int []array3= {1, 2, 4};
		assertEquals("It is not what ",false,lucky13.getLucky(array3));
		
	}

}
