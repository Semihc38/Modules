package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestFrontTimes {
	FrontTimes frontTimes=new FrontTimes();
	@Test
	public void test_front_times() {
		String str1="ChoCho";
		assertEquals("It is not what",str1,frontTimes.generateString("Chocolate", 2));
		String str2="ChoChoCho";
		assertEquals("It is not what",str2,frontTimes.generateString("Chocolate", 3));
		String str3="AbcAbcAbc";
		assertEquals("It is not what",str3,frontTimes.generateString("AbcAbcAbc", 3));
	}

}
