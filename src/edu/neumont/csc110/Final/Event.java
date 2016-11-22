package edu.neumont.csc110.Final;

import java.util.Scanner;

public class Event {
	Scanner in = new Scanner(System.in);
	Methods m = new Methods();
	private int startHours, startMinutes, endHours, endMinutes, frequency, significance;
	private EventType occurence;
	private PriorityType importance;
	private String description, yesNo;
	private boolean valid;

	public Event() {

	}

	public void SetStartTime() {
		startHours = EditTime();
		startMinutes = EditTime();
	}

	public void SetEndTime() {
		endHours = EditTime();
		endMinutes = EditTime();
	}

	private int EditTime() {
		int Time = 0;

		return Time;
	}

	public void DisplayTimes() {
		System.out.println("Event Start Time - [" + startHours + ":" + startMinutes + "]");
		System.out.println("Event End Time - [" + endHours + ":" + endMinutes + "]");
	}

	public void SetDescription() {
		valid = false;
		
		do{
			System.out.println("Please describe the event.");
			description = in.nextLine();
			
			yesNo = m.getConfirmation("You entered : \n" + description + "\n\nIs this correct?");	
		}while (yesNo == "n" || yesNo == "N");
	}
	
	public void DisplayDescription(){
		System.out.println("Description:");
		System.out.println(description);
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

	public void SetPriorityLevel() {
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
	}

}
