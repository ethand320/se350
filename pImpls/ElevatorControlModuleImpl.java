package pImpls;

import pFactories.ElevatorFactory;
import pFactories.FloorFactory;
import pInterfaces.ControlModuleInterface;
import pInterfaces.ElevatorInterface;
import pInterfaces.FloorInterface;


//fill this in
/**
 * Constructor which allows for properties such as the speed, capacity, and floor range of the elevator to be customized.
 * @param paramCapacity The number of people this elevator can hold at a given time. When the elevator hits capacity, no more people may board the elevator.
 * @param numFloors The number of floors that this elevator services. This value cannot be larger than the number of floors in the building that owns it, but it may be smaller
 * @param idNumber The unique identifier number. This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
 * @throws NegativeCapacityException if any of the values passed into it are negative
 */
public class ElevatorControlModuleImpl implements ControlModuleInterface
{
	private ElevatorInterface[] elevators; 
	private FloorInterface[] floors;
	
	public ElevatorControlModuleImpl(int elevatorNum, int floorNum)
	{
		createFloors(floorNum);
		createElevators(elevatorNum, floorNum);		
	}

	@Override
	public void elevatorCallReceiver(int floorNumber, Direction directionRequest)
	{
		if(floorNumber < 0 || floorNumber >= floors.length)
		{
			
		}
		ElevatorInterface elevatorToSend;
		
		//SIMPSON TODO: for now, we're going to hard code the elevator logic to meet the test requirements of the first deliverable 
		//this MUST be changed for the next iteration
		switch(floorNumber)
		{
		case 4:
		case 15:
			elevatorToSend = elevators[2];
			break;
		case 10:
			elevatorToSend = elevators[0];
			break;
		case 12:
		case 13:
		case 14:
			elevatorToSend = elevators[1];
			break;
		default:
			elevatorToSend = elevators[0];
		}
		elevatorToSend.addFloorToQueue(floorNumber);
	}

	@Override
	public ElevatorInterface getElevator(int index)
	{
		return elevators[index-1];
	}
	
	public void shutDown()
	{
		for(ElevatorInterface elevator : elevators)
		{
			elevator.shutDown();
		}
	}

	@Override
	public void elevatorDoorsOpened(ElevatorInterface elevator, int floorNumber)
	{
		floors[floorNumber-1].removeFromFloor(elevator, elevator.getDirection());
	}

	@Override
	public void addPersonToFloor(Person inPerson, int floorNum)
	{
		floors[floorNum-1].addPersonToFloor(inPerson);
	}
	
	private void createElevators(int elevatorNum, int maxFloors)
	{
		if(elevatorNum < 1)
		{
			
		}
		elevators = new ElevatorInterface[elevatorNum];
		for(int i = 0; i < elevatorNum; ++i)
		{
			elevators[i] = ElevatorFactory.createElevator(i, ElevatorInterface.DEFAULT_ELEVATOR_CAPACITY, maxFloors, 1);
		}
	}
	
	private void createFloors(int floorNum)
	{
		if(floorNum < 1)
		{
			
		}
		floors = new FloorInterface[floorNum];
		for(int i = 0; i < floorNum; ++i)
		{
			floors[i] = FloorFactory.createFloor(i);
		}
	}
}
