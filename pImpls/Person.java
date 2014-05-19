package pImpls;

import pExceptions.NegativeFloorException;

/**
 * The Person class represents the starting and ending floor a person will have when interacting with the elevator.
 */
public class Person 
{
	/**
	 * The floor number that this Person will request once it enters an ElevatorInterface object. NOTE: this is an internal representation, 
	 * so this value is ZERO-BASED
	 */
	private int destinationFloor;
	
	/**
	 * The index to the floor that this Person will start on. This value is used to compute which direction that the Person needs to travel.
	 * It will NOT change once this Person enters an elevator. NOTE: this is an internal representation, so this value is ZERO-BASED
	 */
	private int currentFloor;
	
   /**
	* Constructor which allows for properties to be applied to a person in the building.
 	* @param floorToStop The stopping floor number that the person will stop at.
 	* @param floorToStart The starting floor number that the person will stop at.
 	* @throws NegativeFloorException if either floorToStart or floorToStop are outside the bounds of the simulation
    */
	public Person(int floorToStart, int floorToStop) throws NegativeFloorException
	{
		setDestinationFloor(floorToStop);
		setCurrentFloor(floorToStart);
	}
	
	/**
	 * Accessor for the floor that this Person is going to travel to.
	 * @return returns the ONE-BASED index of the floor that this Person is going to travel to.
	 */	
	public int getDestinationFloor()
	{
		return destinationFloor + 1;
	}
	
	/**
	 * Accessor for the current floor that this Person is located on. This value will not change when this Person is moving inside an elevator.
	 * @return returns the ONE-BASED index of the current floor that this Person is located on. 
	 */
	public int getCurrentFloor()
	{
		return currentFloor + 1;
	}
	
   /**
 	* Handles the destination floor of the requesting elevator.
    * @param inDest The floor number that the Person will want to go to. This value will be added to the elevator's request queue upon entering
    * an elevator. NOTE: this value is an external representation, so it is ONE-BASED
    * @throws NegativeFloorException if inDest is less than 1 or greater than the number of floors in the simulation
    */
	public void setDestinationFloor(int inDest) throws NegativeFloorException
	{
		if(inDest < 1 || inDest > SimulationEnvironment.FLOOR_NUM)
		{
			throw new NegativeFloorException("This passenger's destination floor does not exist in the simulation! (inDest: " + inDest + ")");
		}
		destinationFloor = inDest - 1;
	}
	
	/**
	 * @param inCur The floor number that the Person will be starting on. Used to compute the direction in which this Person needs to travel.
	 * NOTE: this value is an external representation, so it is ONE-BASED
	 * @throws NegativeFloorException if inCur is less than 1 or greater than the number of floors in the simulation
	 */
	private void setCurrentFloor(int inCur) throws NegativeFloorException
	{
		if(inCur < 1 || inCur > SimulationEnvironment.FLOOR_NUM)
		{
			throw new NegativeFloorException("This passenger's current floor does not exist in the simulation! (inCur: " + inCur + ")");
		}
		currentFloor = inCur - 1;
	}
}
