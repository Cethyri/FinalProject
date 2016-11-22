package edu.neumont.csc110.Final;



public class Driver {

	public static void main(String[] args) {
//		Day D = new Day();
//		
//		D.AddEvent();
		Event e = new Event();
		
		e.SetStartTime();
		e.SetEndTime();
		e.SetEventOccurence();
		e.SetPriorityLevel();
		e.SetDescription();
		e.DisplayTimes();
		e.DisplayEventType();
		e.DisplayEventPriority();
		e.SetDescription();
		
		Calendar C = new Calendar();
		
		C.interact();
	}

}
