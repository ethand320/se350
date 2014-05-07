package pInterfaces;
import pImpls.Person;
import java.util.ArrayList;

public interface ElevatorInterface
{
    public void addFloorToQueue(int floorNum);
    public void addPassenger(Person inPassenger);
    public void addPassengers(Person[] inPeople);
    public void openDoors();
    public void closeDoors();
    public void removePassenger(Person inPassenger);
    public void removePassengers (ArrayList <Person> inPeople);
    public int getCapacity();
    public ArrayList <Person> getPassengers();
    public int getElevatorId();
	public void shutDown();
    public static final int DEFAULT_ELEVATOR_CAPACITY = 10;
}
