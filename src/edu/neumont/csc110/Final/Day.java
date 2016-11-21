package edu.neumont.csc110.Final;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Day {


	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	Scanner in = new Scanner(System.in);

	public void DateAndTime() {

		Date currentTime = new Date();
		System.out.println(formatter.format(currentTime));
	}


	public void AddEvent() {
		
	}

	public void RemoveEvent() {

	}

	public void interactWithEvents() {

	}
}
