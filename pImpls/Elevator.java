package pImpls;
import pInterfaces.ElevatorInterface;

import java.util.ArrayList;


/**
 * Constructor which allows for properties such as the speed, capacity, and floor range of the elevator to be customized.
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
	private boolean running;
	private int maxFloors;
	
	
       /**
        * Simulates the creation of an elevator with the given parameters. Takes in the elevator Id, capacity, and max floors.
        * @param inId The elevators id. This number cannot be negative and must be independent of another elevators Id.
        * @param inCapacity The total number of passengers the elevator can hold. This number cannot be negative and must not be less than {@ Value}
        * @param inMaxFloors The maximum amount of floors the elevator can access. This number must not be negative and should be less than or equal to the total floors in the building le The floor number that will be added to the queue.This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
        * @throws NegativeCapacityException if any of the values passed into it are negative.
        */
	public Elevator(int inId, int inCapacity, int inMaxFloors)
	{
	setId(inId);
	setCapacity(inCapacity);
	setMaxFloors(inMaxFloors);
	initializeRequestQueue();
	setInitialDirection();
        setDefaultFloor(1);   // will make this customizable in later iterations
        elevatorThread.start();
	}
	
       /**
        * Simulates the act when a floor number is added to the queue.
        * @param floorNum The floor number that will be added to the queue.This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
        * @throws NegativeFloorException if any of the values passed into it are negative
        */
	@Override
	public void addFloorToQueue(int floorNum) 
	{
           /* Logic
            * if the floor is in the direction then allow it,
            * otherwise throw exception or do nothing
            */
			//if(floorNum < 1 || floorNum > this.maxFloors)
			
            // Applies the direction to a swtich in order to determine which elevator can or reject the call.
            switch (this.direction) 
            {
            	//compare to see if the floorNumber is greater than the current floor
            	//If true then assign the elevator to the request.
                case UP:
                   if (floorNum > this.currentFloor)
                    {
                        requestQueue.add(floorNum);
                        System.out.println("Request for floor " + floorNum + " was added to elevator: " + this.elevatorId);
                    
                    }
                    //if the floor number is less than the current floor then the request is rejected due to the direction interference.
                   else
                       System.out.println("Request for floor " + floorNum + " was rejected by the elevator: " + this.elevatorId);
                    break;
                    //compare to see if the floorNumber is less than the current floor
                    // if true then assign the request to the elevator
                case DOWN:
                    if (floorNum < this.currentFloor)
                    {
                        requestQueue.add(floorNum);
                        System.out.println("Request for floor " + floorNum + " was added to elevator: " + this.elevatorId);
                    }
                    // if the floor number is greater than the current floor then the request is rejected due to the direcetion interference.
                    else
                        System.out.println("Request for floor " + floorNum + " was rejected by the elevator: " + this.elevatorId);
                    break;
                    
                    //if the direction is in IDLE then the request is automatically accepted by the elevator
                    // a Direction is then assigned to the elevator based upon the currentFloor and the requestd floorNum
                case IDLE:
                    requestQueue.add(floorNum);
                    System.out.println("Request for floor " + floorNum + " was added to elevator: " + this.elevatorId);
                    if ( currentFloor < floorNum)
                    	direction = Direction.UP;
                    else
                    	direction = Direction.DOWN;
                    break;
            }
			//notifyAll();
	}

       /**
        * Add a passenger to the elevator.
        * @param inPassenger The number of people being removed from the elevator.This number cannot be negative, and should be added to the pasenger list.
        * @throws NegativePassengerException if any of the values passed into it are negative
        */
	@Override
	public void addPassenger(Person inPassenger) 
	{
		this.passengerList.add(inPassenger);
		this.addFloorToQueue(inPassenger.getDestinationFloor());
	}


       /**
     	* Add multiple pasengers to the elevator.
        * @param inPeople The number of people being added to the elevator.This number cannot be negative, and all passengers should be added to the list.
        * @throws NegativePassengersException if any of the values passed into it are negative
        */
	@Override
	public void addPassengers(Person[] inPeople) 
	{
            //could also do weight/capacity check of each person here
		for (Person p : inPeople)
        	   this.passengerList.add(p);
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
			 wait(500);
	         }
		 catch (InterruptedException e)
		 {
			e.printStackTrace();
		 }
	}

	/**
	 * Allows for the doors of the elevator to be close. A 500 ms wait time is added to allow for the removal and addition of passenger(s)
	 * @throws InterruptedException if the elevator doors have any interence, then print a stackTrace.
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
        * Remove a specific passenger from the elevator.
        * @param inPassenger The passenger that will be removed from the elevator. inPassenger's id must be inside of the passengerList and should not be negative.
        * @throws NegativePassengerException throws an exception if the passenger value passed is negative.
        * @throws InvalidIndexException throws an exception if the passenger passed isn't in the passenger list.
        */
	@Override
	public synchronized void removePassenger(Person inPassenger) 
	{
		// TODO Auto-generated method stub
            
             if  (passengerList.remove(inPassenger))
                    System.exit(1);
             
                    //succeeded 
             //Else need to generate error or exception
	}
	
	//private variable that initalizes the requestQueue.
	private synchronized void initializeRequestQueue()
	{
		this.requestQueue = new ArrayList<Integer>();
	}

       /**
        * Remove multiple passenger from the elevator.
        * @param inPeople The passengers that will be removed from the elevator. Each value in inPeople must be inside of the passengerList and should not be negative.
        * @throws NegativePassengerException throws an exception if the passenger value passed is negative.
        * @throws InvalidIndexException throws an exception if the passenger passed isn't in the passenger list.
        */
	@Override
	public void removePassengers(ArrayList <Person> inPeople) 
	{
		// TODO Auto-generated method stub
		this.passengerList.removeAll(inPeople);
	}

       /**
        * Retrieves the capacity of the elevator.
        * @return initaites the capacity to be 0 for each call.
        */
	@Override
	public int getCapacity() 
	{	//shouldn't this return the capacity of the elevator instance????
		// TODO Auto-generated method stub
		return 0;
	}


       /**
        * Retrieves all passengers in the passengerList.
        * @return the collection of passengers in the list
        */
	@Override
	public ArrayList<Person> getPassengers() 
	{
		// not sure how we wnat to return passengers
                // guess we want just to return the collection of People
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
	* Changes the running condition to false to stop all elevators.
  	*/
	public void shutDown()
	{
		this.running = false;
	}
	
       /**
        * Sets the identifier number assigned to this elevator.
        * private variable - only to be used to give an id to an elevator
        * @param inId The unique identifier number. This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
        * @throw NegativeIdException throws an exception of the id being assigned is negative
        * @throw IdenticalIdException throws an exception if the id being assigned already exists
        */
	private void setId(int inId)
	{
		this.elevatorId = inId;
	}
	
		///////////////FIX BELOW THIS LINE
       /**
        * Sets the maximum number of floors.
        * private variable - only to be used to give an id to an elevator
        * @param inId The unique identifier number. This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
        * @throw NegativeIdException throws an exception of the id being assigned is negative
        * @throw IdenticalIdException throws an exception if the id being assigned already exists
        */
	
	private void setMaxFloors(int inMaxFloors)
	{
		this.maxFloors = inMaxFloors;
	}
	
	private void setInitialDirection()
	{
		this.direction = Direction.IDLE;
	}
	
    /**
     * Constructor which allows for properties such as the speed, capacity, and floor range of the elevator to be customized.
     * @param inCapacity The number of people this elevator can hold at a given time. When the elevator hits capacity, no more people may board the elevator.
     * @param inId The unique identifier number. This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
     * @throws NegativeCapacityException if any of the values passed into it are negative
     */
	private void setCapacity(int inCap)
	{
		this.capacity = inCap;
	}
        private void setDefaultFloor(int floor)
        {
            this.currentFloor = floor;
            
        }

		public double idleTimer;
		@Override
		public void run()
		{
			try
			{
				long tStart = 0;
				
				  System.out.println("Elevator " + getElevatorId() + " has started");
			       running = true;
			        while (running)
			        {
			        	
			        	// if currentlfor is in request queue
			        	
			        	if (requestQueue.contains(this.currentFloor))
			        	{
			        	  this.openDoors();
			        	  this.closeDoors();
			        	  requestQueue.remove((Integer)this.currentFloor);
		  
			        	}
			        	
			        	// if queue is empty  switch to idle
			        	if (requestQueue.isEmpty())
			        	{
			        		this.direction = Direction.IDLE;
			        		tStart = System.currentTimeMillis();
			        		
			        	}
			        	
			            synchronized(this)
			            {
			            		//direction up
			            		switch (direction)
			            		{
			            		case IDLE:
			            			wait(10000);
			            			idleTimer = System.currentTimeMillis() - tStart;
			            	
			            			if (idleTimer >= 10000 )
			            			{
			            				addFloorToQueue(1);
			            			}
			            			
			            			break;
			            		case UP:
			            			idleTimer = 0;
			            			
			            		wait(500);
			            			this.currentFloor++;
			                        System.out.println("Elevator " + getElevatorId() + " passing floor " + currentFloor);

			            			break;
			            		case DOWN:
			            			wait(500);
			            			this.currentFloor--;
			                        System.out.println("Elevator " + getElevatorId() + " passing floor " + currentFloor);

			            		}
			            }
			       }
			}     
			catch(InterruptedException e)
	        {
	        	e.printStackTrace();
	        }
	
		}
        
        
}
