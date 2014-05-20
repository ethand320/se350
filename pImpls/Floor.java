package pImpls;
import pExceptions.NegativeCapacityException;
import pExceptions.NegativeElevatorException;
import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pInterfaces.ElevatorInterface;
import pInterfaces.FloorInterface;

import java.util.ArrayList;


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
	 * @throws NegativeFloorException 
	 */
	public Floor(int inFloorId) throws NegativeFloorException
	{
		setFloorNumber(inFloorId);
		initializeFloorArrays();
	}
	
    /** 
     * Adds a person to the floor that called this method.
     * @param inPerson the person that is being added to the floor
     * @throws NullPassengerException if inPerson is null
     */
    @Override
	public void addPersonToFloor(Person inPerson) throws NullPassengerException
	{
    	if(inPerson == null)
    	{
    		throw new NullPassengerException("The passenger meant to be placed on this floor is null!");
    	}
		int destinationFloor = inPerson.getDestinationFloor();
		try
		{
			if(destinationFloor < this.getId() || destinationFloor == ElevatorControlModule.getInstance().getMaxFloors())
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
		catch (NegativeCapacityException | NegativeElevatorException | NegativeFloorException e)
		{
			e.printStackTrace();
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
			//NegativeFloorException isn't be declared here because it has already been caught in the constructor
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
	public int getId() 
	{
		return this.floorNumber + 1;
	}

   /**
    * removes the people that desire to get off at the floor.
    * @param elevatorToEnter places the people from the floor onto the specified elevator.
    * @param directionToGo the direction decides if a person will get off at the given floor or not.
    */
	@Override
	public void removeFromFloor(ElevatorInterface elevatorToEnter, Direction directionToGo) 
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
			for (Person person : peopleToRemove)
			{
				try
				{
					elevatorToEnter.addPassenger(person);
				}
				catch (NullPassengerException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
   /**
    * Sets the floor number to the given parameter
    * @param inNum the floor number that will be set as the floorNumber.
    * @throws NegativeFloorException 
    */
	private void setFloorNumber(int inNum) throws NegativeFloorException
	{
		if(inNum < 0 || inNum >= SimulationEnvironment.FLOOR_NUM)
		{
			throw new NegativeFloorException("Attempting to create a floor with an index that is outside the bounds of the simulation! (inNum: " + inNum + ")");
		}
		floorNumber = inNum;
	}

   /**
    * Creates the floor arrays of type <Person> for both elevator directions.
    */
	private void initializeFloorArrays()
	{
		goingUp = new ArrayList<Person>();
		goingDown = new ArrayList<Person>();
	}
}
