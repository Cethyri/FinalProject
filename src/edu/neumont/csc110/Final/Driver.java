package edu.neumont.csc110.Final;


public class Driver {

	public static void main(String[] args) {
		EventHandler EH = new EventHandler();
		EH.addEvent();
		EH.addEvent();
		System.out.println(EH.getEvents());
		
		Methods.getValidInput("What event would you like to remove.\n\tEnter the event title.");
		EH.removeEvent();
		System.out.println(EH.getEvents());
		
//		Handler H = new Handler();
//		H.interact();
	}

}
