/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pImpls;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import pInterfaces.ElevatorInterface;
import java.util.Date;
import java.util.ArrayList;


/**
 * Constructor for Datalogger that handles the startTime and the masterTime stamps
 */
public class DataLogger
{
    
    
    
    
    
    private static long startTime;   // time object?
    private static long masterTime;   //needs to be a timestamp?
    private static ArrayList<PersonDataEntry> dataEntries = new ArrayList<PersonDataEntry>();
    
    
    //needs to be called at beginning of simulation.
    /**
     * sets the Start time using the current time in Millis.
     */
    public static void setStartTime()
    {
        startTime = System.currentTimeMillis();
        
       // Calendar cal = Calendar.getInstance();
       // SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
       // System.out.println("Master time is set to " + sdf.format(cal.getTime()) );
    }
    
    /**
     * Creates the simple Date Format time stamp and returns it for each call.
     * @return returns the timestamp to be used with all methods. 
     */
    public static String printTimeStamp()
    {
       
        //masterTime = current system time - start time
        
        masterTime = System.currentTimeMillis() - startTime;
        //  System.out.println("differnce of times in millis is " + masterTime);
        
        SimpleDateFormat sdf = new SimpleDateFormat("00:mm:ss");
        Date date = new Date(masterTime);
        

        return sdf.format(date);
        //return timestamp to be used in all below methods
        
    }
    
    /**
     * Keeps track of the person creation details.
     * prints out the person's id, currentfloor, and destinationfloor'
     * @param name takes in the id of a person that will be tracked.
     */
    public static void logPersonCreation(Person name)
    {
    	PersonDataEntry newEntry = new PersonDataEntry();
    	newEntry.personID = name.getID();
    	newEntry.creationTimeStamp = System.currentTimeMillis();
    	dataEntries.add(newEntry);        
         //    masterTime = current system time - start time
        System.out.println( masterTime + ":     Person " + name.getID() + "was created on floor " + name.getCurrentFloor() + " "
                + " heading to floor " + name.getDestinationFloor());
  
        // some logic to keep track of person that was created here
    }
    
    
    //when a perosn presses up/down on a floor  (right after they are created)
    /**
     * keeps track of the floor requests.
     * @param name the id that is associated with the person using the callbox.
     * @param dir the direction of travel that is being tracked.
     * @param floor the floor request that is being tracked.
     */
    public static void logFloorRequest(Person name, Direction dir, Floor floor)
    {
        
        System.out.println("Person "+ name.getID() + "presses " + dir + " on Floor " + floor );
        
        
    }
    
    // when an elevator is assigned an up/down request from a floor
    /**
     * Logs the traffic of the elevator that requested this method.
     * @param dir the direction the elevator is headed.
     * @param floor the destination floor of the elevator.
     * @param elev the elevator id that is associated to the currently being used elevator.
     */
    public static void logElevatorDirectionRequest(ElevatorInterface elev, int floor, Direction dir)
    {
        
      //  masterTime = systemtime - start time
        
        System.out.println(printTimeStamp() + ":     Elevator " + elev.getElevatorId() + " going to floor" + floor + " for " + dir + " request");
        // also need to print out how many floor requests and how many rider requests??
     
    }
    
    //when a person inside an elevator presses a floor number button
    /**
     * Logs the traffic of the requests that are created from inside the elevator.
     * @param elev the elevator id that is currently being tracked.
     * @param floor the floor requested by the elevator passenger.
     */
    public static void logPersonElevatorFloorRequest(Elevator elev, Floor floor)
    {
        System.out.println("Elevator " + elev.getElevatorId() + " Rider Request made for Floor " + floor.getId() );

    }
    
    // When an elevator moves from one floor to another,
    /**
     * logs the traffic of the elevator when it is moving.
     * @param elev the elevator that is currently being tracked
     * @param to the destination floor the elevator is moving to.
     * @param from the starting floor the elevator started at.
     */
    public static void logElevatorMoving(Elevator elev, Floor to, Floor from) 
    {
        
        System.out.println("Elevator " + elev.getElevatorId() + "Moving from " + from + "to floor " + to);
        
        
        
    }
    
