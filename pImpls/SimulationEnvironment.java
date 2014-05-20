package pImpls;

import pExceptions.NegativeCapacityException;
import pExceptions.NegativeElevatorException;
import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pFactories.PersonFactory;
import pInterfaces.ElevatorInterface;

/**
 * SimulationEnvironment class handles the creation of the elevator simulation given the number of floors and elevators.
 */
public class SimulationEnvironment
{
	/**
	 * The private SimulationEnvironment instance required for this class to be considered a singleton.
	 */
	private volatile static SimulationEnvironment instance;
	
	/**
	 * Constant which determines the default number of elevators to create when the default constructor is called.
	 */
	public static final int DEFAULT_ELEVATOR_NUM = 4;
	
	/**
	 * Constant which determines the default number of floors to create when the default constructor is called.
	 */
	public static final int DEFAULT_FLOOR_NUM = 16;
	
	/**
	 * Static integer representing how many elevators are in the simulation environment. Used for error checking in various places in order to
	 * ensure that invalid elevator values are not used in classes which hold elevator indices.
	 * 
	 */
	public static int ELEVATOR_NUM;
	
	/**
	 * Static integer representing how many floors are in the simulation environment. Used for error checking to ensure that invalid floor values
	 * are not used in the classes which hold floor indices.
	 */
	public static int FLOOR_NUM;
    
	/**
	 * Non-default private constructor for the SimulationEnvironment.
	 * @param numFloors the number of floors to create for the simulation
	 * @param numElevators the number of elevators to create for the simulation
	 * @throws NegativeElevatorException 
	 * @throws NegativeCapacityException 
	 * @throws NegativeFloorException 
	 */
	
        
            // We don't need this now that sim envoronment isn't taking straight up input
        /*
        private SimulationEnvironment(int numElevators, int numFloors) throws NegativeFloorException, NegativeCapacityException, NegativeElevatorException
	{
		ElevatorControlModule.getInstance(numElevators, numFloors);
		ELEVATOR_NUM = numElevators;
		FLOOR_NUM = numFloors;
	}
	*/
        
        
	/**
	 * Default private constructor for the SimulationEnvironment. Passes off default values to the ElevatorControlModule's getInstance() method,
	 * which will call the Module's constructor
	 * @throws NegativeElevatorException 
	 * @throws NegativeCapacityException 
	 * @throws NegativeFloorException 
	 */
	private SimulationEnvironment() throws NegativeFloorException, NegativeCapacityException, NegativeElevatorException
	{
		/*   old impl  ElevatorControlModule.getInstance(DEFAULT_ELEVATOR_NUM, DEFAULT_FLOOR_NUM);
		ELEVATOR_NUM = DEFAULT_ELEVATOR_NUM;
		FLOOR_NUM = DEFAULT_FLOOR_NUM; */
            ElevatorControlModule.getInstance();
            
            
	}
	
	/**
	 * Public accessor for the singleton instance of this class. The input parameters are only used if this is the first time that the singleton
	 * is being accessed. Once the singleton instance is intialized, this method need not be called again. Instead, call the "default" overload of this method.
	 * @param numFloors The number of floors to create for the simulation assuming that instance has not been created yet. Must be greater than 0.
	 * @param numElevators The number of elevators to create for the simulation assuming that instance has not been created yet. Must be greater than 0.
	 * @return The static SimulationEnvironment object that is owned by all instances of this class, initialized to hold the passed in number of floors
	 * and elevators if this is the first time that this method is being called.
	 * @throws NegativeElevatorException 
	 * @throws NegativeCapacityException 
	 * @throws NegativeFloorException 
	 */
	public static SimulationEnvironment getInstance(int numFloors, int numElevators) throws NegativeFloorException, NegativeCapacityException, NegativeElevatorException
	{
		synchronized(SimulationEnvironment.class)
		{
			if(instance == null)
			{
				synchronized(SimulationEnvironment.class)
				{
					//  old with paramsinstance = new SimulationEnvironment(numFloors, numElevators);
                                    
                                        instance = new SimulationEnvironment();
				}
			}
			return instance;
		}	
	}
	
	/**
	 * Public "default" accessor for the singleton instance of this class. If the instance has not been created yet, it is initialized with private default
	 * values. Once instance has been initialized, this is the preferred method to call in order to access it.
	 * @return The static SimulationEnvironment object that is owned by all instances of this class, initialized to hold the default number of floors
	 * and elevators if this is the first time that this method is being called.
	 * @throws NegativeElevatorException if the default elevator number passed into the object is less than 1.
	 * @throws NegativeCapacityException if the capacity of the elevators that are created on first call is less than 1.
	 * @throws NegativeFloorException if the default number of floors to create upon first call is less than 1.
	 */
	public static SimulationEnvironment getInstance() throws NegativeFloorException, NegativeCapacityException, NegativeElevatorException
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
		catch (InterruptedException | NullPassengerException | NegativeFloorException | NegativeElevatorException e)
        {
            e.printStackTrace();
        }
		
	}

	/**
	 * Ends the simulation
	 */
	private void stopSimluation()
	{
		try
		{
			ElevatorControlModule.getInstance().shutDown();
		}
		catch (NegativeFloorException | NegativeCapacityException
				| NegativeElevatorException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves the ElevatorInterface object at the specified location.
	 * @param i the index of the ElevatorInterface object to retrieve. NOTE: this method uses ONE-BASED indexing, which makes 0 an invalid entry
	 * @return the ElevatorInterface object stored at the index specified
	 * @throws NegativeElevatorException if i is less than 1 or greater than the number of elevators present in the simulation
	 */
	private ElevatorInterface getElevator(int i) throws NegativeElevatorException
	{
		try
		{
			return ElevatorControlModule.getInstance().getElevator(i);
		}
		catch (NegativeCapacityException | NegativeFloorException e)
		{
			e.printStackTrace();
			return null;
		}
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
		try
		{
			ElevatorControlModule.getInstance().addPersonToFloor(inPerson, floorNum);
		}
		catch (NegativeCapacityException | NegativeElevatorException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
