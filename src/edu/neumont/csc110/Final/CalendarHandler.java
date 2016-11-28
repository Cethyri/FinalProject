package edu.neumont.csc110.Final;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("deprecation")
public class CalendarHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int BI_MONTH_I = 2, MONTH_I = 1, WEEK_I = 0, SPACER_LENGTH = 7;

	private static final int[] WIDTH = { 6, 9, 11 }, HEIGHT = { 4, 3, 2 };

	private static final String[] EVENT_DISP = { "E#:", "Event:", "Events: " },
			PRIORITY_DISP = { "", "Priority:", "Priorities:" };

	private static final String DAY = "day", WEEK = "week", MONTH = "month", BI_MONTH = "bi-month", BACK = "back";

	private boolean back;

	private String name;

	private Date start;

	private EventHandler EH;

	public CalendarHandler(String name) {
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
		menu += "\t" + BI_MONTH + " - veiw two months\n";
		menu += "\t" + MONTH + " - veiw a month\n";
		menu += "\t" + WEEK + " - veiw a week\n";
		menu += "\t" + DAY + " - veiw a day\n";
		menu += "\t" + BACK + " - return to the file menu\n";
		return menu;
	}

	private void veiwAction(String action) {
		switch (action) {
		case BI_MONTH:
		case MONTH:
		case WEEK:
		case DAY:
			start = Methods.getValidDateInput("When would you like the veiw to start");
		default:
			//nothin'
		}

		switch (action) {
		case BI_MONTH:
			veiwBiMonth();
			break;
		case MONTH:
			veiwMonth();
			break;
		case WEEK:
			veiwWeek();
			break;
		case DAY:
			EH.veiwDay(start);
			break;
		case BACK:
			back = true;
			break;
		default:
			Methods.pauseOn("Please enter an input provided.", true);
		}
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
		for (Event e : C.getDates().getEvents()) {
			addEvent(e);
		}
	}

	private EventHandler getDates() {
		return EH;
	}
}
