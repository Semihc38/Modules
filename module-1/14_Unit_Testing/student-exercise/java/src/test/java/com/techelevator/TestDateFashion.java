package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDateFashion {
	DateFashion dateFashion=new DateFashion();
	@Test
	public void test_date_fashion() {
		int num1=2;
		assertEquals("It is not what",num1,dateFashion.getATable(5, 10));
		int num2=0;
		assertEquals("It is not what",num2,dateFashion.getATable(5, 2));
		int num3=1;
		assertEquals("It is not what",num3,dateFashion.getATable(5, 5));
		
	}

}
