package edu.neumont.csc110.Final;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EventHandler{
	private Event e;
	private EventType eT;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	private ArrayList<Event> event = new ArrayList<Event>();
	Predicate<? super Event> filter;
	
	public EventHandler(){
		
	}

	public void veiwDay(Date start) {
		Date currentTime = new Date();
		System.out.println(formatter.format(currentTime));
		//return boolean 
	}
	public void addEvent() {
		e = new Event();
		e.editAll();
		e.displayAll();
		event.add(e);
	}

	public void removeEvent() {
		for ( int i = 0;  i < event.size(); i++){
			Event e = event.get(i);
			if(e.getEventTitle().equals(Methods.getLastInput()))
			{
				event.remove(i);
				i--;
			}
		}
	}
	

	// getEventAmount
	//		gets input of date and checks against events to see how many of them there are
	
	public void interactWithEvents() {
		
	}
	
	public ArrayList<Event> getEvents() {
		return (ArrayList<Event>) event;
	}
	
}
