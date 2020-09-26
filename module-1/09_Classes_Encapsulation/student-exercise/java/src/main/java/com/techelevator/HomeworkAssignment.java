package com.techelevator;

public class HomeworkAssignment {

	private int earnedMarks;
	private int possibleMarks;
	private String submitterName;
	private String letterGrade;
	
	
	
	public HomeworkAssignment(int possibleMarks, String submitterName) {
		this.submitterName=submitterName;
		this.possibleMarks=possibleMarks;	
	}
	
	public int getEarnedMarks() {
		return earnedMarks;
	}
	public void setEarnedMarks(int earnedMarks) {
		this.earnedMarks = earnedMarks;
	}
	public int getPossibleMarks() {
		return possibleMarks;
	}
	
	public String getSubmitterName() {
		return submitterName;
	}
	
	public String getLetterGrade() {
		if(earnedMarks*100/possibleMarks>=90) {
			letterGrade="A";
		}else if(earnedMarks*100/possibleMarks>=80) {
			letterGrade="B";
		}else if(earnedMarks*100/possibleMarks>=70 ) {
			letterGrade="C";
		}else if(earnedMarks*100/possibleMarks>=60 ) {
			letterGrade="D";
		}else  {
			letterGrade="F";
		}
		return letterGrade;
	}
	
}
