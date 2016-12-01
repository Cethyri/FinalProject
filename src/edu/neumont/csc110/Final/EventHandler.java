package edu.neumont.csc110.Final;

import java.text.SimpleDateFormat;
import java.util.*;

public class EventHandler{
	private Event e;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	private List<Event> event = new ArrayList<Event>();
	//private ArrayList<Event> event;
	
	public EventHandler(){
		
	}

	public void veiwDay(Date start) {
		Date currentTime = new Date();
		System.out.println(formatter.format(currentTime));
	}
	public void addEvent() {
		e = new Event(0, 0, 0, 0, 0, 0, null, null, null, null, null, null);
		e.editAll();
		e.displayAll();
		event.add(e);
		System.out.println(event);
	}

	public void removeEvent() {
		event.remove(0);
		System.out.println("Theres nothing there. " + event);
	}
	
	public ArrayList<Event> getEvents() {
		return (ArrayList<Event>) event;
	}
	
}
