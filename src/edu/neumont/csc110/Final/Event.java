package edu.neumont.csc110.Final;

import java.util.Scanner;

public class Event {
	Scanner in = new Scanner(System.in);
	private int startHours, startMinutes, endHours, endMinutes, frequency, significance;
	private EventType occurence;
	private PriorityType importance;
	private String description;
	private boolean valid, yesNo;

	public Event() {

	}

	public void SetStartTime() {
		startHours = EditHours();
		startMinutes = EditMinutes();
	}

	public void SetEndTime() {
		endHours = EditHours();
		endMinutes = EditMinutes();
	}

	private int EditHours() {
		int hours;
		
		
		return hours;
	}
	
	private int EditMinutes(){
		int minutes;
		
		
		return minutes;
	}

	public void SetDescription() {
		valid = false;
		
		do{
			System.out.println("Please describe the event.");
			description = in.nextLine();
			
			yesNo = Methods.getConfirmation("You entered : \n" + description + "\n\nIs this correct?");	
		}while (!yesNo);
	}

	public int SetEventOccurence() {
		valid = false;

		do {
			System.out.println(
					"How often will this event happen? Enter the number corrosponding to how often the event occurs.");
			System.out.println("[1 - Once] [2 - Daily] [3 - Weekly] [4 - Monthly] [5 - Yearly]");

			if (in.hasNextInt()) {
				frequency = in.nextInt();
				if (frequency >= 1 && frequency <= 5) {
					valid = true;
				} else {
					System.out.println("Input out of range. Please try again.");
				}
			} else {
				System.out.println("Invalid input. Please try again.");
				in.nextLine();
			}
		} while (!valid);

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

		do {
			System.out.println(
					"How important is this event happen? Enter the number corrosponding to the level of importance.");
			System.out.println("[1 - Low] [2 - Medium] [3 - High]");

			if (in.hasNextInt()) {
				significance = in.nextInt();
				if (significance >= 1 && significance <= 5) {
					valid = true;
				} else {
					System.out.println("Input out of range. Please try again.");
				}
			} else {
				System.out.println("Invalid input. Please try again.");
				in.nextLine();
			}
		} while (!valid);

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
		System.out.println("Event Start Time - [" + startHours + ":" + startMinutes + "]");
		System.out.println("Event End Time - [" + endHours + ":" + endMinutes + "]");
	}
	
	public void DisplayDescription(){
		System.out.println("Description:");
		System.out.println(description);
	}
	
	public void DisplayEventType(){
		System.out.println("Event Occurrence: ");
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
	
	public void DisplayEventPriority(){
		System.out.println("Event Priority: ");
		switch (significance) {
		case 1:
			System.out.println("Low Priority");
			break;
		case 2:
			System.out.println("Medium Priority");
			break;
		case 3:
			System.out.println("High Priority");
			break;
		}
	}
}