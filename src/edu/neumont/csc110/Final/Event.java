package edu.neumont.csc110.Final;

import java.util.Scanner;

public class Event {
	private static final int MAX_MINUTES = 59, MIN_MINUTES = 0, MAX_HOURS = 12, MIN_HOURS = 0;
	Scanner in = new Scanner(System.in);
	private int startHours, startMinutes, endHours, endMinutes, frequency, significance;
	private EventType occurence;
	private PriorityType importance;
	private String description, startAMPM, endAMPM;
	private boolean valid, yesNo;

	public Event() {

	}

	public void SetStartTime() {
		System.out.println("[Start Time]\n");
		startAMPM = EditTimeConventions();
		startHours = EditHours();
		startMinutes = EditMinutes();
	}

	public void SetEndTime() {
		System.out.println("[End Time]\n");
		endAMPM = EditTimeConventions();
		endHours = EditHours();
		endMinutes = EditMinutes();
	}

	private String EditTimeConventions() {
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

	private int EditHours() {
		int hours;

		hours = Methods.getValidInteger("What is the hours portion of the time?", MIN_HOURS, MAX_HOURS);

		return hours;
	}

	private int EditMinutes() {
		int minutes;

		minutes = Methods.getValidInteger("What is the minutes portion of the time?", MIN_MINUTES, MAX_MINUTES);

		return minutes;
	}

	public void SetDescription() {
		valid = false;

		do {
			description = Methods.getValidInput("Please describe the event.");

			yesNo = Methods.getConfirmation("You entered : \n" + description + "\n\nIs this correct?");
		} while (!yesNo);
	}

	public int SetEventOccurence() {
		valid = false;

		frequency = Methods.getValidInteger(
				"How often will this event happen? Enter the number corrosponding to how often the event occurs.\n[1 - Once] [2 - Daily] [3 - Weekly] [4 - Monthly] [5 - Yearly]",
				1,
				5);

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
		}
		return frequency;
	}

	public int SetPriorityLevel() {
		valid = false;

		significance = Methods.getValidInteger(
				"How important is this event happen? Enter the number corrosponding to the level of importance.\n[1 - Low] [2 - Medium] [3 - High]",
				1,
				3);

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
		}

		return significance;
	}

	public void DisplayTimes() {
		System.out.println("\nEvent Start Time - [" + startHours + ":" + startMinutes + " " + startAMPM + "]");
		System.out.println("\nEvent End Time - [" + endHours + ":" + endMinutes + " " + endAMPM + "]");
	}

	public void DisplayDescription() {
		System.out.println("\nDescription:");
		System.out.println(description);
	}

	public void DisplayEventType() {
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
		}
	}

	public void DisplayEventPriority() {
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
		}
	}
}