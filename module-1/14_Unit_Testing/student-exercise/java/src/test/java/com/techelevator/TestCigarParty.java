package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCigarParty {
	CigarParty cigarParty=new CigarParty();
	@Test
	public void test() {
		assertEquals("It is not what",false,cigarParty.haveParty(30, false));
		assertEquals("It is not what",true,cigarParty.haveParty(50, false));
		assertEquals("It is not what",true,cigarParty.haveParty(70, true));
	}

}
