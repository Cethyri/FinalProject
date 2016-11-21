package edu.neumont.csc110.Final;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Methods {

	private static Scanner in = new Scanner(System.in);
	private static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	
	private static String input;
	private static Date dateFormat;

	public Methods() {

	}

	public static String getValidInput(String question) {
		System.out.print(question);
		boolean valid = false;

		do {
			input = in.nextLine();

			if (!input.isEmpty()) {
				valid = true;
			} else {
				System.out.println("No valid input. " + question);
			}
		} while (!valid);

		return input;
	}
	
	public static void getValidDateInput(String line){
		System.out.println("Please enter a date. Ex. MM/DD/YYYY");

		line = in.nextLine();
		
		String regex = "(0?[1-9]|1[0-2])/(0?[1-9]|[12][0-9]|3[01])/([0-9]{4}$)"; 


		while (!line.matches(regex)) {

			System.out.println("Please enter a date. \nEx. MM/DD/YYYY; 1/1/1111; 01/01/1111");
			line = in.nextLine();
		}
		
		try {
			dateFormat = formatter.parse(line);
			String newDateString = formatter.format(dateFormat);
			System.out.println(newDateString);
		} catch (ParseException e) { }
		
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
	
}
