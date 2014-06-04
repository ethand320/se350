/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pImpls;

import java.util.ArrayList;

/**
 *
 * @author User
 */



public class DataAnalytics 
{
	private static class PersonDataEntry
	{
		/**
		 * The person's ID number
		 */
		public int personID;

		/**
		 * The person's start floor. Used for analytics related to inter-floor travel and total wait time
		 */
		public int personStartFloor;

		/**
		 * The person's destination floor. Used for analytics related to inter-floor travel and total wait time
		 */
		public int personEndFloor;

		/**
		 * The system time at which this object was created, measured in milliseconds
		 */
		public long creationTimeStamp;

		/**
		 * The system time at which this object enters an elevator, measured in milliseconds
		 */
		public long enterElevTimeStamp;

		/**
		 * The system time at which this object leaves the elevator and arrives at their destination floor, measured in milliseconds
		 */
		public long leaveElevTimeStamp;

		public PersonDataEntry()
		{

		}
	}

	private static ArrayList<PersonDataEntry> dataEntries = new ArrayList<PersonDataEntry>();

	public static void personCreationTimeStamp(int inPersonId, int inStartFloor, int inDestFloor)
	{
		PersonDataEntry newEntry = new PersonDataEntry();
    	newEntry.personID = inPersonId;
    	newEntry.personStartFloor = inStartFloor;
    	newEntry.personEndFloor = inDestFloor;
    	newEntry.creationTimeStamp = System.currentTimeMillis();
    	dataEntries.add(newEntry);
	}
	
	public static void elevAddTimeStamp(int inPersonId, int inElevatorId)
	{
		dataEntries.get(inPersonId).enterElevTimeStamp = System.currentTimeMillis();
	}

	public static void elevLeaveTimeStamp(int inPersonId, int inElevatorId)
	{
		dataEntries.get(inPersonId).leaveElevTimeStamp = System.currentTimeMillis();
	}

}