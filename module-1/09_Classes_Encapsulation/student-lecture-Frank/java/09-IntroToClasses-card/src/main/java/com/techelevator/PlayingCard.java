package com.techelevator;

public class PlayingCard {

	//___________________________________________
	//A class to represent/model a PlayingCard
	//___________________________________________
	
	
	/*
	 * Define the data members of the class
	 * 
	 * Data in class includes access modifier that specifies 
	 * who /what has access to the data
	 * public anyone has access
	 * private only members of the class have access
	 * 
	 */
	
	private int cardValue=1;										//value of the card Ace=1, j=11, Q=12, k=13
	private String cardSuit="Spades";
	private String cardShape="Rectangle";
	private String cardColor="Black";
	private boolean faceUp=false;
	
	
	
	
	// methods to support the Class
	// methods to initialize a new object.
	// constructor is a class method that initializes object for the class when they are created
	// 1. constructors have special requirements:
	// 2. they cannot return anything not even void -omit the return datatype on the signature.
	// 3. you can get constructor as much as you need as long as its parameter is different
	// 4. Default constructor is one that takes no parameters
	// 5. if you don't provide constructor Java runs its own default const. which initializes;
	
	// toString() method to convert our class to a String.
	
	
	public int getCardValue() {
		return cardValue;
	}
	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}
	public String getCardSuit() {
		return cardSuit;
	}
	public void setCardSuit(String cardSuit) {
		this.cardSuit = cardSuit;
	}
	public String getCardShape() {
		return cardShape;
	}
	public void setCardShape(String cardShape) {
		this.cardShape = cardShape;
	}
	public String getCardColor() {
		return cardColor;
	}
	public void setCardColor(String cardColor) {
		this.cardColor = cardColor;
	}
	public boolean isFaceUp() {
		return faceUp;
	}
	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}
	public PlayingCard() {
		cardValue=1;
		cardSuit="Spades";
		cardColor="Black";
		cardShape="Rectangle";
		faceUp=false;
			
	}
	public PlayingCard(int value,String suit, String color, boolean faceUp) {
		
		cardValue=value;
		cardSuit=suit;
		cardColor=color;
		cardShape="Rectangle";
		this.faceUp=faceUp;// we need this. bc without that it is a local variable name 
		//so to call the instance variable with the same name we need this. keyword to call instance variable
		
		
	}
	
	public String toString() {
		
		return "Card Value: "  + cardValue + " Card suit: "+ cardSuit
				+" Card Color: "+ cardColor + " Card shape: "+ cardShape
				+" Face up: "  + faceUp;
	}
	
	
	
	
	
	
}
