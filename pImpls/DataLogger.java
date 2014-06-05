/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pImpls;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Constructor for Datalogger that handles the startTime and the masterTime stamps
 */
public class DataLogger
{
	private static long startTime;   // time object?
	private static long masterTime;   //needs to be a timestamp?    

	/**
	 * sets the Start time using the current time in Millis.
	 */
	public static void logSimulationStart()
	{
		if(startTime <= 0)
		{
			startTime = System.currentTimeMillis();
			System.out.println("The simulation will run for " + ( XmlParser.getDuration() / 1000 ) + " seconds.");
		}
	}

	/**
	 * logs the simulation sleep time to the console.
	 * @param timeToSleep the time the simulation will be running for prior to sleeping.
	 */
	public static void logIminentEnd(int timeToSleep)
	{
		System.out.println("The simulation will sleep for " + timeToSleep / 1000 + " seconds before shutting down completely.");
	}

	/**
	 * Logs the shutting down message to the console
	 */
	public static void logActualEnd()
	{
		System.out.println("The simulation is shutting down now");
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

		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SSS");
		Date date = new Date(masterTime);
		
		return sdf.format(date);
		//return timestamp to be used in all below methods
	}

	/**
	 * Keeps track of the person creation details.
	 * prints out the person's id, currentfloor, and destinationfloor'
	 * @param name takes in the id of a person that will be tracked.
	 */
	public static void logPersonCreation(int inPersonId, int inStartFloor, int inDestFloor)
	{
		DataAnalytics.personCreationTimeStamp(inPersonId, inStartFloor, inDestFloor);
		//    masterTime = current system time - start time
		System.out.println( printTimeStamp() + ":     Person " + inPersonId + " was created on floor " + inStartFloor + " "
				+ " heading to floor " + inDestFloor);

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
		System.out.println(printTimeStamp() + ":     Person "+ name.getID() + " presses " + dir + " on Floor " + floor );
	}

	// when an elevator is assigned an up/down request from a floor
	/**
	 * Logs the traffic of the elevator that requested this method.
	 * @param dir the direction the elevator is headed.
	 * @param floor the destination floor of the elevator.
	 * @param elev the elevator id that is associated to the currently being used elevator.
	 */
	public static void logElevatorDirectionRequest(int elevId, int floor, Direction dir, ArrayList<Integer> inRequestQueue)
	{
		//  masterTime = systemtime - start time
		
		System.out.println(printTimeStamp() + ":     Elevator " + elevId + " going to floor " + floor + " for " + dir + " request");
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
		System.out.println(printTimeStamp() + ":     Elevator " + elev.getElevatorId() + " Rider Request made for Floor " + floor.getId() );
	}

	// When an elevator moves from one floor to another,
	/**
	 * logs the traffic of the elevator when it is moving.
	 * @param elev the elevator that is currently being tracked
	 * @param to the destination floor the elevator is moving to.
	 * @param from the starting floor the elevator started at.
	 */
	public static void logElevatorMoving(int elev, int to, int from) 
	{
		System.out.println(printTimeStamp() + ":     Elevator " + elev + " is moving from " + from + " to floor " + to);
	}

	/**
	 * logs the traffic for when an elevator arrives at a requested floor.
	 * @param elev the id that is associated with the currenty being used elevator.
	 * @param floor the floor number the elevator has arrived at.
	 */
	public static void logElevatorArriveAtRequestedFloor(Elevator elev, int floor)
	{
		System.out.println(printTimeStamp() + ":     Elevator " + elev.getElevatorId() + " has arrived at floor " + floor);
	}

	/**
	 * logs the traffic of a specific elevator when doors open.
	 * @param elev the elevator id associated with the elevator that is currently being opened.
	 */
	public static void logElevatorOpenDoors(Elevator elev)
	{
		System.out.println(printTimeStamp() + ":     Elevator " + elev.getElevatorId() + " opening doors");
	}

