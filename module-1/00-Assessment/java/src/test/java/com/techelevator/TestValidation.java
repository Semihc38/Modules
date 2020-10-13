package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestValidation {
	MovieRental movieRental =new MovieRental(null, null, false);
	@Test
	public void test_rental_price_calculation() {
		assertEquals(1.99,movieRental.DVD,0.0);
		assertEquals(2.99,movieRental.BluRay,0.0);
		assertEquals(0.99,movieRental.VHS,0.0);
		
	}
	@Test
	public void test_late_fee_calculation() {
		assertEquals(0,movieRental.lateFee(0),0.0);
		assertEquals(1.99,movieRental.lateFee(1),0.0);
		assertEquals(3.99,movieRental.lateFee(2),0.0);
		assertEquals(19.99,movieRental.lateFee(3),0.0);
	}

}
