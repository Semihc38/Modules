package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStringBits {
	
	StringBits testMethods=new StringBits(); //instantiate and object witht the methods to be tested

	@Test
	public void test_for_Hello() {
		String expectedResult="Hlo";//Arrange -setup expected result
		String testData="Hello";    //Arrange -setup test data
		String actualResult =testMethods.getBits(testData);//Act -test method using the object containing the method and test data
		assertEquals("Incorrect String returned form getBits", expectedResult,actualResult);//Assert 
		
		
		//assertEquals("Incorrect String returned form getBits", "Hlo",testMethods.getBits("Hello"));
		
	}
//	@Test // Test for a null being passed
//	@Test //Test for an empty String being passed
//	@Test // Test for a single character String passed
	

}
