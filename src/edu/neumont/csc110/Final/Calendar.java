package edu.neumont.csc110.Final;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Calendar {

	private static final String LOAD = "load", SAVE = "save", NEW = "new", ADD = "add", DELETE = "delete",
			LIST = "list", QUIT = "quit", NAME = "(calendar name)", FILE_PATH = "saveFiles/";

	boolean quit;
	
	private ArrayList<Day> dates;

	public Calendar() {
		// something normal

	}

	public void interact() {
		quit = false;
		String input, action, name;
		String[] parts;

		do {
			printMenu();
			input = Methods.getValidInput(printMenu());
			input = input.toLowerCase();
			parts = input.split(" ");

			action = parts[0];
			
			if (parts.length > 1) {
				name = parts[1];
				doAction(action, name);
			} else {
				doAction(action);
			}
		} while (!quit);
	}

	private String printMenu() {
		String menu = "";
		menu += "What would you like to do?\n\n";
		menu += "Input - result\n";
		menu += "\t" + LOAD + " " + NAME + " - load a calendar\n";
		menu += "\t" + SAVE + " " + NAME + " - save your current calendar\n";
		menu += "\t" + NEW + " " + NAME + " - save your current calendar to a new file\n";
		menu += "\t" + ADD + " " + NAME + " - load a calendar and add it to yours\n";
		menu += "\t" + DELETE + " " + NAME + " - load a calendar and add it to yours\n";
		menu += "\t" + LIST + " - list available files\n";
		menu += "\t" + QUIT + " - exit the program\n";
		return menu;
	}

	private void doAction(String action, String name) {

		switch (action) {
		case LOAD:
			load(name);
			break;
		case SAVE:
			save(name);
			break;
		case NEW:
			createNew(name);
			break;
		case ADD:
			add(name);
			break;
		case DELETE:
			delete(name);
			break;
		default:
			Methods.pauseOn("Please enter an input provided.", true);
		}
	}

	private void doAction(String action) {
		switch (action) {
		case LIST:
			list();
			break;
		case QUIT:
			quit();
			break;
		default:
			Methods.pauseOn("Please enter an input provided.", true);
		}
	}

	private void load(String name) {
		try {
			// Open file to read from, named SavedObj.sav.
			FileInputStream saveFile = new FileInputStream(FILE_PATH + name + ".sav");

			// Create an ObjectInputStream to get objects from save file.
			ObjectInputStream save = new ObjectInputStream(saveFile);

			// Now we do the restore.
			// readObject() returns a generic Object, we cast those back
			// into their original class type.
			// For primitive types, use the corresponding reference class.
			dates = (ArrayList<Day>) save.readObject();

			// Close the file.
			save.close(); // This also closes saveFile.
		} catch (Exception exc) {
			// exc.printStackTrace(); // If there was an error, print the info.
			Methods.pauseOn("That file name doesn't exist", true);
		}
	}

	private void save(String name) {
		try { // Catch errors in I/O if necessary.
				// Open a file to write to, named SavedObj.sav.
			FileOutputStream saveFile = new FileOutputStream(FILE_PATH + name + ".sav");

			// Create an ObjectOutputStream to put objects into save file.
			ObjectOutputStream save = new ObjectOutputStream(saveFile);

			// Now we do the save.
			save.writeObject(dates);
			// Close the file.
			save.close(); // This also closes saveFile.
		} catch (Exception exc) {
			exc.printStackTrace(); // If there was an error, print the info.
		}

	}

	private void createNew(String name) {
		unimplemented();
	}

	private void add(String name) {
		unimplemented();
	}

	private void delete(String name) {
		File toDelete;
		try {
			toDelete = new File(FILE_PATH + name + ".sav");
			toDelete.delete();

		} catch (Exception e) {
			// File permission problems are caught here.
			System.err.println(e);
		}
		list();
	}

	private void list() {
		File f;
		File[] files;

		try {
			// create new file
			f = new File(FILE_PATH);

			// returns pathnames for files and directory
			files = f.listFiles();

			System.out.println("\nFiles:\n");
			// for each pathname in pathname array
			for (File file : files) {
				// prints file and directory paths
				System.out.println(file.getName());
			}
			System.out.println();
			Methods.pauseOn("", true);
		} catch (Exception e) {
			// if any error occurs
			e.printStackTrace();
		}
	}

	private void quit() {
		unimplemented();
	}
	
	private void unimplemented() {
		Methods.pauseOn("UNINPLEMENTED", true);
		
	}
}
