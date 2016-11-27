package edu.neumont.csc110.Final;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Day {
	
	private ArrayList<Day> events;
	private int i;

	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	Scanner in = new Scanner(System.in);

	public void currentDateAndTime() {

		Date currentTime = new Date();
		System.out.println(formatter.format(currentTime));
	}


	public void AddEvent() {
		Event E = new Event();
		
		E.setEventOccurence();
		E.setPriorityLevel();
		E.setStartTime();
		E.setEndTime();
		E.setDescription();
		
		//events.add(i, E.displayTimesAndDescripiton());
	}

	public void RemoveEvent() {
		
	}
}
