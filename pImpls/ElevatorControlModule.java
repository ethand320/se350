package pImpls;

import pFactories.ControlImplFactory;
import pInterfaces.ControlModuleInterface;
import pInterfaces.ElevatorInterface;

/**
 * Constructor which allows for the creation of an elevator system as well as the elevator calling system.
 * @param paramCapacity The number of people this elevator can hold at a given time. When the elevator hits capacity, no more people may board the elevator.
 * @param numFloors The number of floors that this elevator services. This value cannot be larger than the number of floors in the building that owns it, but it may be smaller
 * @param numElevators The number of floors that this elevator services. This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
 * @throws NegativeCapacityException if any of the values passed into it are negative
 */
public class ElevatorControlModule implements ControlModuleInterface 
{
	private static ControlModuleInterface delegate;
	private static volatile ElevatorControlModule instance;
	private static final int DEFAULT_ELEVATOR_NUM = 4;
	private static final int DEFAULT_FLOOR_NUM = 16;
	
	private ElevatorControlModule(int elevatorNum, int floorNum)
	{
		delegate = ControlImplFactory.createElevatorController(elevatorNum, floorNum);
	}
	
	public static ControlModuleInterface getInstance()
	{
		if(instance == null)
		{
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
	
	public static ControlModuleInterface getInstance(int elevatorNum, int floorNum)
	{
		if(instance == null)
		{
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
     * Handles the system that works with the calling of elevators to and from floors.
     * Inherited from ControlModuleInterface
     */
	@Override
	public void elevatorCallReceiver(int floorNumber, Direction directionRequest)
	{
		delegate.elevatorCallReceiver(floorNumber, directionRequest);
	}

	@Override
	public ElevatorInterface getElevator(int index) {
		return delegate.getElevator(index);
	}
	
	public void shutDown()
	{
		delegate.shutDown();
	}

	@Override
	public void elevatorDoorsOpened(ElevatorInterface elevator, int floorNumber)
	{
		delegate.elevatorDoorsOpened(elevator, floorNumber);
		
	}

	@Override
	public void addPersonToFloor(Person inPerson, int floorNum)
	{
		delegate.addPersonToFloor(inPerson, floorNum);
		
	}
}
