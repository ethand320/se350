package pImpls;
import java.util.ArrayList;

import pExceptions.NegativeCapacityException;
import pExceptions.NegativeElevatorException;
import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pInterfaces.ElevatorInterface;
import pInterfaces.FloorInterface;


/**
 * The floor class represents a building floor containing the floor number, summon elevator buttons, and the modification of people on the floor.  
 *  requests to visit certain floors. Each Elevator object runs in its own thread and will continue to run until manually shut down.
 */
public class Floor implements FloorInterface
{
	/**
	 * Collection of Person objects whose destination floors are higher than this object's floorNumber value, using zero-based indexing
	 */
	private ArrayList<Person> goingUp;
	
	/**
	 * Collection of Person objects whose destination floors are lower than this object's floorNumber value, using zero-based indexing
	 */
	private ArrayList<Person> goingDown;
	
	/**
	 * Integer value representing this floor's position within the simulation environment using zero-based indexing
	 */
	private int floorNumber;
	
	/** 
	 * Constructor which allows for the initializations of the floor array as well as handling floor identification numbers.
	 * @param inFloorId The identification number that will be assigned to each floor.
	 * @throws NegativeFloorException if inFloorId is outside the bounds of the simulation (less than 1 or greater than the number of floors in the simulation)
	 */
	public Floor(int inFloorId) throws NegativeFloorException
	{
		setFloorNumber(inFloorId);
		initializeFloorArrays();
	}
	
    /**
     * Places a Person object in this floor. Once inside, they summon an elevator by calling summonElevator
     * @param inPerson the Person object being added to the floor. Must not be null.
     * @throws NullPassengerException if inPerson is null
     */
    @Override
	public synchronized void addPersonToFloor(Person inPerson) throws NullPassengerException
	{
    	if(inPerson == null)
    	{
    		throw new NullPassengerException("The passenger meant to be placed on this floor is null!");
    	}
		int destinationFloor = inPerson.getDestinationFloor();
		if(destinationFloor < this.getId() || destinationFloor == XmlParser.getTotalFloorNumber())
		{
			goingDown.add(inPerson);
			summonElevator(Direction.DOWN);
		}
		else if(destinationFloor > this.getId())
		{
			goingUp.add(inPerson);
			summonElevator(Direction.UP);
		}
	}
	
	/** 
	 * Requests for an elevator to be summoned in the specified direction.
	 * @param directionToGo the direction the elevator will go. Must not be IDLE
	 */
    @Override
	public void summonElevator(Direction directionToGo)
	{
		if(directionToGo != Direction.IDLE)
		{
			try
			{
				//elevatorCallReceiver's floor index is zero-based, so instead of using this.getId(), we want to pass the
				//zero-based floorNumber index instead
				ElevatorControlModule.getInstance().elevatorCallReceiver(this.floorNumber, directionToGo);
			}
			catch (NegativeCapacityException | NegativeElevatorException | NegativeFloorException e)
			{
				e.printStackTrace();
			}		
		}
	}

   /**
    * Accessor for the Floor's ID number
    * @return returns the position of this floor instance in relation to the other floors in the simulation.
    * NOTE: This is an external representation, which means that the value is represented with ONE-BASED indexing
    */ 
	@Override
	public synchronized int getId() 
	{
		return this.floorNumber + 1;
	}

	/**
	 * Accessor for all of the Person objects who are waiting for elevators on this floor, regardless of direction
	 * @return The collection of Person objects waiting for elevators on this floor.
	 */
	@Override
	public synchronized ArrayList<Person> getWaitingPeople()
	{
		ArrayList<Person> listToReturn = this.goingUp;
		listToReturn.addAll(this.goingDown);
		return listToReturn;
	}
	
   /**
    * removes the people that desire to get off at the floor.
    * @param elevatorToEnter places the people from the floor onto the specified elevator.
    * @param directionToGo the direction decides if a person will get off at the given floor or not.
    */
	@Override
	public synchronized void removeFromFloor(ElevatorInterface elevatorToEnter, Direction directionToGo) 
	{
		if(directionToGo != Direction.IDLE)
		{
			ArrayList<Person> peopleToRemove;
			if(directionToGo == Direction.UP)
			{
				peopleToRemove = this.goingUp;
			}
			else
			{
				peopleToRemove = this.goingDown;
			}
			int i;
			boolean bRemovalSuccessful = true;
			for(i = 0; i < peopleToRemove.size() && bRemovalSuccessful; ++i)
			{
				Person curPerson = peopleToRemove.get(i);
				try
				{
					bRemovalSuccessful = elevatorToEnter.addPassenger(curPerson);
				}
				catch (NullPassengerException | NegativeFloorException e)
				{
					e.printStackTrace();
				}
			}
			int removalIndex;
			if(!bRemovalSuccessful)
			{
				removalIndex = i;
			}
			else
			{
				removalIndex = peopleToRemove.size();
			}
			for(int j = 0; j < removalIndex; ++j)
			{
				try
				{
					peopleToRemove.remove(0);

				}
				catch(IndexOutOfBoundsException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
   /**
    * Sets the floor number to the given parameter
    * @param inNum the floor number that will be set as the floorNumber.
	* @throws NegativeFloorException if inFloorId is outside the bounds of the simulation (less than 1 or greater than the number of floors in the simulation)
    */
	private synchronized void setFloorNumber(int inNum) throws NegativeFloorException
	{
		if(inNum < 0 || inNum >= XmlParser.getTotalFloorNumber())
		{
			throw new NegativeFloorException("Attempting to create a floor with an index that is outside the bounds of the simulation! (inNum: " + inNum + ")");
		}
		floorNumber = inNum;
	}

   /**
    * Creates the floor arrays of type <Person> for both elevator directions.
    */
	private synchronized void initializeFloorArrays()
	{
		goingUp = new ArrayList<Person>();
		goingDown = new ArrayList<Person>();
	}
}
