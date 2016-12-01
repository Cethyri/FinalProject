package edu.neumont.csc110.Final;

import java.io.Serializable;
import java.util.*;

@SuppressWarnings("deprecation")
public class EventHandler implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String ADD = "add", REMOVE = "remove", EDIT = "edit", BACK = "back";
	private int counter;
	private Event e;
	private ArrayList<Event> events = new ArrayList<Event>();
	// private ArrayList<Event> event;

	private boolean back, saved;

	public EventHandler() {

	}

	public boolean viewDay(Date d) {
		back = false;

		String input, action, title;

		String[] parts;

		do {
			title = "";
			input = Methods.getValidInput(interactMenu(d));
			input = input.toLowerCase();

			parts = input.split(" ");

			action = parts[0];

			if (parts.length > 1) {
				for (int i = 1; i < parts.length; i++) {
					title += parts[i];
				}
				interactWith(action, title, d);
			} else {
				interactWith(action, d);
			}
		} while (!back);

		return saved;
	}

	private String interactMenu(Date d) {
		String menu = "";

		ArrayList<Event> eventsOn = getEventsOn(d);
		for (Event e : eventsOn) {
			System.out.println(e);
			System.out.println();
		}

		menu += "What would you like to do?";
		menu += "\t\t";
		menu += "\n\n";
		menu += "\tInput - result\n\n";
		menu += "\t" + ADD + " - add a new events\n";
		menu += "\t" + REMOVE + " (Event title) - remove an event\n";
		menu += "\t" + EDIT + " (Event title) - edit an event\n";
		menu += "\t" + BACK + " - return to the file menu\n";
		return menu;
	}

	private boolean interactWith(String action, String title, Date d) {

		Methods.clearScreen();

		switch (action) {
		case REMOVE:
			removeEvent(title, d);
			saved = false;
			break;
		case EDIT:
			editEvent(title, d);
			System.out.println("hiiii");
			saved = false;
			break;
		default:
			Methods.pauseOn("Please enter an input provided.", true);
		}

		return saved;
	}

	private boolean interactWith(String action, Date d) {

		Methods.clearScreen();

		switch (action) {
		case ADD:
			addEvent(d);
			saved = false;
			break;
		case BACK:
			back = true;
			break;
		default:
			Methods.pauseOn("Please enter an input provided.", true);
		}

		return saved;
	}

	public void addEvent(Date d) {
		e = new Event(d);
		e.displayAll();
		events.add(e);
	}

	public void editEvent(String title, Date d) {
		for (Event e : getEventsOn(d)) {
			if (e.getEventTitle().equals(Methods.getLastInput())) {
				e.editAll();
				e.displayAll();
			}
		}
	}

	public void removeEvent(String title, Date d) {
		for (int i = 0; i < getEventsOn(d).size(); i++) {
			Event e = getEventsOn(d).get(i);
			if (e.getEventTitle().equals(Methods.getLastInput())) {
				events.remove(i);
				i--;
			}
		}
	}

	public int getEventAmount(Date d) {
		return getEventsOn(d).size();
	}

	public String getPriorities(Date d) {
		String priorities = "";
		boolean low = false, med = false, hi = false;
		ArrayList<Event> eventsOnDay = getEventsOn(d);

		for (Event e : eventsOnDay) {
			switch (e.getEventPriority()) {
			case LOW:
				low = true;
				break;
			case MEDIUM:
				med = true;
				break;
			case HIGH:
				hi = true;
				break;
			default:
			}
		}

		if (low) {
			priorities += "L";
		}
		if (med) {
			priorities += "M";
		}
		if (hi) {
			priorities += "H";
		}

		return priorities;
	}

	public ArrayList<Event> getEventsOn(Date d) {
		Date temp;
		ArrayList<Event> eventsOnDay = new ArrayList<Event>();
		events.trimToSize();
		for (Event e : events) {
			temp = e.getDate();
			if (temp.compareTo(d) == 0) {
				eventsOnDay.add(e);
			} else if (e.checkReoccursOn(d)) {
				eventsOnDay.add(e);
			}
		}
		return eventsOnDay;
	}

	public ArrayList<Event> getEvents() {
		return (ArrayList<Event>) events;
	}

	public void addEvent(Event event) {
		events.add(event);
	}
}