	/**
	 * logs the traffic of a specific elevator when doors close.
	 * @param elev the elevator id associated with the elevator that is currently being closed.
	 */
	public static void logElevatorCloseDoors(Elevator elev)
	{
		System.out.println(printTimeStamp() + ":     Elevator " + elev.getElevatorId() + " closing doors");
	}

	//when a person enters an elevator
	// time person ID entered elevator elevID   [array of all riders]

	//When a person leaves an elevator
	// time person ID left elevator elevID   [array of all riders]

	//when person enters a floor
	//  logPersonAddToFloor(inperson, this, this.getWaitingPeople());


	/**
	 * Logs the traffic for when a person is removed from an elevator and placed onto the floor.
	 * @param p the id that is associated with each person.
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
	 * Logs the traffic of passengers that enter the elevator.
	 * @param inPersonId the id of the passenger that is unique to that passenger.
	 * @param inElevatorId the id of the elevator that is unique to that elevator.
	 * @param inCurrentFloor the floor number the passenger is getting on at .
	 */
	public static void logPersonAddToElevator(int inPersonId, int inElevatorId, int inCurrentFloor)
	{
		System.out.println(DataLogger.printTimeStamp() + ":    Person " + inPersonId + " has entered Elevator " + inElevatorId + " at floor " + inCurrentFloor);
		DataAnalytics.elevAddTimeStamp(inPersonId, inElevatorId);
	}

	/**
	 * Logs the traffic of passengers that leave the elevator.
	 * @param inPersonId the id of the passenger that is unique to that passenger.
	 * @param inElevatorId the id of the elevator that is unique to that elevator.
	 * @param inCurrentFloor the floor number the passenger is getting off at .
	 */
	public static void logPersonLeaveElevator(int inPersonId, int inElevatorId, int inCurrentFloor)
	{
		System.out.println(DataLogger.printTimeStamp() + ":     Person " + inPersonId + " is being removed from Elevator " + inElevatorId + " at floor " + inCurrentFloor);
		DataAnalytics.elevLeaveTimeStamp(inPersonId, inElevatorId);
	}

	/**
	 * Logs the rejected elevator requests to the console.
	 * @param inRequest The requested floor that is being rejected.
	 * @param inElevatorId the elevator id that is rejected the request.
	 */
	public static void logRejectedElevatorRequest(int inRequest, int inElevatorId)
	{
		System.out.println(DataLogger.printTimeStamp() + ":     Request for floor " + inRequest + " was rejected by the elevator: " + inElevatorId);
	}

	/**
	 * logs to the console when the elevator is full and rejects a request.
	 * @param inRequest The requested floor that is being rejected.
	 * @param inElevatorId the elevator id that is rejected the request.
	 */
	public static void logFullElevator(int inElevatorId, int inPassengerId)
	{
		System.out.println(DataLogger.printTimeStamp() + ":     Adding person " + inPassengerId + " to Elevator " + inElevatorId + " failed because the elevator is already full!");
	}

	/**
	 * logs the start of the specified elevator to the console.
	 * @param inElevatorId the id of the elevator that has started.
	 */
	public static void logElevatorStart(int inElevatorId)
	{
		System.out.println(DataLogger.printTimeStamp() + ":     Elevator " + inElevatorId + " has started");		
	}

	/**
	 * logs the stop of the specified elevator to the console.
	 * @param inElevatorId the id of the elevator that has stopped.
	 */
	public static void logElevatorStop(int inElevatorId)
	{
		System.out.println(DataLogger.printTimeStamp() + ":     Elevator " + inElevatorId + " has stopped");
	}

	/**
	 * logs the timing out of the specified elevator to the console.
	 * @param inElevatorId the id of the elevator that has timed out.
	 */
	public static void logElevatorTimeout(int inElevatorId)
	{
		System.out.println(DataLogger.printTimeStamp() + ":     Elevator " + inElevatorId + " has been idle for 10 seconds. Returning to floor 1");
	}
}
