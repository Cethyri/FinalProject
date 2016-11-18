package edu.neumont.csc110.Final;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Day {
	
	public void DateAndTime(){
		Date currentTime = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("MM/dd/YYYY HH:mm");
		
		System.out.println(simple.format(currentTime));
		
	}
	
}
