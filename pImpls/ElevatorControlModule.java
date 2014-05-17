package pImpls;

import pExceptions.NegativeCapacityException;
import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pFactories.ControlImplFactory;
import pInterfaces.ControlModuleInterface;
import pInterfaces.ElevatorInterface;

/**
 * Constructor which allows for the creation of an elevator system.
 * The number of floors as well as the elevator number itself are used.
 */
public class ElevatorControlModule implements ControlModuleInterface 
{
	private static ControlModuleInterface delegate;
	private static volatile ElevatorControlModule instance;
	private static final int DEFAULT_ELEVATOR_NUM = 4;
	private static final int DEFAULT_FLOOR_NUM = 16;
	
   /** 
    * private method that uses delegation to create the elevator controller.
	* @param elevatorNum The number that identifies the elevator itself.
	* @param floorNum the floor number that will be passed to the elevator.
	* @throws NegativeNumberException throws an exception if either elevatorNum or floorNum are negative.
	*/
	private ElevatorControlModule(int elevatorNum, int floorNum)
	{
		try
		{
			delegate = ControlImplFactory.createElevatorController(elevatorNum, floorNum);
		}
		catch (NegativeFloorException | NegativeCapacityException e)
		{
			e.printStackTrace();
		}
	}

   /** 
	* Checks to see if the instance has been created already.
  	* If the instance already exists then return it.
	* If the instance is Null then create a new ElevatorControlModule.
  	* @return returns either the newly created instance or the previously existing instance.
  	*/
	public static ControlModuleInterface getInstance()
	{
		if(instance == null)
		{
			//synchronized is used to ensure only one instance is being created at a time
			synchronized(ElevatorControlModule.class)
			{
				if(instance == null)
				{
					instance = new ElevatorControlModule(DEFAULT_ELEVATOR_NUM, DEFAULT_FLOOR_NUM);
				}
			}
		}
		return instance;
	}
	
   /** 
    * Checks to see if the instance has been created already using the given parameters.
    * @param elevatorNum the number that is assigned to the elevator.
    * @param floorNum  the floor number that is being assigned to the elevator.
	* @return returns either the newly created instance or the previously existing instance
	*/
	public static ControlModuleInterface getInstance(int elevatorNum, int floorNum)
	{
		//If the instance already exists then return it
		if(instance == null)
		{		
			//If the instance is Null then create a new ElevatorControlModule
			//synchronized is used to ensure only one instance is created.
			synchronized(ElevatorControlModule.class)
			{
				if(instance == null)
				{
					instance = new ElevatorControlModule(elevatorNum, floorNum);
				}
			}
		}
		return instance;
	}
	
   /**
    * Handles the system that works with the calling of elevators to and from floors. Passes the direction and the floor number to an elevator
    * @param floorNumber the floor number that is delegated to an elevator. This number can not be negative and should exist in the building. 
    * @param directionRequest The requested direction to be delegated to an elevator.
    */
	@Override
	public void elevatorCallReceiver(int floorNumber, Direction directionRequest)
	{
		delegate.elevatorCallReceiver(floorNumber, directionRequest);
	}

   /**
    * ElevatorInterface returns the elevator corresponding to the requested index. This value can not be negative and should be within the index range
    * @param index the number used to retrieve the elevator at the specified index.
    * @return Delegates the elevator with the specified index.
    */
	@Override
	public ElevatorInterface getElevator(int index)
	{
		return delegate.getElevator(index);
	}
	
   /**
	* Retrieves the maximum number of floors.
	* @return returns the total number of floors to be delegated.
	*/
	public int getMaxFloors()
	{
		return delegate.getMaxFloors();
	}
	
   /**
    * Handles the functionality of stopping all elevators .
    * Delegates the shutDown method to allow for the Ending of all elevator instances.
    */
	public void shutDown()
	{
		delegate.shutDown();
	}
	
   /** 
    * Handles the functionality of opening the elevator door at a specified floor.
    * @param elevator the elevator that will have their doors opened.
    * @param floorNumber the floor number of the elevator that will have it's doors opened.
    * Delegates the method to allow for different elevators to perform a similar task.
    */
	@Override
	public void elevatorDoorsOpened(ElevatorInterface elevator, int floorNumber)
	{
		delegate.elevatorDoorsOpened(elevator, floorNumber);
	}

   /** 
    * Handles the functionality of adding a person to the floor.
    * @param inPerson the object person that will be added to the floor.
    * @param floorNum the floor number the person will be added to.
    * Delegates the method to allow for multiple persons to be added to different floors.
 * @throws NullPassengerException 
    */
	@Override
	public void addPersonToFloor(Person inPerson, int floorNum) throws NullPassengerException
	{
		delegate.addPersonToFloor(inPerson, floorNum);	
	}

	@Override
	public int getElevatorNum()
	{
		return delegate.getElevatorNum();
	}
}
