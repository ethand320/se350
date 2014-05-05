package pImpls;
import pInterfaces.ElevatorInterface;
import java.util.ArrayList;

public class Elevator implements ElevatorInterface
{
	private int currentFloor;
	private Direction direction;
	private int speed;
	private int doorSpeed;
	private int[] requestQueue;
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
		
	}

	@Override
	public void addPassenger(Person inPassenger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPassengers(Person[] inPeople) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openDoors() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeDoors() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePassenger(Person inPassenger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePassengers(Person[] inPeople) {
		// TODO Auto-generated method stub
		
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
