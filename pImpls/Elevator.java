package pImpls;
import pInterfaces.ElevatorInterface;
import pExceptions.*;

import java.util.ArrayList;


/**
 *  Elevator class that handles properties such as the speed, capacity, and floor range of the elevator to be customized.
 */
public class Elevator implements ElevatorInterface, Runnable
{
	private int currentFloor;
	private Direction direction;
	private int speed;
	private int doorSpeed;
	private ArrayList <Integer> requestQueue;
	private ArrayList<Person> passengerList;
	private boolean bDoorsOpen;
	private int capacity;
	private int elevatorId;
	private Thread elevatorThread = new Thread(this);
	private boolean running = true;
	private int maxFloors;
	private int minFloors;
	
	
   /**
    * Constructor for Elevator. Takes in the elevator Id, capacity, and max/min floors.
    * @param inId The elevators id. This number cannot be negative and must be independent of another elevators Id.
    * @param inCapacity The total number of passengers the elevator can hold. This number cannot be negative and must not be less than {@ Value}
    * @param inMaxFloors The maximum amount of floors the elevator can access.
    * @param inMinFloors The minimum amount of floors the elevator can access.
 * @throws NegativeCapacityException 
    */
	public Elevator(int inId, int inCapacity, int inMaxFloors, int inMinFloors) throws NegativeCapacityException
	{
		setId(inId);
		setCapacity(inCapacity);
		setMaxFloors(inMaxFloors);
		setMinFloors(inMinFloors);
		initializeRequestQueue();
		setInitialDirection();
    	setDefaultFloor(1);
    	createPassengerList();
    	elevatorThread.start();
	}
	
   /**
    * Simulates the act of calling an elevator.  Only accepted if floor is on the elevator's path
    * @param floorNum The floor number that will be added to the queue. It need not be in consecutive order compared to other elevators in the building.
    */
	@Override
	public void addFloorToQueue(int floorNum) 
	{
		if(this.currentFloor == this.maxFloors)
		{
			this.direction = Direction.DOWN;
		}
		else if(this.currentFloor == this.minFloors)
		{
			this.direction = Direction.UP;
		}
		
        switch (this.direction) 
        {
        case UP:
           if (floorNum > this.currentFloor)
            {
	           synchronized(this)
	           {
	           	requestQueue.add(floorNum);
	           	notifyAll();
	           }
	           System.out.println("Request for floor " + ( floorNum + 1) + " was added to elevator: " + ( this.getElevatorId() + 1 ));
            }
           else
           {
        	   System.out.println("Request for floor " + ( floorNum + 1) + " was rejected by the elevator: " + ( this.getElevatorId() + 1 ));
           }
           break;
        case DOWN:
            if (floorNum < this.currentFloor)
            {
            	synchronized(this)
            	{
            		requestQueue.add(floorNum);
            		notifyAll();
            	}
                System.out.println("Request for floor " + ( floorNum + 1) + " was added to elevator: " + ( this.getElevatorId() + 1 ));
            }
            else
            {
            	System.out.println("Request for floor " + ( floorNum + 1) + " was rejected by the elevator: " + ( this.getElevatorId() + 1 ));
            }
            break;
			
            // Applies the direction to a switch in order to determine which elevator can or reject the call.
        case IDLE:
        	synchronized(this)
        	{
        		requestQueue.add(floorNum);
        		notifyAll();
        	}
            System.out.println("Request for floor " + ( floorNum + 1) + " was added to elevator: " + ( this.getElevatorId() + 1 ));
            if ( currentFloor < floorNum)
            {
            	direction = Direction.UP;
            }
            else
            {
            	//compare to see if the floorNumber is greater than the current floor
            	//If true then assign the elevator to the request.
            	direction = Direction.DOWN;
            }
            break;
        }
	}

   /**
    * Add a passenger to the elevator.  Adds person object.
    * @param inPassenger The number of people being removed from the elevator.This number cannot be negative, and should be added to the passenger list.
    * @throws NullPassengerException if inPassenger is set to null
    */
	@Override
	public synchronized void addPassenger(Person inPassenger) throws NullPassengerException
	{
		if(inPassenger == null)
		{
			throw new NullPassengerException("The passenger object that is being added to the elevator is null!");
		}
		this.passengerList.add(inPassenger);
		this.addFloorToQueue(inPassenger.getDestinationFloor());
	}


   /**
 	* Add multiple passengers to the elevator.
    * @param inPassengers The number of people being added to the elevator.This number cannot be negative, and all passengers should be added to the list.
    * @throws NullPassengerException if any of the passengers contained within inPassengers is null
    */
	@Override
	public synchronized void addPassengers(ArrayList<Person> inPassengers) throws NullPassengerException
	{
		for (Person p : inPassengers)
		{
			if(p == null)
			{
				throw new NullPassengerException("The passenger object that is beinga added to the elevator is null!");
			}
			this.passengerList.add(p);
		}
    }
	
	/**
	 * Allows for the doors of the elevator to be opened. A 500 ms wait time is added to allow for the removal and addition of passenger(s)
	 * @throws InterruptedException if the elevator doors have any interence, then print a stackTrace.
	 */
	@Override
	public synchronized void openDoors() 
	{
		this.bDoorsOpen = true;
		 try
		 {
			 ElevatorControlModule.getInstance().elevatorDoorsOpened(this, this.currentFloor);
			 
			 //find any passengers who are supposed to get off on this floor and remove them
			 for( int i = 0; i < this.passengerList.size(); ++i)
			 {
				 if(this.passengerList.get(i).getDestinationFloor() == this.currentFloor)
				 {
					 this.passengerList.remove(i);
				 }
			 }
			 wait(500);
	     	}
		 catch (InterruptedException e)
		 {
			e.printStackTrace();
		 }
	}

