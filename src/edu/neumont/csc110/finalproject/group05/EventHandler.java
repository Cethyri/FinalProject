package edu.neumont.csc110.finalproject.group05;

import java.io.Serializable;
import java.util.*;

public class EventHandler implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String ADD = "add", REMOVE = "remove", EDIT = "edit", BACK = "back";
	private Event e;

	// CS110 Requirement 5: Array or Collection

	private ArrayList<Event> events = new ArrayList<Event>();

	private boolean back, saved;

	public EventHandler() {

	}
	
	/**
	 * Views any given date 
	 * 
	 * @param d - The date to be viewed
	 * @param saved - Checks whether or not their will be a save
	 * @return returns this.saved
	 */
	public boolean viewDay(Date d, boolean saved) {
		back = false;
		this.saved = saved;

		String input, action, title;

		String[] parts;

		do {
			title = "";
			input = Methods.getValidInput(interactMenu(d));

			parts = input.split(" ");

			action = parts[0];
			action = action.toLowerCase();

			if (parts.length > 1) {
				for (int i = 1; i < parts.length; i++) {
					title += parts[i] + " ";
				}
				title = title.trim();
				interactWith(action, title, d);
			} else {
				interactWith(action, d);
			}
		} while (!back);

		return this.saved;
	}
	/**
	 * 	Interaction with user to either add, remove or edit events
	 * 	or return to menu.
	 * @param d - Viewed date
	 * @return returns menu
	 */
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
	
	/**
	 * Takes action according to input. With event
	 * title input.
	 * @param action - Add, Remove or Edit event
	 * @param title - Event Title
	 * @param d - Date
	 * @return returns boolean saved
	 */
	private boolean interactWith(String action, String title, Date d) {

		Methods.clearScreen();

		switch (action) {
		case REMOVE:
			removeEvent(title, d);
			saved = false;
			break;
		case EDIT:
			editEvent(title, d);
			saved = false;
			break;
		default:
			Methods.pauseOn("Please enter an input provided.", true);
		}

		return saved;
	}

	/**
	 * Takes action according to input.
	 * @param action
	 * @param d
	 * @return returns boolean saved
	 */
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
	
	/**
	 * Adds new event to ArrayList<Event> events.
	 * @param d - Date
	 */
	public void addEvent(Date d) {
		e = new Event(d);
		e.displayAll();
		events.add(e);
	}

	/**
	 * Edits any given event with entered title.
	 * @param title - event title
	 * @param d - date
	 */
	public void editEvent(String title, Date d) {
		for (Event e : getEventsOn(d)) {
			if (e.getEventTitle().equalsIgnoreCase(title)) {
				e.setAll();
				e.displayAll();
			}
		}
	}

	/**
	 * Removes any given event with entered tile.
	 * @param title - event title
	 * @param d - date
	 */
	public void removeEvent(String title, Date d) {
		for (int i = 0; i < getEventsOn(d).size(); i++) {
			Event e = getEventsOn(d).get(i);
			if (e.getEventTitle().equalsIgnoreCase(title)) {
				events.remove(i);
				i--;
			}
		}
	}

	/**
	 * Gets the total amount of events on any given date.
	 * @param d - date
	 * @return returns getEventsOn(d).size();
	 */
	public int getEventAmount(Date d) {
		return getEventsOn(d).size();
	}

	/**
	 * Retrieves priority level.
	 * @param d - date
	 * @return returns priorities
	 */
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

	/**
	 * Gets Events on a any given date.
	 * @param d - date
	 * @return returns eventsOnDay
	 */
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

	/**
	 * Adds Event for combine method in Calendar.
	 * @param event
	 */
	public void addEvent(Event event) {
		events.add(event);
	}
}
