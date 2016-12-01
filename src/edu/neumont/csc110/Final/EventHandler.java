package edu.neumont.csc110.Final;

import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("deprecation")
public class EventHandler{
	private Event e;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	private ArrayList<Event> events = new ArrayList<Event>();
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
		events.add(e);
		System.out.println(events);
	}

	public void removeEvent() {
		events.remove(0);
		System.out.println("Theres nothing there. " + events);
	}
	
	public ArrayList<Event> getEventsOnDay(Date d) {
		Date temp;
		ArrayList<Event> eventsOnDay = new ArrayList<Event> ();
		for (Event e : events) {
			temp = e.getDate();
			if (temp.getMonth() == d.getMonth() && temp.getDate() == d.getDate() && temp.getYear() == d.getYear()) {
				eventsOnDay.add(e);
			}
//			else if (e.reoccursOn(d)) {
//				eventsOnDay.add(e);
//			}
		}
		return (ArrayList<Event>) events;
	}
	
	public ArrayList<Event> getEvents() {
		return (ArrayList<Event>) events;
	}
	
}
