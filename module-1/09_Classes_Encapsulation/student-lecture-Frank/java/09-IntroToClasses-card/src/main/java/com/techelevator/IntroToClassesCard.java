package com.techelevator;

public class IntroToClassesCard {
        //------------------------------------------------------------------------------------------------------------
        // Application program for the Intro To Classes App
        //------------------------------------------------------------------------------------------------------------
        public static void main(String[] args) {
        /*
        *  This program will demonstrate several concepts presented in the Java cohort
        *  in the topic Introduction to Classes.
        *  
        *   
        *   This is the application program that will instantiate and manipulate objects of our class
        *    to do some actual work		
        *    
        *    Class name = new Class();
        *    
        */
        	
        	PlayingCard aCard=new PlayingCard();
        	
        	System.out.println("aCard is: " + aCard);//Display our PlayingCard object	
        	
        	PlayingCard Card1=new PlayingCard(12,"Hearts", "Red",true );
        	System.out.println(Card1);
        	
        	System.out.println("Card1 value is: "+ Card1.getCardValue());
        
        	System.out.println("Card1 value: "+Card1.getCardValue());
        	System.out.println("Card1 color: "+Card1.getCardColor());
        	
           	PlayingCard  Card2=new PlayingCard(134,"Diamond", "White",true );
        	
        	System.out.println("--------------------------------------------");
        	System.out.println("Card2 value is: "+ Card2.getCardValue());
        	System.out.println("Card2 value: "+Card2.getCardValue());
        	System.out.println("Card2 color: "+Card2.getCardColor());
	
	}
}
		 

