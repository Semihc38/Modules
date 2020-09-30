package com.techelevator;

public class RectangleWall extends Wall{

	public RectangleWall(String name, String color,int length,int height) {
		super(name, color);
		this.height=height;
		this.length=length;
	}
	
	
	private int length;
	private int height;
	
	
	public int getLength() {
		return length;
	}
	public int getHeight() {
		return height;
	}
	
	@Override
	public int getArea() {
		return this.height*this.length;
	}
	public String toString() {
		 return this.getName()+" ("+this.getLength()+"x"+this.getHeight()+") rectangle";
	}
}
