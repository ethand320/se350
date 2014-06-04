package pImpls;

import java.util.ArrayList;
import java.util.Collections;

import pExceptions.NegativeCapacityException;
import pExceptions.NegativeElevatorException;
import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pExceptions.PassengerNotFoundException;
import pInterfaces.ElevatorInterface;


/**
 *  Elevator class that handles properties such as the speed, capacity, and floor range of the elevator to be customized.
 */
public class Elevator implements ElevatorInterface, Runnable
{
	/**
	 * The current floor that this Elevator object is on. This value is an internal representation, so it uses ZERO-BASED indexing
	 */
	private int currentFloor;

	/**
	 * The direction that this elevator is currently traveling
	 */
	private Direction direction;

	/**
	 * How fast it should take the elevator to traverse a floor, measured in milliseconds
	 */
	private int speed;

	/**
	 * How fast the Elevator's doors should open, measured in milliseconds
	 */
	private int doorSpeed;

	/**
	 * The list of requests that this elevator object should handle as it travels
	 */
	private ArrayList <Integer> requestQueue;

	/**
	 * The list of Person objects currently inside of the elevator.
	 */
	private ArrayList<Person> passengerList;

	/**
	 * Whether the Elevator's doors are currently open or not. If they are open, then passengers can enter/leave the Elevator
	 */
	private boolean bDoorsOpen;

	/**
	 * How many passengers this Elevator can hold
	 */
	private int capacity;

	/**
	 * The index representing this Elevator's position in relation to the rest of the Elevators within the simulation. This value is an internal representation, so it uses ZERO-BASED indexing.
	 */
	private int elevatorId;

	/**
	 * The thread object that allows each Elevator object to run independently of other Elevator objects
	 */
	private Thread elevatorThread = new Thread(this);

	/**
	 * Whether or not the elevator is currently running. Once set to false, it cannot be set back to true
	 */
	private boolean running = true;

	/**
	 * The index of the top-most floor that this Elevator can visit. This value is an internal representation, so it uses ZERO-BASED indexing
	 */
	private int maxFloors;

	/**
	 * The index of the bottom-most floor that this Elevator can visit. This value is an internal representation, so it uses ZERO-BASED indexing
	 */
	private int minFloors;


	/**
	 * Constructor for Elevator. Takes in the elevator Id, capacity, and max/min floors.
	 * @param inId The elevators id. This number cannot be negative and must be independent of another elevators Id.
	 * @param inCapacity The total number of passengers the elevator can hold. This number cannot be negative and must not be less than {@ Value}
	 * @param inMaxFloors The maximum amount of floors the elevator can access.
	 * @param inMinFloors The minimum amount of floors the elevator can access.
	 * @throws NegativeCapacityException if inCapacity is less than 1
	 * @throws NegativeFloorException if inMinFloors specifies a floor that is outside the bounds of the simulation (less than 1 or greater than the total number of floors in teh simulation)
	 */
	public Elevator(int inId, int inCapacity, int inMaxFloors, int inMinFloors) throws NegativeCapacityException, NegativeFloorException
	{
		setId(inId);
		setCapacity(inCapacity);
		setMaxFloors(inMaxFloors);
		setMinFloors(inMinFloors);   
		setSpeed(XmlParser.getElevDoorTime());    
		setDoorSpeed(XmlParser.getDoorSpeed());
		initializeRequestQueue();
		setInitialDirection();
		setDefaultFloor(0);
		createPassengerList();
		elevatorThread.start();
	}

