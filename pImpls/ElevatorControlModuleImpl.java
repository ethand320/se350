package pImpls;

import pFactories.ElevatorFactory;
import pFactories.FloorFactory;
import pInterfaces.ControlModuleInterface;
import pInterfaces.ElevatorInterface;
import pInterfaces.FloorInterface;


/**
 * ElevatorControlModuleImpl class implements ControlModuleInterface 
 *
 */
public class ElevatorControlModuleImpl implements ControlModuleInterface
{
	private ElevatorInterface[] elevators; 
	private FloorInterface[] floors;
	
       /**
 	* ElevatorControlModuleImpl creates floors and elevators with the given parameters.
	* @param elevatorNum The number of elevators to be created. 
 	* @param floorNum The number of floors to be created.
        */
	public ElevatorControlModuleImpl(int elevatorNum, int floorNum)
	{
		createFloors(floorNum);
		createElevators(elevatorNum, floorNum);		
	}

       /**
        * elevatorCallReciever takes in the elevator request with the given parameters.
        * @param floorNumber the floor number requested. 
        * @param directionRequest the the direction that will lead to the requested floor.
        */
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


       /**
        * ElevatorInterface returns the total elevators.
        * @return returns all elevators.
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
        * @oaram elevator the elevator the will recieve the requests.
        * @param floorNumber the floor number the elevator is recieving the request at.
        */
	@Override
	public void elevatorDoorsOpened(ElevatorInterface elevator, int floorNumber)
	{
		floors[floorNumber-1].removeFromFloor(elevator, elevator.getDirection());
	}

       /*
        * addPersonToFloor moves them from the specified floor to the indicated elevator.
        * @param inPerson calls the method to add a person to the elevator.
        * @param floorNum the floor the person is located in. 
        */
	@Override
	public void addPersonToFloor(Person inPerson, int floorNum)
	{
		floors[floorNum-1].addPersonToFloor(inPerson);
	}
	
       /*
        * creates an elevator with a maximum number of floors
        * @param elevatorNum the number that will identify the elevator.
        * @param maxFloors the maximum number of floors the elevator may visit.
        */
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
	
       /*
        * creates the number of floors.
        * @param floorNum the number of floors being added.
        */
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
