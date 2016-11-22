package edu.neumont.csc110.Final;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Day {

	private Date eventDate;
	private String dateString;

	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	private Scanner in = new Scanner(System.in);
	
	private ArrayList<Event> events;

	public void DateAndTime() {

		Date currentTime = new Date();
		System.out.println(formatter.format(currentTime));
	}

	/*							To be moved into Calender Class
	public void AddEvent() {
		System.out.println("Please enter a date. Ex. MM/DD/YYYY");

		dateString = in.nextLine();
		
		String regex = "(0?[1-9]|1[0-2])/(0?[1-9]|[12][0-9]|3[01])/([0-9]{4}$)"; 


		while (!dateString.matches(regex)) {

			System.out.println("Please enter a date. \nEx. MM/DD/YYYY; 1/1/1111; 01/01/1111");
			dateString = in.nextLine();
		}
		
		try {
			eventDate = formatter.parse(dateString);
			String newDateString = formatter.format(eventDate);
			System.out.println(newDateString);
		} catch (ParseException e) { }
		
	}*/

	public void RemoveEvent() {

	}

	public void interact() {

	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}
}
