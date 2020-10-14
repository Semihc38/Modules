package com.techelevator;

public class MovieRental {
	String movieTitle;
	String movieFormat;
	boolean isPremiumMovie;
	double rentalPrice;
	public MovieRental(String movieTitle, String movieFormat, boolean isPremiumMovie) {
		
		this.movieTitle = movieTitle;
		this.movieFormat = movieFormat;
		this.isPremiumMovie = isPremiumMovie;
		
	}
	double VHS =0.99;
	double DVD =1.99;
	double BluRay=2.99;
	
		public double getRentalPrice(String movieFormat,String isPremiumMovie) {
			
			if(movieFormat.equals("VHS")){
				 rentalPrice=VHS;
			}else if(movieFormat.equals("DVD")) {
				rentalPrice= DVD;
			}else if(movieFormat.equals("BluRay")) {
				 rentalPrice=BluRay;
			}
			if(isPremiumMovie.equals("true")) {
				
				rentalPrice+=1;
			}
			return rentalPrice;
		}
		public double lateFee(int daysLate) {
			if(daysLate==0) {
				return 0.00;
			}else if(daysLate==1) {
				return 1.99;
			}else if(daysLate==2) {
				return 3.99;
			}else if(daysLate>=3) {
				return 19.99;
			}
			return 0.00;
		
		}
		
		public String toString() {
			return  "MOVIE - "+movieTitle+" - "+movieFormat +" "+ rentalPrice;
			
		}

}
