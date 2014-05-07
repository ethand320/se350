package pImpls;
import pInterfaces.ElevatorInterface;
import pInterfaces.FloorInterface;
import java.util.ArrayList;


//fill this in
/**
 * The floor class represents a building floor containing the floor number, summon elevator buttons, and the modification of people on the floor.  
 *  requests to visit certain floors. Each Elevator object runs in its own thread and will continue to run until manually shut down.
 */
public class Floor implements FloorInterface
{
	private ArrayList<Person> goingUp;
	private ArrayList<Person> goingDown;
	private int floorNumber;
	
	    /** EDIT THIS FOR FLOOR
     * Constructor which allows for properties such as the speed, capacity, and floor range of the elevator to be customized.
     * @param paramCapacity The number of people this elevator can hold at a given time. When the elevator hits capacity, no more people may board the elevator.
     * @param numFloors The number of floors that this elevator services. This value cannot be larger than the number of floors in the building that owns it, but it may be smaller
     * @param idNumber The unique identifier number. This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
     * @throws NegativeCapacityException if any of the values passed into it are negative
     */
	public Floor(int inFloorId)
	{
		setFloorNumber(inFloorId);
	}
	
	
	@Override
	public void summonElevator(Direction directionToGo) 
	{
		// Makes call to elevator control module  passing the direction
            
	}


	@Override
	public int getId() 
	{
		return this.floorNumber;
                
	}

	@Override
	public void removeFromFloor(ElevatorInterface elevatorToEnter,
			Direction directionToGo) 
	{
		// TODO Auto-generated method stub
//		foreach person in goingup
//			remove from going up
//			elevatorToEnter.addPassenger(person);
		
	}
	
	private void setFloorNumber(int inNum)
	{
		floorNumber = inNum;
	}

}
