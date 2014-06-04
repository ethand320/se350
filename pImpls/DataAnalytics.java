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
    
     private static ArrayList<PersonDataEntry> dataEntries = new ArrayList<PersonDataEntry>();


    	/**
    	 * The person's ID number
    	 */
    	public int personID;
    	
    	/**
    	 * The person's start floor. Used for analytics related to inter-floor travel and total wait time
    	 */
    	public int personStartFloor;
    	
    	/**
    	  	
    	/**
    	 * The system time at which this object leaves the elevator and arrives at their destination floor, measured in milliseconds
    	 */
    	public long leaveElevTimeStamp;
    	
    	
    /* The person's destination floor. Used for analytics related to inter-floor travel and total wait time
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
    	
    	
        
        
        public static void logPersonAddToElevator(int inPersonId, int inElevatorId, int inCurrentFloor)
	{
		System.out.println(DataLogger.printTimeStamp() + ":    Person " + inPersonId + " has entered Elevator " + inElevatorId + " at floor " + inCurrentFloor);
		dataEntries.get(inPersonId).enterElevTimeStamp = System.currentTimeMillis();
	}
	
	public static void logPersonLeaveElevator(int inPersonId, int inElevatorId, int inCurrentFloor)
	{
		System.out.println(DataLogger.printTimeStamp() + ":     Person " + inPersonId + " is being removed from Elevator " + inElevatorId + " at floor " + inCurrentFloor);
		dataEntries.get(inPersonId).leaveElevTimeStamp = System.currentTimeMillis();

	}
        
    }