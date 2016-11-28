package edu.neumont.csc110.Final;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EvenstHandler {

	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	private ArrayList<Event> eventList;

	public EvenstHandler() {
		Date currentTime = new Date();
		System.out.println(formatter.format(currentTime));
	}

	public void interactWithEvents() {
		
	}
	
	private boolean addToEvent(Event newEvent){
		//eventList.add(e.displayAll());
		
		return true;
	}
	
	public ArrayList<Event> getEvents() {
		return eventList;
	}
	
}
