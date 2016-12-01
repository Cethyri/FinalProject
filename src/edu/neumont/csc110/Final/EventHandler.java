package edu.neumont.csc110.Final;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
public class EventHandler{
	private Event e;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	private ArrayList<Event> events = new ArrayList<Event>();
	
	public EventHandler(){
		
	}

	public void veiwDay(Date start) {
		Date currentTime = new Date();
		System.out.println(formatter.format(currentTime));
	}
	
	public void addEvent() {
		e = new Event();
		e.editAll();
		e.displayAll();
		events.add(e);
		System.out.println(events);
	}

	public void removeEvent() {
		for ( int i = 0;  i < events.size(); i++){
			Event e = events.get(i);
			if(e.getEventTitle().equals(Methods.getLastInput()))
			{
				events.remove(i);
				i--;
			}
		}
	}
	

	// getEventAmount
	//		gets input of date and checks against events to see how many of them there are
	
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