	/**
	 * Simulates the act of calling an elevator.  Only accepted if floor is on the elevator's path
	 * @param floorNum The floor number that will be added to the queue. It need not be in consecutive order compared to other elevators in the building.
	 * If the request is already present inside the requests queue, nothing happens
	 * NOTE: this method uses ONE-BASED indexing, which means that zero corresponds to an invalid request.
	 * @throws NegativeFloorException if floorNum is less than 1 or greater than the total number of floors in the simulation
	 */
	@Override
	public synchronized void addFloorToQueue(int floorNum) throws NegativeFloorException 
	{            
		int internalFloorNum = floorNum - 1;
		if(internalFloorNum < 0 || internalFloorNum > XmlParser.getTotalFloorNumber())
		{
			throw new NegativeFloorException("Attempting to add an invalid floor number to Elevator " + this.getElevatorId() + "'s request queue! (floorNum: " + floorNum + ")");
		}
		if(requestQueue.contains(internalFloorNum))
		{
			//the floor already exists in the queue, so there is nothing that needs to be done
			return;
		}

		//we're at the last floor that this elevator can service. only way to go is DOWN
		if(this.currentFloor == this.maxFloors)
		{
			this.direction = Direction.DOWN;
		}
		//we're at the bottommost floor that this elevator can service. only way to go is UP
		else if(this.currentFloor == this.minFloors)
		{
			this.direction = Direction.UP;
		}
		//if the floor doesn't have any requests, it might as well be considered IDLE so it can take requests otherwise going in the opposite direction
		else if(this.requestQueue.isEmpty())
		{
			this.direction = Direction.IDLE;
		}

		switch (this.direction) 
		{
		case UP:
			if (internalFloorNum > this.currentFloor)
			{
				synchronized(this)
				{
					requestQueue.add(internalFloorNum);
					Collections.sort(requestQueue);
					notifyAll();
				}
				System.out.println(DataLogger.printTimeStamp() + ":     Request for floor " + floorNum + " was added to elevator: " + ( this.getElevatorId() + 1 ));
				printRequestQueue();
			}
			else if(internalFloorNum == this.currentFloor)
			{
				this.openDoors();
				this.closeDoors();
			}
			else
			{
				System.out.println(DataLogger.printTimeStamp() + ":     Request for floor " + floorNum + " was rejected by the elevator: " + ( this.getElevatorId() + 1 ));
			}
			break;
		case DOWN:
			if (internalFloorNum < this.currentFloor)
			{
				synchronized(this)
				{
					requestQueue.add(internalFloorNum);
					Collections.reverse(requestQueue);
					notifyAll();
				}
				System.out.println(DataLogger.printTimeStamp() + ":     Request for floor " + floorNum + " was added to elevator: " + ( this.getElevatorId() + 1 ));
				this.printRequestQueue();
			}
			else if(internalFloorNum == this.currentFloor)
			{
				this.openDoors();
			}
			else
			{
				System.out.println(DataLogger.printTimeStamp() + ":     Request for floor " + floorNum + " was rejected by the elevator: " + ( this.getElevatorId() + 1 ));
			}
			break;
		case IDLE:
			synchronized(this)
			{
				requestQueue.add(internalFloorNum);
				notifyAll();
			}
			System.out.println(DataLogger.printTimeStamp() + ":     Request for floor " + floorNum + " was added to elevator: " + ( this.getElevatorId() + 1 ));
			if (currentFloor < internalFloorNum)
			{
				direction = Direction.UP;
				Collections.sort(requestQueue);
			}
			else
			{
				direction = Direction.DOWN;
				Collections.reverse(requestQueue);
			}
			this.printRequestQueue();
			break;
		}
	}

	/**
	 * Add a passenger to the elevator and adds its destination floor to the elevator's request queue.
	 * @param inPassenger The Person object being added to this elevator object. Must not be null
	 * @return true if the Person object was added to the elevator successfully, false if the elevator is at capacity 
	 * @throws NullPassengerException if inPassenger is set to null
	 * @throws NegativeFloorException if inPassenger's destination floor is less than 1 or greater than the number of floors in the simulation.
	 */
	@Override
	public synchronized boolean addPassenger(Person inPassenger) throws NullPassengerException, NegativeFloorException
	{
		if(inPassenger == null)
		{
			throw new NullPassengerException("The passenger object that is being added to the elevator is null!");
		}
		if(this.passengerList.size() >= this.capacity)
		{
			System.out.println(DataLogger.printTimeStamp() + ":     Adding person " + inPassenger.getID() + " to Elevator " + ( this.getElevatorId() + 1 ) + " failed because the elevator is already full!");
			return false;
		}
		System.out.println(DataLogger.printTimeStamp() + ":    Person " + inPassenger.getID() + " has entered Elevator " + ( this.getElevatorId() + 1 ) );
		this.passengerList.add(inPassenger);
		this.addFloorToQueue(inPassenger.getDestinationFloor());
		return true;
	}


