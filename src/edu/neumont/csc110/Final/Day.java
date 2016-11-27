package edu.neumont.csc110.Final;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Day {

	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	private Scanner in = new Scanner(System.in);
	
	private ArrayList<Event> events;

	public void DateAndTime() {

		Date currentTime = new Date();
		System.out.println(formatter.format(currentTime));
	}


	public void AddEvent() {
		Methods.getValidInput("What would you like to add?\n");
		
		Methods.getConfirmation("Is this correct:\n" + Methods.getLastInput());
		
		//return getInput into array list
	}

	public void RemoveEvent() {
	}

	public void interactWithEvents() {

	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}
}
