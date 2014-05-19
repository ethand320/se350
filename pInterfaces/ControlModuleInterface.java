package pInterfaces;

import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pImpls.Direction;
import pImpls.Person;

public interface ControlModuleInterface
{
	public void elevatorCallReceiver(int floorNumber, Direction directionRequest) throws NegativeFloorException;
	public ElevatorInterface getElevator(int index);
	public void shutDown();
	public void elevatorDoorsOpened(ElevatorInterface elevator, int floorNumber) throws NegativeFloorException;
	public void addPersonToFloor(Person inPerson, int floorNum) throws NullPassengerException, NegativeFloorException;
	public int getMaxFloors();
	public int getElevatorNum();
}