package pImpls;

import pExceptions.NegativeCapacityException;
import pExceptions.NegativeElevatorException;
import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pFactories.ControlImplFactory;
import pInterfaces.ControlModuleInterface;
import pInterfaces.ElevatorInterface;

/**
 * Constructor which allows for the creation of an elevator system.
 * This class delegates the actual selection algorithm, allowing the algorithm to change at runtime if desired.
 */
public class ElevatorControlModule implements ControlModuleInterface 
{
	/**
	 * Delegate object which allows for different elevator selection algorithms to be switched in during runtime.
	 * This object allows this class to implement the Strategy design pattern
	 */
	private static ControlModuleInterface delegate;
	
	/**
	 * The underlying ElevatorControlModule object required for this class to implement the Singleton design pattern
	 */
	private static volatile ElevatorControlModule instance;

	
   /** 
    * private constructor that is called upon the first call to getInstance().
	* @param elevatorNum The number of elevators that the module will control.
	* @param floorNum the number of floors that the module will receive signals from and send elevators to.
	* @throws NegativeElevatorException 
	* @throws NegativeCapacityException 
	* @throws NegativeFloorException 
	* @throws NegativeNumberException throws an exception if either elevatorNum or floorNum are negative.
	*/
	private ElevatorControlModule() throws NegativeFloorException, NegativeCapacityException, NegativeElevatorException
	{
		delegate = ControlImplFactory.createElevatorController();
	}

   /** 
	* Checks to see if the instance has been created already.
  	* If the instance already exists then return it.
	* If the instance is Null then create a new ElevatorControlModule.
  	* @return returns either the newly created instance or the previously existing instance.
  	* @throws NegativeElevatorException 
  	* @throws NegativeCapacityException 
  	* @throws NegativeFloorException 
  	*/
	public static ControlModuleInterface getInstance() throws NegativeFloorException, NegativeCapacityException, NegativeElevatorException
	{
		if(instance == null)
		{
			//synchronized is used to ensure only one instance is being created at a time
			synchronized(ElevatorControlModule.class)
			{
				if(instance == null)
				{
				//	instance = new ElevatorControlModule(SimulationEnvironment.DEFAULT_ELEVATOR_NUM, SimulationEnvironment.DEFAULT_FLOOR_NUM);
                                    // changed, dont think we need to pass the elev numbers and floor numbers anymore!
                                    
                                    instance = new ElevatorControlModule();
                                    
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
	* @throws NegativeElevatorException 
	* @throws NegativeCapacityException 
	* @throws NegativeFloorException 
	*/
	public static ControlModuleInterface getInstance(int elevatorNum, int floorNum) throws NegativeFloorException, NegativeCapacityException, NegativeElevatorException
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
					instance = new ElevatorControlModule();
				}
			}
		}
		return instance;
	}
	
   /**
    * Handles the system that works with the calling of elevators to and from floors. Passes the direction and the floor number to an elevator
    * @param floorNumber the floor number that is delegated to an elevator. This number can not be negative and should exist in the building. 
    * @param directionRequest The requested direction to be delegated to an elevator.
    * @throws NegativeFloorException 
    */
	@Override
	public void elevatorCallReceiver(int floorNumber, Direction directionRequest) throws NegativeFloorException
	{
		delegate.elevatorCallReceiver(floorNumber, directionRequest);
	}

   /**
    * ElevatorInterface returns the elevator corresponding to the requested index. This value can not be negative and should be within the index range
    * @param index the number used to retrieve the elevator at the specified index.
    * @return the elevator with the given index within the module's elevator collection.
    */
	@Override
	public ElevatorInterface getElevator(int index)
	{
		return delegate.getElevator(index);
	}
	
   /**
	* Retrieves the maximum number of floors.
	* @return returns the total number of floors that the elevator controller knows about.
	*/
	public int getMaxFloors()
	{
		return delegate.getMaxFloors();
	}
	
   /**
    * Handles the functionality of stopping all elevators.
    * Delegates the shutDown method to allow for the Ending of all elevator instances.
    */
	public void shutDown()
	{
		delegate.shutDown();
	}
	
   /** 
    * Handles the functionality of opening the elevator door at a specified floor and subsequently moving people into the elevator.
    * @param elevator the elevator that has arrived at this floor and is opening its doors for passengers to enter. 
    * @param floorNumber the floor number that the elevator is currently located. The people contained on this floor will be moved into the 
    * elevator once the doors are opened.
    * @throws NegativeFloorException 
    */
	@Override
	public void elevatorDoorsOpened(ElevatorInterface elevator, int floorNumber) throws NegativeFloorException
	{
		delegate.elevatorDoorsOpened(elevator, floorNumber);
	}

   /** 
    * Handles the functionality of adding a person to the floor.
    * @param inPerson the object person that will be added to the floor.
    * @param floorNum the floor number the person will be added to. NOTE: This method uses ONE-BASED indexing instead of 0 based indexing,
    * which means that 0 does not correspond to the first floor of the SimulationEnvironment
    * @throws NullPassengerException if inPerson is null
    * @throws NegativeFloorException if floorNum corresponds to an invalid floor index
    */
	@Override
	public void addPersonToFloor(Person inPerson, int floorNum) throws NullPassengerException, NegativeFloorException
	{
		delegate.addPersonToFloor(inPerson, floorNum);	
	}

	/**
	 * Accessor for the number of elevators currently being managed by this Control Module
	 * @return the number of elevators currently being managed by this Control Module
	 */
	@Override
	public int getElevatorNum()
	{
		return delegate.getElevatorNum();
	}
}
