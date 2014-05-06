package pImpls;

import pInterfaces.ControlModuleInterface;

/**
 * Constructor which allows for the creation of an elevator system as well as the elevator calling system.
 * @param paramCapacity The number of people this elevator can hold at a given time. When the elevator hits capacity, no more people may board the elevator.
 * @param numFloors The number of floors that this elevator services. This value cannot be larger than the number of floors in the building that owns it, but it may be smaller
 * @param numElevators The number of floors that this elevator services. This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
 * @throws NegativeCapacityException if any of the values passed into it are negative
 */
public class ElevatorControlModule implements ControlModuleInterface 
{
	private ElevatorControlModuleImpl delegate;

	    /**
     * Handles the system that works with the calling of elevators to and from floors
     */
	@Override
	public void elevatorCallReceiver() {
		// TODO Auto-generated method stub
		delegate.elevatorCallReceiver();
	}
	
	
}
