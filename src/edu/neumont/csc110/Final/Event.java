package edu.neumont.csc110.Final;

import java.util.Calendar;
import java.util.Date;

public class Event {
	private static final int MAX_MINUTES = 59, MIN_MINUTES = 0, MAX_HOURS = 12, MIN_HOURS = 0;
	private int startHours, startMinutes, endHours, endMinutes, frequency, significance;
	private EventType occurence;
	private PriorityType importance;
	private String description, startAMPM, endAMPM;
	private boolean valid, yesNo;
	private Calendar eventDate;

	public Event(){
		
	}

	public void editEventDate(){
		eventDate = Methods.getValidDateInput("What date does this event occur on?");
	}
	
	public void editStartTime() {
		System.out.println("\n[Start Time]\n");
		startAMPM = editTimeConventions();
		startHours = editHours();
		startMinutes = editMinutes();
	}

	public void editEndTime() {
		System.out.println("\n[End Time]\n");
		endAMPM = editTimeConventions();
		endHours = editHours();
		endMinutes = editMinutes();
	}

	private String editTimeConventions() {
		String amPM;
		valid = false;

		do {

			amPM = Methods.getValidInput("What time convention do you want to use? Enter am or pm.");
			amPM = amPM.toLowerCase();
			if (amPM.equals("am") || amPM.equals("pm")) {
				valid = true;
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		} while (!valid);

		return amPM;
	}

	private int editHours() {
		int hours;

		hours = Methods.getValidInteger("What is the hours portion of the time?", MIN_HOURS, MAX_HOURS);

		return hours;
	}

	private int editMinutes() {
		int minutes;

		minutes = Methods.getValidInteger("What is the minutes portion of the time?", MIN_MINUTES, MAX_MINUTES);

		return minutes;
	}

	public void editDescription() {
		valid = false;

		do {
			description = Methods.getValidInput("\nPlease describe the event.");

			yesNo = Methods.getConfirmation("\nYou entered : \n" + description + "\n\nIs this correct?");
		} while (!yesNo);
	}

	public void editEventOccurence() {
		valid = false;

		frequency = Methods.getValidInteger("\nHow often will this event happen? Enter the number corrosponding to "
				+ "how often the event occurs.\n[1 - Once] [2 - Daily] [3 - Weekly] [4 - Monthly] [5 - Yearly]", 1, 5);

		switch (frequency) {
		case 1:
			occurence = EventType.Once;
			break;
		case 2:
			occurence = EventType.Daily;
			break;
		case 3:
			occurence = EventType.Weekly;
			break;
		case 4:
			occurence = EventType.Monthly;
			break;
		case 5:
			occurence = EventType.Yearly;
			break;
		default:
			Methods.pauseOn("Something went wrong - SetEventOccurence()", true);
		}
	}

	public void editPriorityLevel() {
		valid = false;

		significance = Methods.getValidInteger("\nHow important is this event happen? Enter the number "
				+ "corrosponding to the level of importance.\n[1 - Low] [2 - Medium] [3 - High]", 1, 3);

		switch (significance) {
		case 1:
			importance = PriorityType.LowImportance;
			break;
		case 2:
			importance = PriorityType.MediumImportance;
			break;
		case 3:
			importance = PriorityType.HighImportance;
			break;
		default:
			Methods.pauseOn("Something went wrong - SetPriorityLevel()", true);
		}
	}
	
	public void editAll(){
		editEventDate();
		editStartTime();
		editEndTime();
		editDescription();
		editEventOccurence();
		editPriorityLevel();
	}
	
	public void displayEventDate(){
		System.out.println("\nEvent Date:");
		System.out.println(Methods.getNewDateString());
	}
	
	public void displayTimes() {
		System.out.println("\nEvent Start Time - [" + startHours + ":" + startMinutes + " " + startAMPM + "]");
		System.out.println("\nEvent End Time - [" + endHours + ":" + endMinutes + " " + endAMPM + "]");
	}

	public void displayDescription() {
		System.out.println("\nDescription:");
		System.out.println(description);
	}

	public void displayEventType() {
		System.out.println("\nEvent Occurrence: ");
		switch (frequency) {
		case 1:
			System.out.println("Once");
			break;
		case 2:
			System.out.println("Daily");
			break;
		case 3:
			System.out.println("Weekly");
			break;
		case 4:
			System.out.println("Monthly");
			break;
		case 5:
			System.out.println("Yearly");
			break;
		default:
			Methods.pauseOn("Something went wrong - DisplayEventType()", true);
		}
	}

	public void displayEventPriority() {
		System.out.println("\nEvent Priority: ");
		switch (significance) {
		case 1:
			System.out.println("Low");
			break;
		case 2:
			System.out.println("Medium");
			break;
		case 3:
			System.out.println("High");
			break;
		default:
			Methods.pauseOn("Something went wrong - DisplayEventPriority()", true);
		}
	}
	
	public void displayAll() {
		displayEventDate();
		displayTimes();
		displayDescription();
		displayEventType();
		displayEventPriority();
	}
	
	public EventType getEventOccurence(){
		return occurence;
	}
	
	public PriorityType getEventPriority(){
		return importance;
	}
	
	public CalendargetDate(){
		return eventDate;
	}
}