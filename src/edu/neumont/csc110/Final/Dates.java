package edu.neumont.csc110.Final;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Dates {

	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	private Scanner in = new Scanner(System.in);
	
	private ArrayList<Event> event;

	public void dateAndTime() {

		Date currentTime = new Date();
		System.out.println(formatter.format(currentTime));
	}


	public void addEvent() {
		Methods.getValidDateInput("What date would you like this event to take place on?");

		do {
		Methods.getValidInput("What would you like to add?\n");
		
		Methods.getConfirmation("Is this correct:\n" + Methods.getLastInput());
		} while(Methods.getAnswer() == false);
		
//		dates.add(e);
//		event.add(Methods.getLastInput());
		//return getInput into array list
	}

	public void removeEvent() {
	}

	public void interactWithEvents() {

	}
	
	public ArrayList<Event> getEvents() {
		return event;
	}
	
}
