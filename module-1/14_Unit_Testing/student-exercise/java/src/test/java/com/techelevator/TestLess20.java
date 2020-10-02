package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLess20 {
	Less20 less20=new Less20();
	@Test
	public void test_less_20() {
		
		int num1=18;
		assertEquals("it is now what",true,less20.isLessThanMultipleOf20(num1));
		int num2=19;
		assertEquals("it is now what",true,less20.isLessThanMultipleOf20(num2));
		int num3=20;
		assertEquals("it is now what",false,less20.isLessThanMultipleOf20(num3));
		
	}

}
