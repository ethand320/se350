package pImpls;

/**
 * The Person class represents the starting and ending floor a person will have when interacting with the elevator.
 */
public class Person 
{
	private int destinationFloor;
	private int currentFloor;
	
	/**
     * Constructor which allows for properties to be applied to a person in the building.
     * @param floorToStop The stopping floor number that the person will stop at.
     * @param floorToStart The starting floor number that the person will stop at.
     * @throws NegativeCapacityException if any of the values passed into it are negative
     */
	public Person(int floorToStart, int floorToStop)
	{
		setDestinationFloor(floorToStop);
		setCurrentFloor(floorToStart);
	}
	
	public int getDestinationFloor()
	{
		return destinationFloor;
	}
	
	public int getCurrentFloor()
	{
		return currentFloor;
	}
	/**
     * Handles the destination floor of elevator is is called upon.
     * @param inDest The floor number that will be taken in as the floor destination
     */
	public void setDestinationFloor(int inDest)
	{
		destinationFloor = inDest;
	}
	
	/**
     * Allows for the setting of the current floor.
     * @param inCur the current floor that will be set.
     */
	private void setCurrentFloor(int inCur)
	{
		currentFloor = inCur;
	}
}
