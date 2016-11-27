package edu.neumont.csc110.Final;

import java.io.Serializable;
import java.util.ArrayList;

public class Calendar implements Serializable{
	
	private static final String DAY = "day", WEEK = "week", MONTH = "month", BI_MONTH = "bi-month", BACK = "back";
	
	private boolean back;
	
	private String name;

	private ArrayList<Day> dates;

	public Calendar(String name) {
		back = false;
		
		this.name = name;
	}
	
	public void veiw() {
		String input;

		do {
			input = Methods.getValidInput(veiwMenu());
			input = input.toLowerCase();

			veiwAction(input);

		} while (!back);
	}

	private String veiwMenu() {
		String menu = "";
		menu += "What would you like to do?";
		menu += "\t\t";
		menu += "Current calendar: " + name;
		menu += "\n\n";
		menu += "\tInput - result\n\n";
		menu += "\t" + DAY + " - veiw a day\n";
		menu += "\t" + WEEK + " - veiw a week\n";
		menu += "\t" + MONTH + " - veiw a month\n";
		menu += "\t" + BI_MONTH + " - veiw two months\n";
		menu += "\t" + BACK + " - return to the file menu\n";
		return menu;
	}

	private void veiwAction(String action) {
		switch (action) {
		case DAY:
			veiwDay();
			break;
		case WEEK:
			veiwWeek();
			break;
		case MONTH:
			veiwMonth();
			break;
		case BI_MONTH:
			veiwBiMonth();
			break;
		case BACK:
			back = true;
			break;
		default:
			Methods.pauseOn("Please enter an input provided.", true);
		}
	}

	private void veiwDay() {
		
	}

	private void veiwWeek() {
		
	}

	private void veiwMonth() {
		
	}

	private void veiwBiMonth() {
		
	}

	private void addEvent(Event e) {
		unimplemented();

	}

	private void unimplemented() {
		Methods.pauseOn("UNINPLEMENTED", true);
	}

	public void Combine(Calendar C) {
		for (Day d : C.getDates()) {
			for (Event e : d.getEvents()) {
				addEvent(e);
			}
		}
	}

	private ArrayList<Day> getDates() {
		return dates;
	}
}
