package pInterfaces;
import pImpls.Person;

public interface ElevatorInterface
{
    public void addFloorToQueue(int floorNum);
    public void addPassenger(Person inPassenger);
    public void addPassengers(Person[] inPeople);
    public void openDoors();
    public void closeDoors();
    public void removePassenger(Person inPassenger);
    public void removePassengers(Person inPeople[]);
    public int getCapacity();
    public Person[] getPassengers();
    public int getElevatorId();
}
