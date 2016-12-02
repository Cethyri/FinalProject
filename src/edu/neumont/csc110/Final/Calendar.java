package edu.neumont.csc110.Final;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Calendar extends GregorianCalendar implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int DAYS_IN_A_WEEK = 7;

	private static final int BI_MONTH_I = 0, MONTH_I = 1, WEEK_I = 2;

	private static final int[] WIDTH = { 6, 9, 11 }, HEIGHT = { 2, 3, 4 }, view_ROWS = { 6, 6, 1 };

	private static final char LOW = 'L', MEDIUM = 'M', HIGH = 'H';

	private static final String[] EVENT_DISP = { "E#:", "Event:", "Events: " },
			PRIORITY_DISP = { "", "Priority:", "Priorities:" };

	private static final String DAY_S = "day", WEEK_S = "week", MONTH_S = "month", BI_MONTH_S = "bi-month",
			BACK = "back", SPACER = "       ";

	private boolean back, saved;

	private String name;

	private ArrayList<String> display;

	private EventHandler EH;

	public Calendar(String name) {
		back = false;

		saved = true;

		this.name = name;

		display = new ArrayList<String>();

		EH = new EventHandler();
	}

	public boolean view(boolean saved) {
		back = false;
		this.saved = saved;

		String input;

		do {
			input = Methods.getValidInput(viewMenu());
			input = input.toLowerCase();

			viewAction(input);

		} while (!back);

		return this.saved;
	}

	private String viewMenu() {
		String menu = "";
		menu += "What would you like to do?";
		menu += "\t\t";
		menu += "\n\n";
		menu += "\tInput - result\n\n";
		menu += "\t" + BI_MONTH_S + " - view two months\n";
		menu += "\t" + MONTH_S + " - view a month\n";
		menu += "\t" + WEEK_S + " - view a week\n";
		menu += "\t" + DAY_S + " - view and edit a day\n";
		menu += "\t" + BACK + " - return to the file menu\n";
		return menu;
	}

	private void viewAction(String action) {
		Date start;
		switch (action) {
		case BI_MONTH_S:
		case MONTH_S:
		case WEEK_S:
			display.clear();
		case DAY_S:
			start = Methods.getValidDateInput(
					"When would you like the view to start?\nDates will be shifted backwards to fit the view.\nEx: 11/12/2016 will be shifted to 11/01/2016 for viewing a month.");
			setTime(start);
		default:
			// nothin'
		}

		Methods.clearScreen();

		switch (action) {
		case BI_MONTH_S:
			add(DAY_OF_MONTH, -(get(DAY_OF_MONTH) - 1));
			complete();
			viewBiMonth();
			break;
		case MONTH_S:
			add(DAY_OF_MONTH, -(get(DAY_OF_MONTH) - 1));
			complete();
			viewMonth();
			break;
		case WEEK_S:
			add(DAY_OF_WEEK, -(get(DAY_OF_WEEK) - 1));
			complete();
			viewWeek();
			break;
		case DAY_S:
			complete();
			saved = EH.viewDay(getTime(), saved);
			break;
		case BACK:
			back = true;
			break;
		default:
			Methods.pauseOn("Please enter an input provided.", true);
		}
	}

	private void viewBiMonth() {
		display.add("Calendar " + name);
		display.add("");
		display.add(getMonthTitle(BI_MONTH_I));
		display.add(verticalLine(BI_MONTH_I) + SPACER + verticalLine(BI_MONTH_I));
		display.add(
				"|Sun.  |Mon.  |Tue.  |Wed.  |Thur. |Fri.  |Sat.  |" + SPACER
						+ "|Sun.  |Mon.  |Tue.  |Wed.  |Thur. |Fri.  |Sat.  |");
		display.add(verticalLine(BI_MONTH_I) + SPACER + verticalLine(BI_MONTH_I));
		createDisplay(BI_MONTH_I, display.size());

		display();
		System.out.println();
	}

	private void viewMonth() {
		display.add("Calendar " + name + " - " + getMonthTitle(MONTH_I));
		display.add(" --Sun.--- --Mon.--- --Tue.--- --Wed.--- --Thur.-- --Fri.--- --Sat.---");
		createDisplay(MONTH_I, display.size());

		display();
		System.out.println();
	}

	private void viewWeek() {
		display.add("Calendar " + name);
		display.add("");
		display.add(getMonthTitle(WEEK_I));
		display.add("");
		display.add(" Sunday      Monday      Tuesday     Wednesday   Thursday    Friday      Saturday");
		display.add(verticalLine(WEEK_I));
		createDisplay(WEEK_I, display.size());

		display();
		System.out.println();
	}

	private String getMonthTitle(int viewType) {
		String monthTitle = "";
		switch (viewType) {
		case BI_MONTH_I:
			monthTitle = getDisplayName(MONTH, LONG_STANDALONE, Locale.US) + " " + get(YEAR);
			for (int i = monthTitle.length(); i < (WIDTH[viewType] + 1) * DAYS_IN_A_WEEK + 1; i++) {
				monthTitle += " ";
			}
			monthTitle += SPACER;
			add(MONTH, 1);
			monthTitle += getDisplayName(MONTH, LONG_STANDALONE, Locale.US) + " " + get(YEAR);
			add(MONTH, -1);
			break;
		case MONTH_I:
			monthTitle = getDisplayName(MONTH, LONG_STANDALONE, Locale.US) + " " + get(YEAR);
			break;
		case WEEK_I:
			monthTitle = getDisplayName(MONTH, LONG_STANDALONE, Locale.US) + " " + get(YEAR);
			int startMonth = get(MONTH);
			add(DAY_OF_MONTH, 7);
			if (get(MONTH) != startMonth) {
				monthTitle += " - " + getDisplayName(MONTH, LONG_STANDALONE, Locale.US) + " " + get(YEAR);
			}
			add(DAY_OF_MONTH, -7);
			break;
		default:
		}
		return monthTitle;
	}

	private void display() {
		for (String s : display) {
			System.out.println(s);
		}
	}

	private void createDisplay(int viewType, int startRow) {
		int startDay = get(DAY_OF_WEEK) - 1;
		int startMonth = get(MONTH);
		for (int i = startRow; i - startRow < (HEIGHT[viewType] + 1) * view_ROWS[viewType]; i += HEIGHT[viewType] + 1) {
			for (int j = 0; j < HEIGHT[viewType]; j++) {
				display.add("");
			}

			singleRow(viewType, startRow, startDay, startMonth, i);
			display.add(verticalLine(viewType) + SPACER);
		}
		if (viewType == BI_MONTH_I) {
			startDay = get(DAY_OF_WEEK) - 1;
			startMonth = get(MONTH);
			for (int i = startRow; i - startRow < (HEIGHT[viewType] + 1) * view_ROWS[viewType]; i += HEIGHT[viewType]
					+ 1) {

				singleRow(viewType, startRow, startDay, startMonth, i);
				display.set(i + HEIGHT[viewType], display.get(i + HEIGHT[viewType]) + verticalLine(viewType));
			}
		}

	}
	
	// CS110 Requirement 11: Javadoc Comment Syntax
	/**
	 * creates a single row (one week) of the display
	 * 
	 * @param viewType determines how to format the row, is one of BI_MONTH_I, MONTH_I, WEEK_I
	 * @param startRow what row of the display this started on
	 * @param startDay first day that should display
	 * @param startMonth what month the display starts on (for week or bi-month)
	 * @param i the current row
	 */
	private void singleRow(int viewType, int startRow, int startDay, int startMonth, int i) {
		for (int j = 0; j < DAYS_IN_A_WEEK; j++) {
			if (j < startDay && i == startRow || (get(MONTH) != startMonth && viewType != WEEK_I)) {
				blankDay(viewType, i);
			} else {
				createDisplayDay(viewType, i);
			}
		}
		for (int k = 0; k < HEIGHT[viewType]; k++) {
			display.set(i + k, display.get(i + k) + "|" + SPACER);
		}
	}

	private void blankDay(int viewType, int i) {
		for (int k = 0; k < HEIGHT[viewType]; k++) {
			setLine(viewType, i + k, "");
		}
	}

	private void createDisplayDay(int viewType, int i) {
		setLine(viewType, i, getEventAmount() != 0 ? EVENT_DISP[viewType] + getEventAmount() : "");
		if (viewType != BI_MONTH_I) {
			setLine(viewType, i + 1, getEventAmount() != 0 ? PRIORITY_DISP[viewType] : "");
		}
		switch (viewType) {
		case BI_MONTH_I:
			setLine(viewType, i + 1, dayNumber(viewType, getPriorities(getTime(), viewType)));
			break;
		case MONTH_I:
			setLine(viewType, i + 2, dayNumber(viewType, getPriorities(getTime(), viewType)));
			break;
		case WEEK_I:
			setLine(viewType, i + 2, getPriorities(getTime(), viewType));
			setLine(viewType, i + 3, dayNumber(viewType, ""));
			break;
		default:
		}
		add(DAY_OF_MONTH, 1);
		complete();
	}

	private void setLine(int viewType, int i, String string) {
		for (int j = string.length(); j < WIDTH[viewType]; j++) {
			string += " ";
		}
		display.set(i, display.get(i) + "|" + string);
	}

	private String dayNumber(int viewType, String string) {
		for (int i = string.length(); i < WIDTH[viewType] - 2; i++) {
			string += " ";
		}
		return string + get(DAY_OF_MONTH);
	}

	private int getEventAmount() {
		return EH.getEventAmount(getTime());
	}

	private String getPriorities(Date time, int viewType) {
		String temp = "LMH", priorities = "";
		temp = EH.getPriorities(getTime());
		switch (viewType) {
		case BI_MONTH_I:
			priorities = temp;
			break;
		case MONTH_I:
			for (int i = 0; i < temp.length(); i++) {
				switch (temp.charAt(i)) {
				case LOW:
					priorities += "L ";
					break;
				case MEDIUM:
					priorities += "M ";
					break;
				case HIGH:
					priorities += "H ";
					break;
				default:
				}
			}
			break;
		case WEEK_I:
			for (int i = 0; i < temp.length(); i++) {
				switch (temp.charAt(i)) {
				case LOW:
					priorities += "LOW ";
					break;
				case MEDIUM:
					priorities += "MED ";
					break;
				case HIGH:
					priorities += "HI ";
					break;
				default:
				}
			}
			break;
		default:
		}
		return getEventAmount() != 0 ? priorities : "";
	}

	private String verticalLine(int viewType) {
		String line = " ";
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < WIDTH[viewType]; j++) {
				line += "-";
			}
			line += " ";
		}
		return line;
	}

	public void Combine(Calendar C) {
		for (Event e : C.getDates().getEvents()) {
			EH.addEvent(e);
		}
	}

	private EventHandler getDates() {
		return EH;
	}
}
