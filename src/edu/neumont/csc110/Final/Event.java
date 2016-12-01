package edu.neumont.csc110.Final;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
	private static final int MAX_MINUTES = 59, MIN_MINUTES = 0, MAX_HOURS = 12, MIN_HOURS = 0;
	private int startHours, startMinutes, endHours, endMinutes, frequency, significance;
	private EventType occurrence;
	private PriorityType importance;
	private String description, startAMPM, endAMPM, eventTitle, dateString;
	private boolean valid, yesNo;
	private Date eventDate;
	
	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	
	
	public Event() {
		
	}
	
	public Event(int startHours, int startMinutes, int endHours, int endMinutes,
			int frequency, int significance, EventType occurrence, PriorityType importance,
			String description, String startAMPM, String endAMPM, String eventTitle, Date eventDate, String dateString){
		
		this.significance = significance;
		this.occurrence = occurrence;
		this.importance = importance;
		this.startAMPM = startAMPM;
		this.endAMPM = endAMPM;
		this.startHours = startHours;
		this.startMinutes = startMinutes;
		this.endHours = endHours;
		this.endMinutes = endMinutes;
		this.frequency = frequency;
		this.description = description;
		this.eventTitle = eventTitle;
		this.eventDate = eventDate;
		this.dateString = dateString;
	}
	public void editTitle(){
		eventTitle = Methods.getValidInput("What is the title for this event?");
	}
	
	public void editEventDate(){
		eventDate = (Methods.getValidDateInput("What date does this event occur on?"));
		dateString = formatter.format(eventDate);
	}
	
	private void editStartTime() {
		System.out.println("\n[Start Time]\n");
		startAMPM = editTimeConventions();
		startHours = editHours();
		startMinutes = editMinutes();
	}

	private void editEndTime() {
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
			} 
			else {
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

	private void editDescription() {
		valid = false;

		do {
			description = Methods.getValidInput("\nPlease describe the event.");

			yesNo = Methods.getConfirmation("\nYou entered : \n" + description + "\n\nIs this correct?");
		} while (!yesNo);
	}

	private void editEventOccurence() {
		valid = false;

		frequency = Methods.getValidInteger("\nHow often will this event happen? Enter the number corrosponding to "
				+ "how often the event occurs.\n[1 - Once] [2 - Daily] [3 - Weekly] [4 - Monthly] [5 - Yearly]", 1, 5);

		switch (frequency) {
		case 1:
			occurrence = EventType.ONCE;
			break;
		case 2:
			occurrence = EventType.DAILY;
			break;
		case 3:
			occurrence = EventType.WEEKLY;
			break;
		case 4:
			occurrence = EventType.MONTHLY;
			break;
		case 5:
			occurrence = EventType.YEARLY;
			break;
		default:
			Methods.pauseOn("Something went wrong - SetEventOccurence()", true);
		}
	}

	private void editPriorityLevel() {
		valid = false;

		significance = Methods.getValidInteger("\nHow important is this event happen? Enter the number "
				+ "corrosponding to the level of importance.\n[1 - Low] [2 - Medium] [3 - High]", 1, 3);

		switch (significance) {
		case 1:
			importance = PriorityType.LOW;
			break;
		case 2:
			importance = PriorityType.MEDIUM;
			break;
		case 3:
			importance = PriorityType.HIGH;
			break;
		default:
			Methods.pauseOn("Something went wrong - SetPriorityLevel()", true);
		}
	}
	
	public void editAll(){
		editEventDate();
		editTitle();
		editStartTime();
		editEndTime();
		editDescription();
		editEventOccurence();
		editPriorityLevel();
	}
	
	private void displayEventDate(){
		System.out.println("\nEvent Date:\n" + Methods.getLastDateString());
	}

	public void displayTitle(){
		System.out.println("\nTitle:\n" + eventTitle);
	}
	
	private void displayTimes() {
		System.out.println("\nEvent Start Time - [" + startHours + ":" + startMinutes + " " + startAMPM + "]" 
				+ "\nEvent End Time - [" + endHours + ":" + endMinutes + " " + endAMPM + "]");
	}

	private void displayDescription() {
		System.out.println("\nDescription:\n" + description);
	}

	private void displayEventType() {
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
			System.out.println("Monthly"); // discuss, get rid of
			break;
		case 5:
			System.out.println("Yearly");
			break;
		default:
			Methods.pauseOn("Something went wrong - DisplayEventType()", true);
		}
	}

	private void displayEventPriority() {
		System.out.println("\nEvent Priority: ");
		switch (significance) {
		case 1:
			System.out.println("Low\n");
			break;
		case 2:
			System.out.println("Medium\n");
			break;
		case 3:
			System.out.println("High\n");
			break;
		default:
			Methods.pauseOn("Something went wrong - DisplayEventPriority()", true);
		}
	}
	
	public void displayAll() {
		displayEventDate();
		displayTitle();
		displayTimes();
		displayDescription();
		displayEventType();
		displayEventPriority();
	}
	
	public EventType getEventOccurence(){
		return occurrence;
	}
	
	public PriorityType getEventPriority(){
		return importance;
	}
	
	public Date getDate(){
		return eventDate;
	}
	
	public String getEventTitle() {
		return eventTitle;
	}

	@Override
	public String toString(){
		return "\nEvent Date: " + dateString + "\nTitle: " + eventTitle
				+ "\nEvent Start Time - " + startHours + ":" + startMinutes + " " + startAMPM 
				+ "\nEvent End Time - " + endHours + ":" + endMinutes + " " + endAMPM
				+ "\nDescription:\n\t" + description + "\nPriority Level - " + importance.name() 
				+ "\nOccurrence Level - " + occurrence.name() + "\n";
	}

}