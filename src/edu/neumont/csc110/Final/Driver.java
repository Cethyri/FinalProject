package edu.neumont.csc110.Final;

public class Driver {

	public static void main(String[] args) {
		Day D = new Day();
		
		Event E = new Event();
		
		E.DisplayAll();
		
		D.addEvent();

		Handler H = new Handler();
		
		H.interact();
	}

}
