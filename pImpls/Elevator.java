package pImpls;
import pInterfaces.ElevatorInterface;
import java.util.ArrayList;

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
	}
	@Override
	public void addFloorToQueue(int floorNum) {
		// TODO Auto-generated method stub
		/*Logic
            if the floor is in the direction then allow it,
            otherwise throw exception or do nothing
            */
            
            
            
	}

	@Override
	public void addPassenger(Person inPassenger) {
		// TODO Auto-generated method stub
		this.passengerList.add(inPassenger);
                
	}

	@Override
	public void addPassengers(Person[] inPeople) {
		
            
            //could also do weight/capacity check of each person here
            
		for (Person p : inPeople)
                    this.passengerList.add(p);
                
                
        }

	@Override
	public void openDoors() {
            
            //somethign to account for time here
           this.bDoorsOpen = true;
           
           
		
	}

	@Override
	public void closeDoors() {
		//time to close doors, add a wait
            
            this.bDoorsOpen = false;
            
		
	}

	@Override
	public void removePassenger(Person inPassenger) {
		// TODO Auto-generated method stub
            
            
            
             if  (passengerList.remove(inPassenger))
                    System.exit(1);
             
                    //succeeded 
             //Else need to generate error or exception
                
             
            
		
	}

	@Override
        
	public void removePassengers(ArrayList <Person> inPeople) {
		// TODO Auto-generated method stub
		this.passengerList.removeAll(inPeople);
                
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Person[] getPassengers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getElevatorId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void setId(int inId)
	{
		elevatorId = inId;
	}
	
	private void setCapacity(int inCap)
	{
		capacity = inCap;
	}

}
