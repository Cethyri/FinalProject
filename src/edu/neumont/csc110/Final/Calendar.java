package edu.neumont.csc110.Final;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Calendar {

	private ArrayList<Day> dates;

	public Calendar() {
		
	}

	private void addEvent(Event e) {
		unimplemented();

	}

	private void unimplemented() {
		Methods.pauseOn("UNINPLEMENTED", true);
	}

	public void Combine(Calendar C) {
		for (Day d : C.getDates()) {
			for (Event e : d.getEvents()) {
				addEvent(e);
			}
		}
	}

	private ArrayList<Day> getDates() {
		// TODO Auto-generated method stub
		return dates;
	}
}
