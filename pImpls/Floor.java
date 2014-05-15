package pImpls;
import pInterfaces.ElevatorInterface;
import pInterfaces.FloorInterface;
import java.util.ArrayList;


/**
 * The floor class represents a building floor containing the floor number, summon elevator buttons, and the modification of people on the floor.  
 *  requests to visit certain floors. Each Elevator object runs in its own thread and will continue to run until manually shut down.
 */
public class Floor implements FloorInterface
{
	private ArrayList<Person> goingUp;
	private ArrayList<Person> goingDown;
	private int floorNumber;
	
    /** 
     * Constructor which allows for the initializations of the floor array as well as handling floor identification numbers.
     * @param inFloorId The identification number that will be assigned to each floor.
     */
	public Floor(int inFloorId)
	{
		setFloorNumber(inFloorId);
		initializeFloorArrays();
	}
	
        /** 
         * Adds a person to the floor that called this method.
         * @param inPerson the person that is being added to the floor
         */
        @Override
	public void addPersonToFloor(Person inPerson)
	{
		int destinationFloor = inPerson.getDestinationFloor();
		if(destinationFloor < this.floorNumber || destinationFloor == ElevatorControlModule.getInstance().getMaxFloors())
		{
			goingDown.add(inPerson);
			summonElevator(Direction.DOWN);
		}
		else if(inPerson.getDestinationFloor() > this.getId())
		{
			goingUp.add(inPerson);
			summonElevator(Direction.UP);
		}
	}
	
        /** 
         * Requests for an elevator to be summoned in the specified direction.
         * @param directionToGo the direction the elevator will go.
         */
        @Override
	public void summonElevator(Direction directionToGo) 
	{
		if(directionToGo != Direction.IDLE)
		{
			ElevatorControlModule.getInstance().elevatorCallReceiver(this.getId(), directionToGo);		
		}
	}

       /**
        * retrieves the floor identification number for the requested call.
        * @return returns this floor number when called.
        */ 
	@Override
	public int getId() 
	{
		return this.floorNumber;
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
			for (Person person : peopleToRemove )
			{
				elevatorToEnter.addPassenger(person);
			}
		}
	}
	
       /**
        * Sets the floor number to the given paramter
        * @param inNum the floor number that will be set as the floorNumber.
        */
	private void setFloorNumber(int inNum)
	{
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
