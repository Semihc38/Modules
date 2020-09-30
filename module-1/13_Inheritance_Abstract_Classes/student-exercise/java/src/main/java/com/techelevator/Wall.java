package com.techelevator;

public abstract class Wall {
	
	
	private String color;
	private String name;
	 
	 public Wall(String name, String color) {
			this.color=color;
			this.name=name;
			
		}
	 
	public String getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public abstract int getArea() ;

}
