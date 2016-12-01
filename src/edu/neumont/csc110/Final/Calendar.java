package edu.neumont.csc110.Final;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

@SuppressWarnings("deprecation")
public class Calendar extends GregorianCalendar implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int DAYS_IN_A_WEEK = 7;

	private static final int BI_MONTH_I = 0, MONTH_I = 1, WEEK_I = 2;

	private static final int[] WIDTH = { 6, 9, 11 }, HEIGHT = { 2, 3, 4 }, VEIW_ROWS = { 6, 6, 1 };

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
	}

	public boolean veiw() {
		back = false;
		
		String input;

		do {
			input = Methods.getValidInput(veiwMenu());
			input = input.toLowerCase();

			saved = veiwAction(input);

		} while (!back);

		return saved;
	}

	private String veiwMenu() {
		String menu = "";
		menu += "What would you like to do?";
		menu += "\t\t";
		//menu += "Current calendar: " + name;
		menu += "\n\n";
		menu += "\tInput - result\n\n";
		menu += "\t" + BI_MONTH_S + " - veiw two months\n";
		menu += "\t" + MONTH_S + " - veiw a month\n";
		menu += "\t" + WEEK_S + " - veiw a week\n";
		menu += "\t" + DAY_S + " - veiw and edit a day\n";
		menu += "\t" + BACK + " - return to the file menu\n";
		return menu;
	}

	private boolean veiwAction(String action) {
		Date start;
		switch (action) {
		case BI_MONTH_S:
		case MONTH_S:
		case WEEK_S:
			display.clear();
		case DAY_S:
			start = Methods.getValidDateInput(
					"When would you like the veiw to start?\nDates will be shifted backwards to fit the veiw.\nEx: 11/12/2016 will be shifted to 11/01/2016 for veiwing a month.");
			setTime(start);
		default:
			// nothin'
		}

		Methods.clearScreen();
		
		switch (action) {
		case BI_MONTH_S:
			add(DAY_OF_MONTH, -(get(DAY_OF_MONTH) - 1));
			complete();
			veiwBiMonth();
			break;
		case MONTH_S:
			add(DAY_OF_MONTH, -(get(DAY_OF_MONTH) - 1));
			complete();
			veiwMonth();
			break;
		case WEEK_S:
			add(DAY_OF_WEEK, -(get(DAY_OF_WEEK) - 1));
			complete();
			veiwWeek();
			break;
		case DAY_S:
			complete();
			saved = EH.veiwDay(getTime());
			break;
		case BACK:
			back = true;
			break;
		default:
			Methods.pauseOn("Please enter an input provided.", true);
		}

		return saved;
	}

	private void veiwBiMonth() {
		display.add("Calendar " + name);
		display.add("");
		display.add(getMonthTitle(BI_MONTH_I));
		display.add(verticalLine(BI_MONTH_I) + SPACER + verticalLine(BI_MONTH_I));
		display.add("|Sun.  |Mon.  |Tue.  |Wed.  |Thur. |Fri.  |Sat.  |" + SPACER + "|Sun.  |Mon.  |Tue.  |Wed.  |Thur. |Fri.  |Sat.  |");
		display.add(verticalLine(BI_MONTH_I) + SPACER + verticalLine(BI_MONTH_I));
		createDisplay(BI_MONTH_I, display.size());

		display();
		System.out.println();
	}

	private void veiwMonth() {
		display.add("Calendar " + name + " - " + getMonthTitle(MONTH_I));
		display.add(" --Sun.--- --Mon.--- --Tue.--- --Wed.--- --Thur.-- --Fri.--- --Sat.---");
		createDisplay(MONTH_I, display.size());

		display();
		System.out.println();
	}

	private void veiwWeek() {
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
	
	private String getMonthTitle(int veiwType) {
		String monthTitle = "";
		switch (veiwType) {
		case BI_MONTH_I:
			monthTitle = getDisplayName(MONTH, LONG_STANDALONE, Locale.US) + " " + get(YEAR);
			for (int i = monthTitle.length(); monthTitle.length() < (WIDTH[veiwType] + 1) * DAYS_IN_A_WEEK + 1; i++) {
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

	private void createDisplay(int veiwType, int startRow) {
		int startDay = get(DAY_OF_WEEK) - 1;
		int startMonth = get(MONTH);
		for (int i = startRow; i - startRow < (HEIGHT[veiwType] + 1) * VEIW_ROWS[veiwType]; i += HEIGHT[veiwType] + 1) {
			for (int j = 0; j < HEIGHT[veiwType]; j++) {
				display.add("");
			}

			singleRow(veiwType, startRow, startDay, startMonth, i);
			display.add(verticalLine(veiwType) + SPACER);
		}
		if (veiwType == BI_MONTH_I) {
			startDay = get(DAY_OF_WEEK) - 1;
			startMonth = get(MONTH);
			for (int i = startRow; i - startRow < (HEIGHT[veiwType] + 1) * VEIW_ROWS[veiwType]; i += HEIGHT[veiwType] + 1) {

				singleRow(veiwType, startRow, startDay, startMonth, i);
				display.set(i + HEIGHT[veiwType], display.get(i + HEIGHT[veiwType]) + verticalLine(veiwType));
			}
		}
		
	}

	private void singleRow(int veiwType, int startRow, int startDay, int startMonth, int i) {
		for (int j = 0; j < DAYS_IN_A_WEEK; j++) {
			if (j < startDay && i == startRow || (get(MONTH) != startMonth && veiwType != WEEK_I)) {
				blankDay(veiwType, i);
			} else {
				createDisplayDay(veiwType, i);
			}
		}
		for (int k = 0; k < HEIGHT[veiwType]; k++) {
			display.set(i + k, display.get(i + k) + "|" + SPACER);
		}
	}

	private void blankDay(int veiwType, int i) {
		for (int k = 0; k < HEIGHT[veiwType]; k++) {
			setLine(veiwType, i + k, "");
		}
	}

	private void createDisplayDay(int veiwType, int i) {
		setLine(veiwType, i, EVENT_DISP[veiwType] + getEventAmount());
		if (veiwType != BI_MONTH_I) {
			setLine(veiwType, i + 1, PRIORITY_DISP[veiwType]);			
		}
		switch (veiwType) {
		case BI_MONTH_I:
			setLine(veiwType, i + 1, dayNumber(veiwType, getPriorities(getTime(), veiwType)));
			break;
		case MONTH_I:
			setLine(veiwType, i + 2, dayNumber(veiwType, getPriorities(getTime(), veiwType)));
			break;
		case WEEK_I:
			setLine(veiwType, i + 2, getPriorities(getTime(), veiwType));
			setLine(veiwType, i + 3, dayNumber(veiwType, ""));
			break;
		default:
		}
		add(DAY_OF_MONTH, 1);
		complete();
	}
	
	private void setLine(int veiwType, int i, String string) {
		for (int j = string.length(); string.length() < WIDTH[veiwType]; j++) {
			string += " ";
		}
		display.set(i, display.get(i) + "|" + string);
	}

	private String dayNumber(int veiwType, String string) {
		for (int i = string.length(); i < WIDTH[veiwType] - 2; i++) {
			string += " ";
		}
		return string + get(DAY_OF_MONTH);
	}

	private String getEventAmount() {
		return "" + EH.getEventAmount(getTime());
	}

	private String getPriorities(Date time, int veiwType) {
		String temp = "LMH", priorities = "";
		temp = EH.getPriorities(getTime());
		switch (veiwType) {
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
		return priorities;
	}

	private String verticalLine(int veiwType) {
		String line = " ";
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < WIDTH[veiwType]; j++) {
				line += "-";
			}
			line += " ";
		}
		return line;
	}

	private void addEvent(Event e) {
		unimplemented();

	}

	private void unimplemented() {
		Methods.pauseOn("UNINPLEMENTED", true);
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
