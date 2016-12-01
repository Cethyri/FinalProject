package edu.neumont.csc110.Final;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Methods {

	private static final int CHAR_VAL_NUM_MIN = 48, CHAR_VAL_NUM_MAX = 57;
	
	private static Scanner in = new Scanner(System.in);
	private static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	
	private static Boolean answer = false;
	private static String input;
	private static Date dateFormat;
	private static Calendar date;
	private static String newDateString;

	public Methods() {

	}

	public static String getValidInput(String question) {
		System.out.println(question);
		boolean valid = false;

		do {
			input = in.nextLine();

			if (!input.isEmpty()) {
				valid = true;
			} else {
				System.out.println("\nNo valid input. " + question);
			}
		} while (!valid);

		return input;
	}
	
	public static int getValidInteger(String question, int min, int max) {
		System.out.println(question);
		boolean valid = false;
		int num = 0;
		do {
			input = in.nextLine();

			input = onlyNum(input);

			if (!input.isEmpty()) {
				num = Integer.parseInt(input);

				if (num >= min && num <= max) {
					valid = true;
				} else {
					System.out.println("Out of range. " + question);
				}
			} else {
				System.out.println("No valid input. " + question);
			}
		} while (!valid);

		return num;
	}
	
	private static String onlyNum(String input) {
		String temp = "";
		for (int x = 0; x < input.length(); x++) {
			if (input.charAt(x) >= CHAR_VAL_NUM_MIN && input.charAt(x) <= CHAR_VAL_NUM_MAX) {
				temp += input.charAt(x);
			}
		}

		return temp;
	}
	
	public static Date getValidDateInput(String question){
		System.out.print(question);
		System.out.println("Ex. MM/DD/YYYY, 1/1/1111, 01/01/1111\n");

		input = in.nextLine();
		
		String regex = "(0?[1-9]|1[0-2])/(0?[1-9]|[12][0-9]|3[01])/([0-9]{4}$)"; 
		/*limits input  months 1 - 12, limits days 1-31, limits year to 4 digits*/

		while (!input.matches(regex)) {

			System.out.println("Please enter a date.\nEx. MM/DD/YYYY, 1/1/1111, 01/01/1111\n" + question);
			input = in.nextLine();
		}
		
		try {
			dateFormat = formatter.parse(input);
		} catch (ParseException e) { }
		
		return dateFormat;
	}
	

	public static boolean getConfirmation(String question) {
		question += " (yes or no)";
		System.out.println(question);
		boolean valid = false;
		
		do {
			String confirmation = in.nextLine();
			
			if (!confirmation.isEmpty()) {
				switch(confirmation.toLowerCase()) {
				case "yes" :
				case "y" :
					answer = true;
					valid = true;	
					break;
				case "no" :
				case "n" :
					valid = true;
					break;
				default :
					System.out.println("\nUnrecognizable input. " + question);
				}
			} else {
				System.out.println("\nNo valid input. " + question);
			}
		} while (!valid);
		
		return answer;
	}
	
	public static void pauseOn(String line, boolean clearScreen) {
		if (!line.isEmpty()) {
			System.out.println("\n" + line);
		}
		System.out.println("Enter to continue");
		in.nextLine();
		
		if (clearScreen) {
			clearScreen();			
		}
	}
	
	public static void clearScreen() {
		for (int i = 0; i < 30; i++) {
			System.out.println("");
		}
	}

	public static String getLastInput() {
		return input;
	}
	
	public static boolean getAnswer(){
		return answer;
	}

	public static String getNewDateString() {
		return newDateString;
	}
	
}
