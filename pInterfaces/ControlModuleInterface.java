package pInterfaces;

import pExceptions.NullPassengerException;
import pImpls.Direction;
import pImpls.Person;

public interface ControlModuleInterface
{
	public void elevatorCallReceiver(int floorNumber, Direction directionRequest);
	public ElevatorInterface getElevator(int index);
	public void shutDown();
	public void elevatorDoorsOpened(ElevatorInterface elevator, int floorNumber);
	public void addPersonToFloor(Person inPerson, int floorNum) throws NullPassengerException;
	public int getMaxFloors();
	public int getElevatorNum();
}