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
	private static final String FILE_PROB = "That file doesn't exist. Try checking the files with \"list\".";

	private boolean quit, saved;

	private String currentName;

	private ArrayList<Day> dates;

	public Calendar() {
		currentName = null;
		quit = false;
		saved = true;
		
	}

	public void interact() {
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
		menu += "What would you like to do?";
		menu += "\t\t";
		if (currentName != null) {
			menu += "Current calendar: " + currentName;
		} else {
			menu += " No calendar selected";
		}
		menu += "\n\n";
		menu += "\tInput - result\n\n";
		menu += "\t" + LOAD + " " + NAME + " - load a calendar\n";
		menu += "\t" + SAVE + " - save your current calendar\n";
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
		case SAVE:
			save(currentName);
			break;
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

		File f = new File(FILE_PATH + name + ".sav");

		if (f.exists()) {
			try {

				FileInputStream loadFile = new FileInputStream(f.getPath());

				ObjectInputStream load = new ObjectInputStream(loadFile);

				saveBefore("loading");

				dates = (ArrayList<Day>) load.readObject();
				currentName = name;
				
				Methods.pauseOn("Calendar " + name + " loaded", true);

				load.close();

			} catch (Exception exc) {
				error(exc);
			}
		} else {
			Methods.pauseOn(FILE_PROB, true);
		}
	}

	private void save(String name) {
		if (currentName != null) {

			File f = new File(FILE_PATH + name + ".sav");

			if (f.exists()) {
				try {

					FileOutputStream saveFile = new FileOutputStream(f.getPath());

					ObjectOutputStream save = new ObjectOutputStream(saveFile);

					save.writeObject(dates);
					currentName = name;
					
					Methods.pauseOn("Calendar " + name + " saved", true);

					save.close();

				} catch (Exception exc) {
					error(exc);
				}
			} else {
				Methods.pauseOn(FILE_PROB, false);
				if (Methods.getConfirmation("Would you like to create a new file?")) {
					createNew(name);
				}
			}
		} else {
			Methods.pauseOn("You haven't loaded or created a file yet", true);
		}

	}

	private void createNew(String name) {

		File f = new File(FILE_PATH + name + ".sav");

		if (f.exists()) {

			System.out.println("This file already exists.");
			if (Methods.getConfirmation("would you like to save over it?")) {
				save(name);
			}

		} else {
			try {
				
				FileOutputStream saveFile = new FileOutputStream(f.getPath());

				ObjectOutputStream save = new ObjectOutputStream(saveFile);
				
				save.writeObject(dates);
				currentName = name;

				Methods.pauseOn("New Calendar \"" + name + "\" created", false);
				
				save.close();

			} catch (Exception e) {
				error(e);
			}
		}
	}

	private void add(String name) {

		if (currentName != name) {
			
			File f = new File(FILE_PATH + name + ".sav");
			
			if (f.exists()) {
				try {
					
					FileInputStream saveFile = new FileInputStream(f.getPath());
					
					ObjectInputStream save = new ObjectInputStream(saveFile);
					
					ArrayList<Day> tDates = (ArrayList<Day>) save.readObject();
					
					save.close();
					
					if (tDates != null) {					
						for (Day d : tDates) {
//							for (Event e : d.getEvents()) {
//								addEvent(e);
//							}
						}
						Methods.pauseOn("Calendar " + name + " added to " + currentName + ".", true);
					} else {
						Methods.pauseOn("Calander " + name + " has no contents,", true);
					}
					
				} catch (Exception exc) {
					error(exc);
				}
			} else {
				Methods.pauseOn(FILE_PROB, true);
			}
		} else {
			Methods.pauseOn("You can't add a Calendar to itself.", true);
		}

	}

	private void delete(String name) {

		File toDelete = new File(FILE_PATH + name + ".sav");

		if (toDelete.exists()) {

			if (Methods.getConfirmation("Are you sure you want to delete " + name + "?")) {

				toDelete.delete();
				if (name.equalsIgnoreCase(currentName)) {
					currentName = null;
				}

				Methods.pauseOn("Calendar \"" + name + "\" deleted", false);
			}

		} else {
			Methods.pauseOn(FILE_PROB, false);
		}
		System.out.print("Remaining ");
		list();
	}

	private void list() {
		File f = new File(FILE_PATH);
		File[] files = f.listFiles();

		if (f.isDirectory()) {

			System.out.println("\nFiles:\n");

			for (File file : files) {

				if (file.getName().endsWith(".sav")) {

					System.out.println(file.getName().substring(0, file.getName().length() - ".sav".length()));
				}
			}
			Methods.pauseOn("\n", true);

		}
	}

	private void quit() {
		if (Methods.getConfirmation("Are you sure you would like to quit?")) {
			saveBefore("quiting");
			quit = true;
		}
		Methods.pauseOn("", true);
	}

	private void saveBefore(String action) {
		if (!saved) {

			System.out.print("You haven't saved");
			if (currentName != null) {
				System.out.println(" " + currentName + " yet?");
			} else {
				System.out.println(".");
			}
			if (Methods.getConfirmation("Would you like to save before " + action + "?")) {
				save(currentName);
			}
		}
	}

	private void addEvent(Event e) {
		unimplemented();

	}

	private void unimplemented() {
		Methods.pauseOn("UNINPLEMENTED", true);
	}

	private void error(Exception exc) {
		exc.printStackTrace();
		Methods.pauseOn("An error occured", true);
	}
}
