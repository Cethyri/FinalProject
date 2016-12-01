package edu.neumont.csc110.Final;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Event implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final int MAX_MINUTES = 59, MIN_MINUTES = 0, MAX_HOURS = 12, MIN_HOURS = 0,
			MAX_FREQUENCY = 5, MIN_FREQUENCY = 1, MAX_SIGNIFICANCE = 3, MIN_SIGNIFICANCE = 1;
	private int startHours, startMinutes, endHours, endMinutes;
	private EventType occurrence;
	private PriorityType importance;
	private String description, startAMPM, endAMPM, eventTitle, dateString;
	private boolean valid, yesNo;
	private Date eventDate;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	private DecimalFormat timeFormat = new DecimalFormat("00");
	
	public Event() {
		
	}
	
	public Event(int startHours, int startMinutes, int endHours, int endMinutes,
			int frequency, int significance, EventType occurrence, 
			PriorityType importance, String description, String startAMPM, 
			String endAMPM, String eventTitle, Date eventDate, String dateString){
		
		this.occurrence = occurrence;
		this.importance = importance;
		this.startAMPM = startAMPM;
		this.endAMPM = endAMPM;
		this.startHours = startHours;
		this.startMinutes = startMinutes;
		this.endHours = endHours;
		this.endMinutes = endMinutes;
		this.description = description;
		this.eventTitle = eventTitle;
		this.eventDate = eventDate;
		this.dateString = dateString;
	}
	public void editTitle(){
		eventTitle = Methods.getValidInput("What is the title for this event?");
	}
	
	public void editInitialEventDate(){
		eventDate = (Methods.getValidDateInput("What date does this event occur on?"));
		dateString = formatter.format(eventDate);
	}
	
	public void editNewEventDate(Date newDate){
		eventDate = newDate;
	}
	
	private void editStartTime() {
		System.out.println("\n[Start Time]\n");
		startAMPM = editTimeConventions();
		startHours = editHours();
		startMinutes = editMinutes();
	}

	private void editEndTime() {
		System.out.println("\n[End Time]\n");
		do {
			endAMPM = editTimeConventions();
			endHours = editHours();
			endMinutes = editMinutes();
			if ((startAMPM == endAMPM && startHours > endHours)
					|| (startAMPM == endAMPM && (startHours == endHours && startMinutes > endMinutes))
					|| (startAMPM.equals("pm") && endAMPM.equals("am"))) {
				valid = false;
				System.out.println(
						"You entered a time that would make the event end before it starts. Please try again.");
			}
		} while (!valid);

	}

	private String editTimeConventions() {
		String amPM;
		valid = false;

		do {

			amPM = Methods.getValidInput("Will this be am or pm.");
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
		int frequency = 0;
		
		frequency = Methods.getValidInteger("\nHow often will this event happen? Enter the number corrosponding to "
				+ "how often the event occurs.\n[1 - Once] [2 - Daily] [3 - Weekly] [4 - Monthly] [5 - Yearly]", MIN_FREQUENCY, MAX_FREQUENCY);

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
		int significance = 0;
		
		significance = Methods.getValidInteger("\nHow important is this event happen? Enter the number "
				+ "corrosponding to the level of importance.\n[1 - Low] [2 - Medium] [3 - High]", MIN_SIGNIFICANCE, MAX_SIGNIFICANCE);

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
		editInitialEventDate();
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
		System.out.println("\nEvent Start Time - [" + startHours + ":" + timeFormat.format(startMinutes) + " " + startAMPM + "]" 
				+ "\nEvent End Time - [" + endHours + ":" + timeFormat.format(endMinutes) + " " + endAMPM + "]");
	}

	private void displayDescription() {
		System.out.println("\nDescription:\n" + description);
	}

	private void displayEventType() {
		System.out.println("\nEvent Occurrence: ");
		System.out.println(occurrence.name() + "\n");
	}

	private void displayEventPriority() {
		System.out.println("\nEvent Priority: ");
		System.out.println(importance.name() + "\n");
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
	public String toString() {
		return "\nEvent Date: " + dateString + "\nTitle: " + eventTitle + "\nEvent Start Time - " + startHours + ":"
				+ timeFormat.format(startMinutes) + " " + startAMPM + "\nEvent End Time - " + endHours + ":" + timeFormat.format(endMinutes) + " " + endAMPM
				+ "\nDescription:\n\t" + description + "\nPriority Level - " + importance.name()
				+ "\nOccurrence Level - " + occurrence.name() + "\n";
	}

	public boolean checkReoccursOn(Date recursiveDate) {
		boolean checkDate = false;
		GregorianCalendar gcLocal = new GregorianCalendar();
		GregorianCalendar gcCheck = new GregorianCalendar();

		gcLocal.setTime(eventDate);
		gcCheck.setTime(recursiveDate);

		if (occurrence.equals(EventType.DAILY)) {
			
			checkDate = true;
		
		} else if (occurrence.equals(EventType.WEEKLY)
				&& (gcLocal.get(GregorianCalendar.DAY_OF_WEEK) == gcCheck.get(GregorianCalendar.DAY_OF_WEEK))) {
			
			checkDate = true;
			
		} else if (occurrence.equals(EventType.MONTHLY)) {
			
			if (gcLocal.get(GregorianCalendar.DAY_OF_MONTH) == gcCheck.get(GregorianCalendar.DAY_OF_MONTH)) {
				checkDate = true;
			} else if (gcLocal.get(GregorianCalendar.DAY_OF_MONTH) > gcCheck.get(GregorianCalendar.DAY_OF_MONTH)
					&& gcCheck.get(GregorianCalendar.DAY_OF_MONTH) == gcCheck
							.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)) {
				checkDate = true;
			} else if (gcLocal.get(GregorianCalendar.DAY_OF_MONTH) < gcCheck.get(GregorianCalendar.DAY_OF_MONTH)
					&& gcLocal.get(GregorianCalendar.DAY_OF_MONTH) == gcLocal
							.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)) {
				checkDate = true;			
			}
			
		} else if (occurrence.equals(EventType.YEARLY)) {
			
			if (gcLocal.get(GregorianCalendar.DAY_OF_MONTH) == gcCheck.get(GregorianCalendar.DAY_OF_MONTH)
					&& gcLocal.get(GregorianCalendar.MONTH) == gcCheck.get(GregorianCalendar.MONTH)) {
				checkDate = true;
			} else if (gcLocal.isLeapYear(gcLocal.get(GregorianCalendar.YEAR))
					&& gcLocal.get(GregorianCalendar.MONTH) == GregorianCalendar.FEBRUARY) {
				if (gcLocal.get(GregorianCalendar.DAY_OF_MONTH) > gcCheck.get(GregorianCalendar.DAY_OF_MONTH)
						&& gcCheck.get(GregorianCalendar.DAY_OF_MONTH) == gcCheck
								.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)) {
					checkDate = true;
				}
			}
			
		} else {
			checkDate = false;
		}

		return checkDate;
	}
}