package pImpls;
import pInterfaces.ElevatorInterface;
import pInterfaces.FloorInterface;
import java.util.ArrayList;

public class Floor implements FloorInterface
{
	private ArrayList<Person> goingUp;
	private ArrayList<Person> goingDown;
	private int floorNumber;
	
	public Floor(int inFloorId)
	{
		setFloorNumber(inFloorId);
	}
	@Override
	public void summonElevator(Direction directionToGo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeFromFloor(ElevatorInterface elevatorToEnter,
			Direction directionToGo) {
		// TODO Auto-generated method stub
		
	}
	
	private void setFloorNumber(int inNum)
	{
		floorNumber = inNum;
	}

}
