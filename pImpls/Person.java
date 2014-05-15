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
        */
	public Person(int floorToStart, int floorToStop)
	{
		setDestinationFloor(floorToStop);
		setCurrentFloor(floorToStart);
	}
	
	/**
	 * retrieves the destination floor for the requested call.
	 * @return returns the destination floor of the requested caller.
	 */	
	public int getDestinationFloor()
	{
		return destinationFloor;
	}
	
	/**
	 * retrieves the current floor for the requested call.
	 * @return returns the current floor of the requested caller.  
	 */
	public int getCurrentFloor()
	{
		return currentFloor;
	}
	
       /**
     	* Handles the destination floor of the requesting elevator.
        * @param inDest The floor number that will be taken in as the floor's destination.
        */
	public void setDestinationFloor(int inDest)
	{
		destinationFloor = inDest;
	}
	
       /**
     	* Handles the current floor of the requesting elevator.
        * @param inDest The floor number that will be taken in as the current floor.
        */
	private void setCurrentFloor(int inCur)
	{
		currentFloor = inCur;
	}
}