	/**
	 * Add multiple passengers to the elevator and adds all of their destination floors to the elevator's request queue.
	 * @param inPassengers The collection of Person objects to add to the elevator object. None of the Person objects in this collection should be null.
	 * @return true if every passenger was added to the elevator successfully, otherwise false.
	 * @throws NullPassengerException if any of the passengers contained within inPassengers is null
	 * @throws NegativeFloorException if any of the Person objects have a destination floor that's less than 1 or greater than the number of floors in the simulation
	 */
	@Override
	public synchronized boolean addPassengers(ArrayList<Person> inPassengers) throws NullPassengerException, NegativeFloorException
	{
		if(inPassengers.contains(null))
		{
			throw new NullPassengerException("The passenger object that is being added to the elevator is null!");
		}
		for(Person personToAdd : inPassengers)
		{
			if(this.passengerList.size() >= this.capacity)
			{
				//the elevator is full. don't attempt to move any more passengers into this elevator
				return false;
			}
			this.addPassenger(personToAdd);
		}
		return true;
	}

	/**
	 * Opens the elevator's doors and facilitates the movement of Person objects to and from this Elevator. The time it takes for this method to complete
	 * is determined by the time specified (in milliseconds) in the xmlInputs file
	 */
	@Override
	public synchronized void openDoors() 
	{
		this.bDoorsOpen = true;
		try
		{
			if(this.requestQueue.isEmpty())
			{
				//either the elevator is at the top or it's not at the bottom right now
				if(XmlParser.getTotalFloorNumber() == this.currentFloor + 1 || ( this.direction == Direction.UP && this.currentFloor != 0 ))
				{
					this.direction = Direction.DOWN;
				}
				else if(this.currentFloor == 0 || this.direction == Direction.DOWN)
				{
					this.direction = Direction.UP;
				}
			}
			ElevatorControlModule.getInstance().elevatorDoorsOpened(this, this.currentFloor);
			ArrayList<Person> peopleToRemove = new ArrayList<Person>();

			//find any passengers who are supposed to get off on this floor and remove them
			for( int i = 0; i < this.passengerList.size(); ++i)
			{
				Person currentPerson = this.passengerList.get(i);
				if(currentPerson.getDestinationFloor() == this.currentFloor + 1)
				{
					peopleToRemove.add(currentPerson);
				}
			}
			if(!peopleToRemove.isEmpty())
			{
				try
				{
					this.removePassengers(peopleToRemove);
				}
				catch (PassengerNotFoundException e)
				{
					e.printStackTrace();
				}
			}
                        DataLogger.logElevatorOpenDoors(this);
                        
			wait(doorSpeed);
		}
		catch (InterruptedException | NegativeFloorException | NegativeCapacityException | NegativeElevatorException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Closes the elevator's doors so nobody else can enter/leave this Elevator. The time it takes for this method to complete
	 * is determined by the time specified (in milliseconds) in the xmlInputs file
	 */
	@Override
	public synchronized void closeDoors() 
	{
		this.bDoorsOpen = false;

		//time to close doors, add a wait
		try
		{
                        DataLogger.logElevatorCloseDoors(this);
                        
			wait(doorSpeed);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Accessor for the elevator's current direction.
	 * @return The current direction of this elevator object.
	 */
	@Override
	public synchronized Direction getDirection()
	{
		return this.direction;
	}

	/**
	 * Remove a specific passenger from the elevator.
	 * @param inPassenger The passenger that will be removed from the elevator. inPassenger's id must be inside of the passengerList and should not be negative.
	 * @throws PassengerNotFoundException if the passenger passed isn't in the passenger list.
	 */
	@Override
	public synchronized void removePassenger(Person inPassenger) throws PassengerNotFoundException
	{            
		if(!passengerList.contains(inPassenger))
		{
			throw new PassengerNotFoundException("The passenger object that was meant to be removed is not present in the elevator!");
		}
		System.out.println(DataLogger.printTimeStamp() + ":     Person " + inPassenger.getID() + " is being removed from Elevator " + ( this.getElevatorId() + 1 ) + " at floor " + ( this.currentFloor + 1 ) );
		passengerList.remove(inPassenger);
	}

	/**
	 * Remove multiple passengers from the elevator.
	 * @param inPeople The passengers that will be removed from the elevator. Each value in inPeople must be inside of the passengerList and should not be negative.
	 * @throws NegativePassengerException throws an exception if the passenger value passed is negative.
	 * @throws InvalidIndexException throws an exception if the passenger passed isn't in the passenger list.
	 */
	@Override
	public synchronized void removePassengers(ArrayList <Person> inPeople) throws PassengerNotFoundException
	{
		if(!this.passengerList.containsAll(inPeople))
		{
			throw new PassengerNotFoundException("At least one of the passenger objects that were meant to be removed are not present in the elevator!");
		}
		for(Person personToRemove : inPeople)
		{
			this.removePassenger(personToRemove);
		}
	}

	/**
	 * Accessor for the capacity of this elevator object.
	 * @return the number of people that the elevator object can hold.
	 */
	@Override
	public synchronized int getCapacity() 
	{	
		return this.capacity;
	}

	/**
	 * Retrieves all passengers in the passengerList.
	 * @return the collection of passengers in the list as an ArrayList.
	 */
	@Override
	public synchronized ArrayList<Person> getPassengers() 
	{
		return this.passengerList;
	}

	/**
	 * Retrieves the elevator's id.
	 * @return returns the id that corresponds to the elevator that requested this method.
	 */
	@Override
	public synchronized int getElevatorId() 
	{
		return this.elevatorId;
	}

	/**
	 * Accessor which retrieves the current floor of this elevator
	 * @return the index of the current floor that the elevator is on.
	 */
	@Override
	public synchronized int getCurrentFloor()
	{
		return this.currentFloor + 1;
	}

	/**
	 * Retrieves the door speed time.
	 * @return returns the door speed time for the simulation
	 */
	@Override
	public synchronized int getDoorSpeed()
	{
		return this.doorSpeed;
	}

	/**
	 * Queries the state of the elevator's doors.
	 * @return returns true if the doors are open or false if the doors are closed.
	 */
	@Override
	public synchronized boolean isOpen() 
	{
		return bDoorsOpen;
	}

	/**
	 * Accessor for the running state of the elevator.
	 * @return returns true if the elevator is currently running and accepting floor requests or false if the system is inactive.
	 */
	@Override
	public synchronized boolean isRunning() 
	{
		return running;
	}

	/**
	 * Stops the elevator from running immediately without returning to its default floor. Once shut down, it cannot be started up again.
	 */
	@Override
	public synchronized void shutDown()
	{
		this.running = false;
	}

	/**
	 * Starts the elevator system process. Creates a timer to record the events of the elevator.
	 */
	@Override
	public void run()
	{
		try
		{
			long tStart = System.currentTimeMillis();

			System.out.println(DataLogger.printTimeStamp() + ":     Elevator " + ( getElevatorId() + 1 ) + " has started");
			running = true;
			while (running)
			{
				// if current floor is in request queue.      
				if (requestQueue.contains(this.currentFloor))
				{
					requestQueue.remove((Integer)this.currentFloor);
                                        // log elev stop on floor
                                        DataLogger.logElevatorArriveAtRequestedFloor(this, this.currentFloor);
					this.openDoors();
					this.closeDoors();
				}

				// if queue is empty  switch to idle.
				if (requestQueue.isEmpty())
				{
					System.out.println(DataLogger.printTimeStamp() + ":     Request queue for Elevator " + ( getElevatorId() + 1 ) + " is empty");

					this.direction = Direction.IDLE;
					tStart = System.currentTimeMillis();
				}

				synchronized(this)
				{
					switch (direction)
					{
					case IDLE:
						wait(10000);
						tStart = System.currentTimeMillis() - tStart;

						//only add a new request (and add an entry to the log) if the elevator is idle and isn't already at its default floor
						if (tStart >= 10001 && this.currentFloor != 0)
						{
							System.out.println(DataLogger.printTimeStamp() + ":     Elevator " + ( getElevatorId() + 1 ) + " has been idle for 10 seconds. Returning to floor 1");

							//since this is a hard coded value, there's no point in adding this exception to the outer catch block. just take care of it here
							try
							{
								addFloorToQueue(1);
							}
							catch(NegativeFloorException e)
							{
								e.printStackTrace();
							}
						}

						break;
					case UP:
						tStart = System.currentTimeMillis();
						wait(speed);
						if(this.currentFloor < this.maxFloors)
						{
							this.currentFloor++;
							System.out.println(DataLogger.printTimeStamp() + ":     Elevator " + ( getElevatorId() + 1 ) + " passing floor " + ( currentFloor + 1 ) );
							this.printRequestQueue();
						}
						else if(this.currentFloor == this.maxFloors)
						{
							this.direction = Direction.IDLE;
						}

						break;
					case DOWN:
						tStart = System.currentTimeMillis();
						wait(speed);
						if(this.currentFloor > this.minFloors)
						{
							this.currentFloor--;
							System.out.println(DataLogger.printTimeStamp() + ":     Elevator " + ( getElevatorId() + 1 ) + " passing floor " + ( currentFloor + 1 ) );
							this.printRequestQueue();
						}
						else if(this.currentFloor == this.minFloors)
						{
							this.direction = Direction.IDLE;
						}
					}
				}
			}
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the collection which holds the floor requests that this elevator object should respond to.
	 * Will not create a new collection object if one exists already
	 */
	private synchronized void initializeRequestQueue()
	{
		if(this.requestQueue == null)
		{
			this.requestQueue = new ArrayList<Integer>();
		}
	}

	/**
	 * Private helper method used to print the external representation (one-based indices) of this Elevator's request queue (stored as zero-based indices)
	 */
	private void printRequestQueue()
	{
		ArrayList<Integer> queueToPrint = new ArrayList<Integer>(requestQueue);
		for(int i = 0; i < queueToPrint.size(); ++i)
		{
			queueToPrint.set(i, queueToPrint.get(i) + 1);
		}
		System.out.println(DataLogger.printTimeStamp() + ":     Elevator " + ( this.getElevatorId() + 1 ) + "'s request queue: " + queueToPrint.toString());
	}

	/**
	 * Sets the identifier number assigned to this elevator.
	 * @param inId The unique identifier number. This number need not be in consecutive order compared to other elevators in the building, but it must be no larger than the 
	 * maximum number of elevators in the simulation environment.
	 */
	private synchronized void setId(int inId)
	{
		this.elevatorId = inId;
	}

	/**
	 * Sets the maximum number of floors that can be reached by this particular Elevator object. Useful for if the situation arises where an elevator should service a subset of the available
	 * floors in the simulation.
	 * private variable - only to be used to handle the maximum elevator floors.
	 * @param inMaxFloors The total number of floors the elevator can visit.
	 */
	private synchronized void setMaxFloors(int inMaxFloors)
	{
		this.maxFloors = inMaxFloors - 1;
	}

	/**
	 * Sets the minimum number of floors.
	 * private variable - only to be used to handle the minimum elevator floors.
	 * @param inMinFloors The lowest numbered floor the elevator can visit.
	 * @throws NegativeFloorException if inMinFloors is less than 0 (using ZERO-BASED indexing)
	 */
	private synchronized void setMinFloors(int inMinFloors) throws NegativeFloorException
	{
		if(inMinFloors < 1)
		{
			throw new NegativeFloorException("Attempting to set the minimum floor value of an elevator to a negative value! (inMinFloors: " + inMinFloors + ")");
		}
		this.minFloors = inMinFloors - 1;
	}

	/**
	 * Sets the initial direction for the elevator to IDLE.
	 */
	private synchronized void setInitialDirection()
	{
		this.direction = Direction.IDLE;
	}

	/**
	 * Sets the initial capacity for the elevator.
	 * @param inCap the total capacity limit the elevator can hold.
	 * @throws NegativeCapacityException if inCap is negative
	 */
	private synchronized void setCapacity(int inCap) throws NegativeCapacityException
	{
		if(inCap < 1)
		{
			throw new NegativeCapacityException("Attempted to set the capacity of the elevator to a negative number! (inCap: " + inCap + ")");
		}
		this.capacity = inCap;
	}

	/**
	 * Sets the speed of this elevator. Whenever an elevator moves between floors, this value will be used in the corresponding call to wait()
	 * @param newSpeed the time (in milliseconds) that it should take this elevator object to move between floors
	 */
	private synchronized void setSpeed(int newSpeed)
	{
		speed = newSpeed;
	}

	/**
	 * Sets the initial floor for the elevator.
	 * private variable - only to be used to give a default floor to an elevator.
	 * @param floor The default floor level that will be assigned to an elevator.
	 */	
	private synchronized void setDefaultFloor(int floor)
	{
		//TODO: this shouldn't be touching currentFloor. There should be a defaultFloor member that this method will modify instead
		this.currentFloor = floor;
	}

	/**
	 * Sets the speed of the elevator doors.
	 * @param newDoorSpeed the speed of the elevator doors.
	 */
	private synchronized void setDoorSpeed(int newDoorSpeed)
	{
		doorSpeed = newDoorSpeed;
	}	

	/**
	 * Creates the passengerList to be used with the elevators.
	 * private variable - only to be used create the passengerList.
	 */    
	private synchronized void createPassengerList()
	{
		passengerList = new ArrayList<Person>();
	}
}
