package com.techelevator;

import static org.junit.Assert.*;



import org.junit.Test;

public class TestAnimalGroupName {
	AnimalGroupName animalGroupName=new AnimalGroupName();
	@Test
	public void test_animal_group_name() {
		String []groupNames= {"Crash","Tower","Herd","Herd","Murder","Kit", "Pat","Herd","Pack","Float"};
		String []animalNames= {"rhino","giraffe","elephant","lion","crow","pigeon", "flamingo","deer","dog","crocodile"};
		for(int i=0; i<groupNames.length;i++) {
			assertEquals("It is now what",groupNames[i],animalGroupName.getHerd(animalNames[i]));
		}
		
	}

}
