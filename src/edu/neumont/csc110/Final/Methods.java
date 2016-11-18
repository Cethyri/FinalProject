package edu.neumont.csc110.Final;

import java.util.Scanner;

public class Methods {

	private static Scanner in = new Scanner(System.in);
	private static String input;

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
