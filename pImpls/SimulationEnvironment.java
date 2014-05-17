package pImpls;

import pExceptions.NullPassengerException;
import pFactories.PersonFactory;
import pInterfaces.ElevatorInterface;

/**
 * SimulationEnvironment class handles the creation of the elevator simulation given the number of floors and elevators.
 * This is what will run all the elevators until control module logic is fully implemented.
 */
public class SimulationEnvironment
{
	private volatile static SimulationEnvironment instance;
	
        /**
	 * 
	 */
	private SimulationEnvironment(int numFloors, int numElevators)
	{
		ElevatorControlModule.getInstance(numFloors, numElevators);
	}
	
	/**
	 * 
	 */
	private SimulationEnvironment()
	{
		ElevatorControlModule.getInstance();
	}
	
	/**
	 * 
	 */
	public static SimulationEnvironment getInstance(int numFloors, int numElevators)
	{
		synchronized(SimulationEnvironment.class)
		{
			if(instance == null)
			{
				synchronized(SimulationEnvironment.class)
				{
					instance = new SimulationEnvironment(numFloors, numElevators);
				}
			}
			return instance;
		}	
	}
	
	public static SimulationEnvironment getInstance()
	{
		synchronized(SimulationEnvironment.class)
		{
			if(instance == null)
			{
				synchronized(SimulationEnvironment.class)
				{
					instance = new SimulationEnvironment();
				}
			}
			return instance;
		}	
	}
	
	public void startSimulation()
	{
		
		try
		{
			//by placing a person in a floor, we can simulate the action of having a person call the elevator from a given floor
			//we give them a destination of floor 0 to indicate that they have no destination and that they are only spawned to call the elevator.
			//their request for floor 0 will be ignored by the elevator
			instance.addPersonToFloor(PersonFactory.createPerson(11,0), 11);
			instance.addPersonToFloor(PersonFactory.createPerson(14,0), 14);
			instance.addPersonToFloor(PersonFactory.createPerson(13,0), 13);
			instance.addPersonToFloor(PersonFactory.createPerson(15,0), 15);
			
			//allow the elevators time to go idle and go back to the bottom floor
			System.out.println("Going to sleep now (1/2)");
	        Thread.sleep(15000);
	        System.out.println("Waking up now (1/2)");
			
			//by placing a person in the floor and giving them a destination floor, we can simulate the action of having that person enter the elevator and press the floor button inside the elevator
			instance.addPersonToFloor(PersonFactory.createPerson(5,16), 5);
			
			//by creating a new person and placing him on the 16th floor, we ensure that the request for floors 2, 3, and 5 aren't processed until the elevator reaches that destination
			instance.addPersonToFloor(PersonFactory.createPerson(16,2), 16);
			
			//manually attempt to add floor 1 to the elevator's queue while it is going up. this call should fail
			instance.getElevator(3).addFloorToQueue(1);
			
			//allow the elevator enough time to reach the top floor and receive its request to go to the fifth floor
			Thread.sleep(8500);
			
			instance.getElevator(3).addFloorToQueue(3);
			instance.getElevator(3).addFloorToQueue(5);
			
			//allow the elevators time to run their floor requests and go idle
			System.out.println("Going to sleep now (2/2)");
	        Thread.sleep(10000);
	        System.out.println("Waking up now (2/2)");
	        
	        	        
	        System.out.println("Ending simulation");
	        instance.stopSimluation();
		}
		catch (InterruptedException | NullPassengerException e)
        {
            e.printStackTrace();
        }
		
	}

	private void stopSimluation()
	{
		ElevatorControlModule.getInstance().shutDown();
	}
	
	private ElevatorInterface getElevator(int i)
	{
		return ElevatorControlModule.getInstance().getElevator(i);
	}
	
	private void addPersonToFloor(Person inPerson, int floorNum) throws NullPassengerException
	{
		ElevatorControlModule.getInstance().addPersonToFloor(inPerson, floorNum);
	}
}
