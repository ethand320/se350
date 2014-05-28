package pImpls;

import java.util.Random;

import pExceptions.NegativeCapacityException;
import pExceptions.NegativeElevatorException;
import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pFactories.PersonFactory;

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
	 * Default private constructor for the SimulationEnvironment. Passes off default values to the ElevatorControlModule's getInstance() method,
	 * which will call the Module's constructor
	 * @throws NegativeElevatorException if the ECM constructor receives a total elevator number that is less than 1
	 * @throws NegativeCapacityException if the ECM constructor receives an elevator capacity number that is less than 1
	 * @throws NegativeFloorException if the ECM constructor receives a total floor number that is less than 1
	 */
	private SimulationEnvironment() throws NegativeFloorException, NegativeCapacityException, NegativeElevatorException
	{                
		ElevatorControlModule.getInstance(); 
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
			System.out.println("The simulation will run for " + ( XmlParser.getDuration() / 1000 ) + " seconds.");
			getInstance().randPersonGenerator(XmlParser.getDuration(), XmlParser.getPeoplePerMin());

			//let the thread wait enough time for every elevator to reach the default floor
			//movement speed * ( door open speed + door close speed ) * number of floors
			int totalSleepTime = XmlParser.getTotalFloorNumber() * ( ( XmlParser.getElevDoorTime() * 2 ) + XmlParser.getElevTravelTime() );
			System.out.println("The simulation will sleep for " + totalSleepTime / 1000 + " seconds before shutting down completely.");
			Thread.sleep(totalSleepTime);
			System.out.println("The simulation is shutting down now");
			getInstance().stopSimluation();  // kill simulation after time is up (determined by randPersGen method
		}
		catch(InterruptedException | NegativeFloorException | NegativeCapacityException | NegativeElevatorException e)
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
			e.printStackTrace();
		}
	}

	/**
	 * Helper method to generate a specified number of Person objects per minute while the simualation is running
	 * @param totalSimTime the amount of time (in milliseconds) that the simulation should run for
	 * @param personRate the number of Person objects that should be created every minute (60000 milliseconds)
	 */
	private void randPersonGenerator(long totalSimTime, int personRate)
	{
		long tStart = System.currentTimeMillis();
		int totalFloors = XmlParser.getTotalFloorNumber();
		try
		{
			Random randomGenerator = new Random();
			int totalPeopleCreated = 0;
			while(totalSimTime > (System.currentTimeMillis()- tStart))
			{
				int randStartFloor, randEndFloor;

				for(int i = 0; i < XmlParser.getPeoplePerMin(); ++i)
				{
					//make sure these two numbers aren't the same
					do
					{
						randStartFloor =  randomGenerator.nextInt(totalFloors) + 1;
						randEndFloor = randomGenerator.nextInt(totalFloors) + 1;
					}
					while(randStartFloor == randEndFloor);
					Person newPerson = PersonFactory.createPerson(totalPeopleCreated++, randStartFloor, randEndFloor);
					System.out.println("Person " + newPerson.getID() + " going to floor " + randEndFloor + " is being created and added to a floor #: " + randStartFloor);

					addPersonToFloor(newPerson, randStartFloor);
				}
				Thread.sleep(60000);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}   
}
