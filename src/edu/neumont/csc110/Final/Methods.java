package edu.neumont.csc110.Final;

import java.util.Scanner;

public class Methods {

	private static Scanner in = new Scanner(System.in);
	private static String input;

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
	
	public static boolean yesOrNo(String question) {
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
	
}
