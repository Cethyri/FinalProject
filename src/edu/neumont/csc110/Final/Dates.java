package edu.neumont.csc110.Final;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Dates {

	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	private Scanner in = new Scanner(System.in);
	

	public void dateAndTime() {

		Date currentTime = new Date();
		System.out.println(formatter.format(currentTime));
	}


	public void addEvent() {
		
	}

	public void removeEvent() {
	}

	public void interactWithEvents() {

	}
	
	
}