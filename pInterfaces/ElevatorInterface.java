package pInterfaces;
import pExceptions.NullPassengerException;
import pExceptions.PassengerNotFoundException;
import pImpls.Direction;
import pImpls.Person;

import java.util.ArrayList;

public interface ElevatorInterface
{
    public void addFloorToQueue(int floorNum);
    public void addPassenger(Person inPassenger) throws NullPassengerException;
	void addPassengers(ArrayList<Person> inPeople) throws NullPassengerException;
    public void openDoors();
    public void closeDoors();
    public void removePassenger(Person inPassenger) throws PassengerNotFoundException;
    public void removePassengers (ArrayList <Person> inPeople) throws PassengerNotFoundException;
    public int getCapacity();
    public ArrayList <Person> getPassengers();
    public int getElevatorId();
    public Direction getDirection();
    public void shutDown();
    public boolean isOpen();
    public boolean isRunning();
    public static final int DEFAULT_ELEVATOR_CAPACITY = 10;

}