    //when elevator arrives at a floor with an UP/DOWN request
    /**
     * logs the traffic for when an elevator arrives at a requested floor.
     * @param elev the id that is associated with the currenty being used elevator.
     * @param floor the floor number the elevator has arrived at.
     */
    public static void logElevatorArriveAtRequestedFloor(Elevator elev, int floor)
    {
        
        System.out.println(printTimeStamp() + ":     Elevator " + elev.getElevatorId() + " has arrived at floor " + floor);
        
    }
    
    
    //when elevator doors open
    /**
     * logs the traffic of a specific elevator when doors open.
     * @param elev the elevator id associated with the elevator that is currently being opened.
     */
    public static void logElevatorOpenDoors(Elevator elev)
    {
        
        System.out.println(printTimeStamp() + ":     Elevator " + elev.getElevatorId() + "opening doors");
                
    }
    
    
    // when elevator doors close
    /**
     * logs the traffic of a specific elevator when doors close.
     * @param elev the elevator id associated with the elevator that is currently being closed.
     */
    public static void logElevatorCloseDoors(Elevator elev)
    {
        
        System.out.println(printTimeStamp() + ":     Elevator " + elev.getElevatorId() + "closing doors");
                
    }
     
     //when a person enters an elevator
     // time person ID entered elevator elevID   [array of all riders]
     
     //When a person leaves an elevator
     // time person ID left elevator elevID   [array of all riders]
     
     //when person enters a floor
   //  logPersonAddToFloor(inperson, this, this.getWaitingPeople());
     
     /**
      * Logs the traffic for when a person is removed from an elevator and placed onto the floor.
      * @param p the id that is associated with each person
      * @param floor the floor number that some passengers will get off at.
      * @param peopleList array list of people being added to the floor.
      */
     public static void logPersonAddToFloor(Person p, Floor floor, ArrayList<Person> peopleList)
     {
         System.out.println(printTimeStamp() + ":     Person " + p.getID() + " added to floor " + floor.getId() + displayArrayList(peopleList) );
     }
     //time person persID has entered floor floorNum
     
     //when person leaves a floor
     // itme person pers]ID has left floor floorNum
     
     
     /**
      * displays the current list of people
      * @param list the list of people that will be displayed
      */
     public static String displayArrayList(ArrayList<Person> list)
     {
         String myString = "[ ";
         for (Person person: list )
                 
         {
             myString += (Integer.toString(person.getID()) + " :");
         }
         myString += "]";
         
         return myString;
     }

	/**
	 * Logs the traffic of passengers that enter the elevator
	 * @param inPersonId the id of the passenger that is unique to that passenger
	 * @param inElevatorId the id of the elevator that is unique to that elevator
	 * @param inCurrentFloor the floor number the passenger is getting on at 
	 */
    	public static void logPersonAddToElevator(int inPersonId, int inElevatorId, int inCurrentFloor)
	{
		System.out.println(DataLogger.printTimeStamp() + ":    Person " + inPersonId + " has entered Elevator " + inElevatorId + " at floor " + inCurrentFloor);
		dataEntries.get(inPersonId).enterElevTimeStamp = System.currentTimeMillis();
	}

	/**
	 * Logs the traffic of passengers that leave the elevator
	 * @param inPersonId the id of the passenger that is unique to that passenger
	 * @param inElevatorId the id of the elevator that is unique to that elevator
	 * @param inCurrentFloor the floor number the passenger is getting off at 
	 */
	public static void logPersonLeaveElevator(int inPersonId, int inElevatorId, int inCurrentFloor)
	{
		System.out.println(DataLogger.printTimeStamp() + ":     Person " + inPersonId + " is being removed from Elevator " + inElevatorId + " at floor " + inCurrentFloor);
		dataEntries.get(inPersonId).leaveElevTimeStamp = System.currentTimeMillis();
	
	}

}
