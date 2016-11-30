package edu.neumont.csc110.Final;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("deprecation")
public class Calendar extends java.util.Calendar implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int BI_MONTH_I = 2, MONTH_I = 1, WEEK_I = 0, SPACER_LENGTH = 7;

	private static final int[] WIDTH = { 6, 9, 11 }, HEIGHT = { 4, 3, 2 };

	private static final String[] EVENT_DISP = { "E#:", "Event:", "Events: " },
			PRIORITY_DISP = { "", "Priority:", "Priorities:" };

	private static final String DAY = "day", WEEK = "week", MONTH = "month", BI_MONTH = "bi-month", BACK = "back";

	private boolean back;

	private String name;

	private EventHandler EH;

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
		menu += "\t" + BI_MONTH + " - veiw two months\n";
		menu += "\t" + MONTH + " - veiw a month\n";
		menu += "\t" + WEEK + " - veiw a week\n";
		menu += "\t" + DAY + " - veiw a day\n";
		menu += "\t" + BACK + " - return to the file menu\n";
		return menu;
	}

	private void veiwAction(String action) {
		Date start;
		switch (action) {
		case BI_MONTH:
		case MONTH:
		case WEEK:
		case DAY:
			start = Methods.getValidDateInput("When would you like the veiw to start?\nDates will be shifted backwards to fit the veiw.\nEx: 11/12/2016 will be shifted to 11/01/2016 for veiwing a month.");
			setTime(start);
		default:
			//nothin'
		}
		
		switch (action) {
		case BI_MONTH:
			set(DAY_OF_MONTH, 1);
			veiwBiMonth();
			break;
		case MONTH:
			set(DAY_OF_MONTH, 1);
			veiwMonth();
			break;
		case WEEK:
			set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
			veiwWeek();
			break;
		case DAY:
			EH.veiwDay(getTime());
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

	@Override
	public void add(int field, int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void computeFields() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void computeTime() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getGreatestMinimum(int field) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLeastMaximum(int field) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaximum(int field) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinimum(int field) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void roll(int field, boolean up) {
		// TODO Auto-generated method stub
		
	}
}
