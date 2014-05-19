package pInterfaces;
import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pImpls.Direction;
import pImpls.Person;

public interface FloorInterface
{
    public void summonElevator(Direction directionToGo) throws NegativeFloorException;
    public int getId();
    public void removeFromFloor(ElevatorInterface elevatorToEnter, Direction directionToGo);
	public void addPersonToFloor(Person inPerson) throws NullPassengerException, NegativeFloorException;
}