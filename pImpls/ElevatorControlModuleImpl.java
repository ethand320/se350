package pImpls;

import pExceptions.NegativeCapacityException;
import pExceptions.NegativeElevatorException;
import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pFactories.ElevatorFactory;
import pFactories.FloorFactory;
import pInterfaces.ControlModuleInterface;
import pInterfaces.ElevatorInterface;
import pInterfaces.FloorInterface;


/**
 * An implementation of the ControlModule interface. Implements a specific elevator selection algorithm. 
 */
public class ElevatorControlModuleImpl implements ControlModuleInterface
{
	/**
	 * The collection of ElevatorInterface objects that this Control Module Implementation is responsible for
	 */
	private ElevatorInterface[] elevators; 

	/**
	 * The collection of FloorInterface objects that this Control Module Implementation is responsible for
	 */
	private FloorInterface[] floors;     

	// New ECM constructor to take xml data for floors/elevators
	public ElevatorControlModuleImpl()
	{
		int elevatorNum =  XmlParser.getTotalElevatorNumber();
		int floorNum = XmlParser.getTotalFloorNumber();

		System.out.println("Number of elevators from file : " + elevatorNum);
		System.out.println("Numberof floor numbers from file : "+ floorNum);
		try
		{
			createElevators(elevatorNum, floorNum);
			createFloors(floorNum);
		}
		catch (NegativeCapacityException | NegativeElevatorException
				| NegativeFloorException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This function is called whenever a Person object summons an elevator from a given floor. This method will compute the best elevator to
	 * send to that floor and add that floor to its request queue.
	 * @param floorNumber the ZERO-BASED floor number where the request is originating from.
	 * @param directionRequest the the direction that will lead to the requested floor.
	 * @throws NegativeFloorException if the floorNumber is outside the bounds of the floor collection. NOTE: this method uses ZERO-BASED indexing as it is an internal method
	 */
	@Override
	public void elevatorCallReceiver(int floorNumber, Direction directionRequest) throws NegativeFloorException
	{
		if(floorNumber < 0 || floorNumber >= floors.length)
		{
			throw new NegativeFloorException("The floor object that called this method has an invalid ID number! (floorNumber: " + floorNumber + ")");
		}

		//Ethan psudo code implementation here.  

		/*  This is for when a person presses up/down on a floor, what elevator gets the request put in it's queue, algo is from the notes Project submission 1 pdf

        if there is an elevator on the floor
            if elevator is idle OR going in desired direction
                then add the floor to that elevator;s queue

        have to loop through each elevator in elevators[]


        if there is an elevator on the floor
            if elevator is idle OR going in desired direction
                then add the floor to that elevator;s queue  and be DONE
            else 

            is there an elevator already moving?
        yes: is it also going in desired direction
              yes:  add the floor to that elevator's request queue

        no:
            are there any idle elevators?
            yes:
                pick an idle elevator and add the request to the queue
            no:
                add to unique pending request list  ( a catch all queue I guess?)

		 */
		int externalFloorNum = floorNumber + 1;
		int elevatorNum = elevators.length;
		boolean handledRequest = false;

		//  if there is an elevator on the floor
		//     if elevator is idle OR going in desired direction
		//         then add the floor to that elevator;s queue  and be DONE
		//for (ElevatorInterface curElev: elevators)
		for(int i = 0; i < elevatorNum && !handledRequest; ++i)
		{
			ElevatorInterface curElev = this.elevators[i];
			Direction curDirection = curElev.getDirection();
			int curFloor = curElev.getCurrentFloor();

			//is there an elevator on this floor already?
			if (curFloor == externalFloorNum)
			{
				if (curDirection == directionRequest || curDirection == Direction.IDLE)
				{    
					//open the doors of that elevator so people can get in. no need to actually add a request since it's already there
					curElev.openDoors();
					curElev.closeDoors();
					handledRequest = true;
				}   
			}
			// is there an elevator already moving?
			// yes: is it also going in desired direction or isn't moving at all?
			//      yes:  add the floor to that elevator's request queue
			else if (curElev.isRunning() && (curDirection == directionRequest ||  curDirection == Direction.IDLE) )
			{
				curElev.addFloorToQueue(externalFloorNum);
				handledRequest = true; 
			}
		}

		//If we got this far and request still hasn't been handled...
		// then it needs to be sent again  need this implementation done eventually     
	}          

	/**
	 * Returns the elevator at the index specified.
	 * NOTE: this function uses ONE-BASED indexing, which means that 0 is not a valid value
	 * @return The ElevatorInterface object located at the specified ONE-BASED index
	 */
	@Override
	public ElevatorInterface getElevator(int index)
	{
		return elevators[index-1];
	}

	/**
	 * Master shutdown command that stops further elevator commands.
	 */
	public void shutDown()
	{
		for(ElevatorInterface elevator : elevators)
		{
			elevator.shutDown();
		}
	}

	/**
	 * elevatorDoorsOpened takes in two parameters of the floor and elevator and removes initiates removeFromFloor
	 * @see removeFroomFloor removes passengers from the floor that will be placed into the elevator.
	 * @param elevator the elevator the will receive the requests.
	 * @param floorNumber the floor number the elevator is receiving the request at. Because this method is called by an Elevator object, it uses ZERO-BASED indexing
	 * @throws NegativeFloorException if floorNumber is less than 0 or greater than or equal to the number of total floors in the simulation (remember: this is ZERO-BASED indexing)
	 */
	@Override
	public void elevatorDoorsOpened(ElevatorInterface elevator, int floorNumber) throws NegativeFloorException
	{
		if(floorNumber < 0 || floorNumber >= this.floors.length)
		{
			throw new NegativeFloorException();
		}
		this.floors[floorNumber].removeFromFloor(elevator, elevator.getDirection());
	}

	/**
	 * addPersonToFloor moves them from the specified floor to the indicated elevator.
	 * @param inPerson calls the method to add a person to the elevator.
	 * @param floorNum the floor the person is located in. NOTE: this method uses ONE-BASED indexing, which means that 0 does not correspond
	 * to the metaphorical first floor
	 * @throws NullPassengerException if inPerson is null
	 * @throws NegativeFloorException if floorNum is less than or equal to 0
	 */
	@Override
	public void addPersonToFloor(Person inPerson, int floorNum) throws NullPassengerException, NegativeFloorException
	{
		if(floorNum <= 0 || floorNum > this.floors.length)
		{
			throw new NegativeFloorException("Attempting to add a person to an invalid floor. Remember that this method uses ONE-BASED indexing instead of ZERO-BASED indexing (Floor number: " + (floorNum + 1) + ").");
		}
               // DataLogger.logFloorRequest(inPerson, Direction dir, Floor floor);
		this.floors[floorNum-1].addPersonToFloor(inPerson);
	}

	/**
	 * Creates the collection of elevators that this ElevatorControlModuleImpl object is responsible for
	 * @param elevatorNum the number that will identify the elevator.
	 * @param maxFloors the maximum number of floors the elevator may visit.
	 * @throws NegativeCapacityException if the capacity value of the elevator objects is less than 1
	 * @throws NegativeElevatorException if elevatorNum is less than 1
	 * @throws NegativeFloorException if any elevators are created with invalid maximum or minimum floor values
	 */
	private void createElevators(int elevatorNum, int maxFloors) throws NegativeCapacityException, NegativeElevatorException, NegativeFloorException
	{
		if(elevatorNum < 1)
		{
			throw new NegativeElevatorException("Attempting to create zero or a negative number of Elevators! (elevatorNum: " + elevatorNum + ")");
		}

		elevators = new ElevatorInterface[elevatorNum];
		for(int i = 0; i < elevatorNum; ++i)
		{
			elevators[i] = ElevatorFactory.createElevator(i, XmlParser.getElevCapacity(), maxFloors, 1);
		}
	}

	/**
	 * Creates the collection of floors that this ElevatorControlModuleImpl object is responsible for
	 * @param floorNum the number of floors being added.
	 * @throws NegativeFloorException if floorNum is less than 1
	 */
	private void createFloors(int floorNum) throws NegativeFloorException
	{
		System.out.println("Creating " + floorNum + " floors..");

		if(floorNum < 1)
		{
			throw new NegativeFloorException("Attempting to create a control module without any floors! (floorNum: " + floorNum + ")");
		}

		floors = new FloorInterface[floorNum];
		for(int i = 0; i < floorNum; ++i)
		{
			floors[i] = FloorFactory.createFloor(i);
		}
	}

	/**
	 * Accessor for the total number of floors being managed by this Control Module Implementation
	 * @return the number of floors being managed by this Control Module Implementation
	 */
	@Override
	public int getMaxFloors()
	{
		return this.floors.length;
	}

	/**
	 * Accessor for the number of elevators currently being managed by this Control Module Implementation
	 * @return the number of elevators currently being managed by this Control Module
	 */
	@Override
	public int getElevatorNum()
	{
		return this.elevators.length;
	}
}
