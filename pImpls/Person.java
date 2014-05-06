package pImpls;

/**
 * The Elevator class represents a moveable container that navigates through various floors within the Building singleton. Elevators can hold a certain number of people and interpret
 * requests to visit certain floors. Each Elevator object runs in its own thread and will continue to run until manually shut down.
 */
public class Person 
{
	private int destinationFloor;
	private int currentFloor;
	
	    /**
     * Constructor which allows for properties such as the speed, capacity, and floor range of the elevator to be customized.
     * @param paramCapacity The number of people this elevator can hold at a given time. When the elevator hits capacity, no more people may board the elevator.
     * @param numFloors The number of floors that this elevator services. This value cannot be larger than the number of floors in the building that owns it, but it may be smaller
     * @param idNumber The unique identifier number. This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
     * @throws NegativeCapacityException if any of the values passed into it are negative
     */
	public Person(int floorToStart, int floorToStop)
	{
		setDestinationFloor(floorToStop);
		setCurrentFloor(floorToStart);
	}
	
	    /**
     * Handles the movement of the elevator. 
     * Will continue to run until <code>shutDownElevator()</code> is called.
     */
	private void setDestinationFloor(int inDest)
	{
		destinationFloor = inDest;
	}
	
	    /**
     * Handles the movement of the elevator. 
     * Will continue to run until <code>shutDownElevator()</code> is called.
     */
	private void setCurrentFloor(int inCur)
	{
		currentFloor = inCur;
	}
}
