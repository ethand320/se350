package pInterfaces;
import java.util.ArrayList;

import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pImpls.Direction;
import pImpls.Person;

public interface FloorInterface
{
	/**
	 * Summons an elevator to this floor. Called whenever a Person object is added to this floor
	 * @param directionToGo The direction in which the Person object needs to travel
	 */
    public void summonElevator(Direction directionToGo);
    
    /**
     * Accessor which returns the ID number of this floor
     * @return the ID number of this floor
     */
    public int getId();
    
    /**
     * Removes one or more Person objects from this floor and places them into an elevator
     * @param elevatorToEnter the elevator object which the Person objects should enter
     * @param directionToGo the direction in which the elevator is going. Only the Person objects that are going in the same direction should
     * enter elevatorToEnter.
     */
    public void removeFromFloor(ElevatorInterface elevatorToEnter, Direction directionToGo);
    
    /**
     * Places a Person object in this floor. Once inside, they summon an elevator by calling summonElevator
     * @param inPerson the Person object being added to the floor. Must not be null.
     * @throws NullPassengerException if inPerson is null
     * @throws NegativeFloorException
     */
	public void addPersonToFloor(Person inPerson) throws NullPassengerException, NegativeFloorException;
	
	/**
	 * Accessor for all of the people waiting for an elevator on this floor
	 * @return The ArrayList containing all of the Person objects who are waiting for an elevator on this particular floor
	 */
	public ArrayList<Person> getWaitingPeople();
}