package pImpls;
import pInterfaces.ElevatorInterface;
import java.util.ArrayList;


    /**
     * Constructor which allows for properties such as the speed, capacity, and floor range of the elevator to be customized.
     * @param inCapacity The number of people this elevator can hold at a given time. When the elevator hits capacity, no more people may board the elevator.
     * @param inId The unique identifier number. This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
     * @throws NegativeCapacityException if any of the values passed into it are negative.
     */
public class Elevator implements ElevatorInterface
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
	
	

	public Elevator(int inId, int inCapacity)
	{
		setId(inId);
		setCapacity(inCapacity);
                setDefaultFloor(1);   // will make this customizable in later iterations
                
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
            
            switch (this.direction) 
            {
                case UP:
                   if (floorNum > this.currentFloor)
                    {
                        requestQueue.add(floorNum);
                        System.out.println("Request for floor " + floorNum + " was added to elevator: " + this.elevatorId);
                    
                    }
                    break;
                case DOWN:
                    if (floorNum < this.currentFloor)
                    {
                        requestQueue.add(floorNum);
                        System.out.println("Request for floor " + floorNum + " was added to elevator: " + this.elevatorId);
                    }
                    break;
                    
                case IDLE:
                    requestQueue.add(floorNum);
                    System.out.println("Request for floor " + floorNum + " was added to elevator: " + this.elevatorId);
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
		// TODO Auto-generated method stub
		this.passengerList.add(inPassenger);
                
	}


    /**
     * Add passengers to the elevator.
     * @param inPeople The number of people being removed from the elevator.This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
     * @throws NegativeCapacityException if any of the values passed into it are negative
     */
	@Override
	public void addPassengers(Person[] inPeople) 
	{
		
            
            //could also do weight/capacity check of each person here
            
		for (Person p : inPeople)
                    this.passengerList.add(p);
        }
	
	/**
	 * 
	 */
	@Override
	public void openDoors() 
	{
            
            //somethign to account for time here
           this.bDoorsOpen = true;
           
	}
    /**
     * Close the elevator doors.
     */
	@Override
	public void closeDoors() 
	{
		//time to close doors, add a wait
            
            this.bDoorsOpen = false;
	}

    /**
     * Remove a specific passenger from the elevator.
     * @param inPassenger The number of people being removed from the elevator.This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
     * @throws NegativeCapacityException if any of the values passed into it are negative
     */
	@Override
	public void removePassenger(Person inPassenger) 
	{
		// TODO Auto-generated method stub
            
             if  (passengerList.remove(inPassenger))
                    System.exit(1);
             
                    //succeeded 
             //Else need to generate error or exception
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
		// TODO Auto-generated method stub
		return 0;
	}


    /**
     * Retrieves the passenger. 
     */
	@Override
	public ArrayList<Person> getPassengers() 
	{
		// not sure how we wnat to return passengers
                // guess we want just to return the collection of People
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
	
	
    /**
     * Sets the identifier number assigned to this elevator.
     * @param inId The unique identifier number. This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
     */
	private void setId(int inId)
	{
		elevatorId = inId;
	}
	
	
    /**
     * Constructor which allows for properties such as the speed, capacity, and floor range of the elevator to be customized.
     * @param inCapacity The number of people this elevator can hold at a given time. When the elevator hits capacity, no more people may board the elevator.
     * @param inId The unique identifier number. This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
     * @throws NegativeCapacityException if any of the values passed into it are negative
     */
	private void setCapacity(int inCap)
	{
		capacity = inCap;
	}
        private void setDefaultFloor(int floor)
        {
            currentFloor = floor;
            
        }
        
        
}
