package pImpls;

import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pFactories.PersonFactory;
import pInterfaces.ElevatorInterface;

/**
 * SimulationEnvironment class handles the creation of the elevator simulation given the number of floors and elevators.
 */
public class SimulationEnvironment
{
	private volatile static SimulationEnvironment instance;
	public static final int DEFAULT_ELEVATOR_NUM = 4;
	public static final int DEFAULT_FLOOR_NUM = 16;
	public static int ELEVATOR_NUM;
	public static int FLOOR_NUM;
    
	/**
	 * Non-default private constructor for the SimulationEnvironment.
	 * @param numFloors the number of floors to create for the simulation
	 * @param numElevators the number of elevators to create for the simulation
	 */
	private SimulationEnvironment(int numElevators, int numFloors)
	{
		ElevatorControlModule.getInstance(numElevators, numFloors);
		ELEVATOR_NUM = numElevators;
		FLOOR_NUM = numFloors;
	}
	
	/**
	 * Default private constructor for the SimulationEnvironment. Passes off default values to the ElevatorControlModule's getInstance() method,
	 * which will call the Module's constructor
	 */
	private SimulationEnvironment()
	{
		ElevatorControlModule.getInstance(DEFAULT_ELEVATOR_NUM, DEFAULT_FLOOR_NUM);
		ELEVATOR_NUM = DEFAULT_ELEVATOR_NUM;
		FLOOR_NUM = DEFAULT_FLOOR_NUM;
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
	
	/**
	 * Begins the simulation
	 */
	public void startSimulation()
	{
		
		try
		{
			//by placing a person in a floor, we can simulate the action of having a person call the elevator from a given floor
			//we give them a destination of floor 0 to indicate that they have no destination and that they are only spawned to call the elevator.
			//their request for floor 0 will be ignored by the elevator
			instance.addPersonToFloor(PersonFactory.createPerson(11,14), 11);
			instance.addPersonToFloor(PersonFactory.createPerson(14,15), 14);
			instance.addPersonToFloor(PersonFactory.createPerson(13,14), 13);
			instance.addPersonToFloor(PersonFactory.createPerson(15,16), 15);
			
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
		catch (InterruptedException | NullPassengerException | NegativeFloorException e)
        {
            e.printStackTrace();
        }
		
	}

	/**
	 * Ends the simulation
	 */
	private void stopSimluation()
	{
		ElevatorControlModule.getInstance().shutDown();
	}
	
	/**
	 * Retrieves the ElevatorInterface object at the specified location.
	 * @param i the index of the ElevatorInterface object to retrieve. NOTE: this method uses ONE-BASED indexing, which makes 0 an invalid entry
	 * @return the ElevatorInterface object stored at the index specified
	 */
	private ElevatorInterface getElevator(int i)
	{
		return ElevatorControlModule.getInstance().getElevator(i);
	}
	
	/**
	 * Adds a Person object to the specified floor. Once on the floor, the Person will summon an elevator
	 * @param inPerson The person to add to the FloorInterface object
	 * @param floorNum A number which corresponds to a FloorInterface object. NOTE: this method uses ONE-BASED indexing, which means 0 does not correspond to the first floor.
	 * @throws NullPassengerException if inPerson is null
	 * @throws NegativeFloorException if floorNum is less than 1 or greater than the maximum number of floors in the simulation
	 */
	private void addPersonToFloor(Person inPerson, int floorNum) throws NullPassengerException, NegativeFloorException
	{
		ElevatorControlModule.getInstance().addPersonToFloor(inPerson, floorNum);
	}
}
