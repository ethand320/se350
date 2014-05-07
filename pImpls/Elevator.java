package pImpls;
import pInterfaces.ElevatorInterface;

import java.util.ArrayList;


    /**
     * Constructor which allows for properties such as the speed, capacity, and floor range of the elevator to be customized.
     * @param inCapacity The number of people this elevator can hold at a given time. When the elevator hits capacity, no more people may board the elevator.
     * @param inId The unique identifier number. This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
     * @throws NegativeCapacityException if any of the values passed into it are negative.
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
	
	

	public Elevator(int inId, int inCapacity, int inMaxFloors)
	{
		setId(inId);
		setCapacity(inCapacity);
		setMaxFloors(inMaxFloors);
		initializeRequestQueue();
		setInitialDirection();
        setDefaultFloor(1);   // will make this customizable in later iterations
        createPassengerList();
        elevatorThread.start();
	}

	/**
     * Simulates the act when a floor number is added to the queue.
     * @param floorNum The floor number that will be added to the queue.This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
     * @throws NegativeCapacityException if any of the values passed into it are negative
     */
	@Override
	public void addFloorToQueue(int floorNum) 
	{
		// TODO Auto-generated method stub
           /* Logic
            * if the floor is in the direction then allow it,
            * otherwise throw exception or do nothing
            */
			//if(floorNum < 1 || floorNum > this.maxFloors)
            
        switch (this.direction) 
        {
        case UP:
           if (floorNum > this.currentFloor)
            {
                requestQueue.add(floorNum);
                System.out.println("Request for floor " + floorNum + " was added to elevator: " + ( this.getElevatorId() + 1 ));
            
            }
           else
               System.out.println("Request for floor " + floorNum + " was rejected by the elevator: " + ( this.getElevatorId() + 1 ));
            break;
        case DOWN:
            if (floorNum < this.currentFloor)
            {
                requestQueue.add(floorNum);
                System.out.println("Request for floor " + floorNum + " was added to elevator: " + ( this.getElevatorId() + 1 ));
            }
            else
                System.out.println("Request for floor " + floorNum + " was rejected by the elevator: " + ( this.getElevatorId() + 1 ));

            break;
            
        case IDLE:
            requestQueue.add(floorNum);
            System.out.println("Request for floor " + floorNum + " was added to elevator: " + ( this.getElevatorId() + 1 ));
            if ( currentFloor < floorNum)
            	direction = Direction.UP;
            else
            	direction = Direction.DOWN;
            
            break;
        }
	}

    /**
     * Add a passenger to the elevator.
     * @param inPassenger The number of people being removed from the elevator.This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
     * @throws NegativeCapacityException if any of the values passed into it are negative
     */
	@Override
	public void addPassenger(Person inPassenger) 
	{
		this.passengerList.add(inPassenger);
		this.addFloorToQueue(inPassenger.getDestinationFloor());
	}


    /**
     * Add passengers to the elevator.
     * @param inPeople The number of people being removed from the elevator.This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
     * @throws NegativeCapacityException if any of the values passed into it are negative
     */
	@Override
	public void addPassengers(Person[] inPeople) 
	{
		for (Person p : inPeople)
		{
			this.passengerList.add(p);
		}
    }
	
	/**
	 * 
	 */
	@Override
	public synchronized void openDoors() 
	{
		this.bDoorsOpen = true;
		 try
		 {
			 ElevatorControlModule.getInstance().elevatorDoorsOpened(this, this.currentFloor);
	        wait(500);
	     }
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		 
           
           
	}
    /**
     * Close the elevator doors.
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

	@Override
	public Direction getDirection()
	{
		return this.direction;
	}
	
    /**
     * Remove a specific passenger from the elevator.
     * @param inPassenger The number of people being removed from the elevator.This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
     * @throws NegativeCapacityException if any of the values passed into it are negative
     */
	@Override
	public synchronized void removePassenger(Person inPassenger) 
	{            
	     if  (passengerList.contains(inPassenger))
	     {
	    	 passengerList.remove(inPassenger);
	     }
	     else
	     {
	    	 //TODO: add exception handling here
	     }
	}
	
    /**
     * Remove a specified number of passengers from the elevator.
     * @param inPeople The number of people being removed from the elevator.This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
     * @throws NegativeCapacityException if any of the values passed into it are negative
     */
	@Override
	public void removePassengers(ArrayList <Person> inPeople) 
	{
		// TODO Auto-generated method stub
		this.passengerList.removeAll(inPeople);
	}

    /**
     * Retrieves the capacity of the elevator.
     */
	@Override
	public int getCapacity() 
	{
		return this.capacity;
	}

    /**
     * Retrieves the passenger. 
     */
	@Override
	public ArrayList<Person> getPassengers() 
	{
		return this.passengerList;
	}
	
    /**
     * Retrieves the elevator. 
     */
	@Override
	public int getElevatorId() 
	{
		return this.elevatorId;
	}
	
	@Override
	public void shutDown()
	{
		this.running = false;
	}
	
	@Override
	public void run()
	{
		try
		{
			long tStart = 0;
			
			System.out.println("Elevator " + ( getElevatorId() + 1 ) + " has started");
		    running = true;
	        while (running)
	        {
	        	
	        	// if current floor is in request queue
	        	
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
					switch (direction)
					{
					case IDLE:
						wait(10000);
						tStart = System.currentTimeMillis() - tStart;
				
						//for some reason, tStart becomes 10001 in the worst case, so instead of checking whether we waited 10 seconds flat, we'll check to see if we
						//waited 10002 milliseconds
						if (tStart >= 10002 )
						{
							System.out.println("Elevator" + ( getElevatorId() + 1 ) + "has been idle for 10 seconds. Returning to floor 1");
							addFloorToQueue(1);
						}
						
						break;
					case UP:
						tStart = 0;
						wait(500);
						this.currentFloor++;
			            System.out.println("Elevator " + ( getElevatorId() + 1 ) + " passing floor " + currentFloor);
			
						break;
					case DOWN:
						tStart = 0;
						wait(500);
						this.currentFloor--;
			            System.out.println("Elevator " + ( getElevatorId() + 1 ) + " passing floor " + currentFloor);
					}
				}
	        }
		}
		catch(InterruptedException e)
        {
        	e.printStackTrace();
        }
	}
	
	private synchronized void initializeRequestQueue()
	{
		this.requestQueue = new ArrayList<Integer>();
	}
	
    /**
     * Sets the identifier number assigned to this elevator.
     * @param inId The unique identifier number. This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
     */
	private void setId(int inId)
	{
		this.elevatorId = inId;
	}
	
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
    
    private void createPassengerList()
    {
		passengerList = new ArrayList<Person>();
	}
}
