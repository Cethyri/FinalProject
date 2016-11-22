package edu.neumont.csc110.Final;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Methods {

	private static Scanner in = new Scanner(System.in);
	private static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	
	private static String input;
	private static String regex;
	private static Date dateFormat;

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
	
	public static Date getValidDateInput(String question){
		System.out.print(question);

		question= in.nextLine();
		
		regex = "(0?[1-9]|1[0-2])/(0?[1-9]|[12][0-9]|3[01])/([0-9]{4}$)"; 


		while (!question.matches(regex)) {

			System.out.println("Please enter a date. \nEx. MM/DD/YYYY; 1/1/1111; 01/01/1111");
			question = in.nextLine();
		}
		
		try {
			dateFormat = formatter.parse(question);
			String newDateString = formatter.format(dateFormat);
			System.out.println(newDateString);
		} catch (ParseException e) { }
		
		return dateFormat;
	}
	
	public static boolean getConfirmation(String question) {
		question += " (yes or no)";
		System.out.println(question);
		boolean valid = false, answer = false;
		
		do {
			input = in.nextLine();
			
			if (!input.isEmpty()) {
				switch(input.toLowerCase()) {
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
	
	private static void clearScreen() {
		for (int i = 0; i < 30; i++) {
			System.out.println("");
		}
	}

	public static String getLastInput() {
		return input;
	}

	
}
