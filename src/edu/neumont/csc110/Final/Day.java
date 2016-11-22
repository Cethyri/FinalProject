package edu.neumont.csc110.Final;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Day {
	private ArrayList<Event> events;

	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	Scanner in = new Scanner(System.in);

	public void DateAndTime() {

		Date currentTime = new Date();
		System.out.println(formatter.format(currentTime));
	}


	public void AddEvent() {
		Methods.getValidInput("What would you like to add?\n");
		
		System.out.println("Is this correct:\n" + Methods.getInput());
		Methods.getConfirmation();
		
		////////////return getInput into array list
	}

	public void RemoveEvent() {
	}

	public void interactWithEvents() {

	}
}
