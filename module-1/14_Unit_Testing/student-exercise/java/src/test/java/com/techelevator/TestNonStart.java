package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestNonStart {

	
	NonStart nonStart=new NonStart();
	@Test
	public void test_for_non_start() {
		String str1="ellohere";
		assertEquals("it is not what is ",str1,nonStart.getPartialString("Hello", "There"));
		String str2="avaode";
		assertEquals("it is not what is ",str2,nonStart.getPartialString("java", "Code"));
		String str3="hotlava";
		assertEquals("it is not what is ",str3,nonStart.getPartialString("shotl", "java"));
	}

}