	/**
	 * Allows for the doors of the elevator to be close. A 500 ms wait time is added to allow for the removal and addition of passenger(s)
	 */
	@Override
	public synchronized void closeDoors() 
	{
		this.bDoorsOpen = false;
		
		//time to close doors, add a wait
        	try
        	{
        		wait(500);
        	}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

   /**
    * retrieves the direction of the requested elevator and returns it.
    * @return returns the current direction of the requested elevator.
    */
	@Override
	public Direction getDirection()
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
	     if  (!passengerList.contains(inPassenger))
	     {
	    	 throw new PassengerNotFoundException("The passenger object that was meant to be removed is not present in the elevator!");
	     }
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
		this.passengerList.removeAll(inPeople);			
	}

   /**
    * Retrieves the capacity of the elevator.
    * @return the number of people that the elevator object can hold.
    */
	@Override
	public int getCapacity() 
	{	
		return this.capacity;
	}

   /**
    * Retrieves all passengers in the passengerList.
    * @return the collection of passengers in the list as an ArrayList.
    */
	@Override
	public ArrayList<Person> getPassengers() 
	{
		return this.passengerList;
	}
	
   /**
    * Retrieves the elevator's id.
    * @return returns the id that corresponds to the elevator that requested this method.
    */
	@Override
	public int getElevatorId() 
	{
		return this.elevatorId;
	}
	
   /**
	* Queries the state of the elevator's doors.
    * @return returns true if the doors are open or false if the doors are closed.
    */
	@Override
	public boolean isOpen() 
	{
		return bDoorsOpen;
	}
    
   /**
    * Queries the running state of the elevator.
    * @return returns true if the elevator is currently running and accepting floor requests or false if the system is inactive.
    */
	@Override
	public boolean isRunning() 
	{
    	return running;
	}
	
   /**
    * Stops the elevator from running immediately without returning to its default floor. Once shut down, it cannot be started up again.
    */
	@Override
	public void shutDown()
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
			
			System.out.println("Elevator " + ( getElevatorId() + 1 ) + " has started");
		    running = true;
	        while (running)
	        {
	        	
	        	// if current floor is in request queue.
	        	
	        	if (requestQueue.contains(this.currentFloor))
	        	{
	        	  this.openDoors();
	        	  this.closeDoors();
	        	  requestQueue.remove((Integer)this.currentFloor);
	        	}
	        	
	        	// if queue is empty  switch to idle.
	        	if (requestQueue.isEmpty())
	        	{
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
						if (tStart >= 10001 && this.currentFloor != 1)
						{
							System.out.println("Elevator " + ( getElevatorId() + 1 ) + " has been idle for 10 seconds. Returning to floor 1");
							addFloorToQueue(1);
						}
						
						break;
					case UP:
						tStart = System.currentTimeMillis();
						wait(500);
						if(this.currentFloor < this.maxFloors)
						{
							this.currentFloor++;
				            System.out.println("Elevator " + ( getElevatorId() + 1 ) + " passing floor " + ( currentFloor + 1 ) );
						}
						else if(this.currentFloor == this.maxFloors)
						{
							this.direction = Direction.IDLE;
						}
			
						break;
					case DOWN:
						tStart = System.currentTimeMillis();
						wait(500);
						if(this.currentFloor > this.minFloors)
						{
							this.currentFloor--;
				            System.out.println("Elevator " + ( getElevatorId() + 1 ) + " passing floor " + ( currentFloor + 1 ) );
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
    * Sets the identifier number assigned to this elevator.
    * private variable - only to be used to give an id to an elevator.
    * @param inId The unique identifier number. This number need not be in consecutive order compared to other elevators in the building, but it must be no larger than the 
    * maximum number of elevators in the simulation environment.
    */
	private void setId(int inId)
	{
		this.elevatorId = inId;
	}
	
   /**
    * Sets the maximum number of floors.
    * private variable - only to be used to handle the maximum elevator floors.
    * @param inMaxFloors The total number of floors the elevator can visit.
    */
	private void setMaxFloors(int inMaxFloors)
	{
		this.maxFloors = inMaxFloors;
	}

   /**
    * Sets the minimum number of floors.
    * private variable - only to be used to handle the minimum elevator floors.
    * @param inMinFloors The lowest numbered floor the elevator can visit.
    */
	private void setMinFloors(int inMinFloors)
	{
		this.minFloors = inMinFloors;
	}
	
   /**
    * Sets the initial direction for the elevator to IDLE.
    * private variable - only to be used to give a default direction to an elevator.
    */
	private void setInitialDirection()
	{
		this.direction = Direction.IDLE;
	}
	
   /**
    * Sets the initial capacity for the elevator.
    * private variable - only to be used to give a default direction to an elevator.
    * @param inCap the total capacity limit the elevator can hold.
    * @throws NegativeCapacityException if inCap is negative
    */
	private void setCapacity(int inCap) throws NegativeCapacityException
	{
		if(inCap < 1)
		{
			throw new NegativeCapacityException("Attempted to set the capacity of the elevator to a negative number!");
		}
		this.capacity = inCap;
	}

   /**
    * Sets the initial floor for the elevator.
    * private variable - only to be used to give a default floor to an elevator.
    * @param floor The default floor level that will be assigned to an elevator.
    */	
	private void setDefaultFloor(int floor)
    {
    	this.currentFloor = floor;
	}
    	
   /**
    * Creates the passengerList to be used with the elevators.
    * private variable - only to be used create the passengerList.
    */    
	private void createPassengerList()
	{
		passengerList = new ArrayList<Person>();
	}
}
