package pInterfaces;
import pExceptions.NullPassengerException;
import pExceptions.PassengerNotFoundException;
import pImpls.Direction;
import pImpls.Person;

import java.util.ArrayList;

public interface ElevatorInterface
{
	/**
	 * Adds a floor to the elevator's request queue, indicating that the elevator should stop at the indicated floor.
	 * @param floorNum the floor number to stop at.
	 */
    public void addFloorToQueue(int floorNum);
    
    /**
     * Adds a Person object to the elevator. Once inside, their destination floor is added to the elevator's request queue.
     * @param inPassenger the Person object that is entering the elevator.
     * @throws NullPassengerException if inPassenger is null.
     */
    public void addPassenger(Person inPassenger) throws NullPassengerException;
    
    /**
     * Adds a group of Person objects to the elevator. Once they are inside the elevator, their destination floors are added to the elevator's request queue.
     * @param inPeople the group of Person objects that are entering the elevator
     * @throws NullPassengerException if any of the Person objects in inPeople are null
     */
	void addPassengers(ArrayList<Person> inPeople) throws NullPassengerException;
	
	/**
	 * Open the doors of the elevator so Person objects may enter or leave this elevator.
	 */
    public void openDoors();
    
    /**
     * Close the doors of the elevator so no more Person objects may enter or leave.
     */
    public void closeDoors();
    
    /**
     * Remove a Person from this elevator. Should be called once a Person has reached their destination floor and the doors have been opened.
     * @param inPassenger the Person object who has reached their destination and should leave.
     * @throws PassengerNotFoundException if inPassenger is not located inside this elevator.
     */
    public void removePassenger(Person inPassenger) throws PassengerNotFoundException;
    
    /**
     * Removes multiple Person objects from this elevator. Should be called once multiple Persons have reached their destination floor and the doors
     * have been opened.
     * @param inPeople The group of Person objects who have all reached the same destination floor and should leave.
     * @throws PassengerNotFoundException if any of the passengers are not located inside this elevator.
     */
    public void removePassengers (ArrayList <Person> inPeople) throws PassengerNotFoundException;
    
    /**
     * Accessor for the maximum number of Person objects that may be inside this elevator at any time.
     * @return the number of Person objects that may be inside this elevator at any time.
     */
    public int getCapacity();
    
    /**
     * Accessor for the container of Person objects that are currently inside this elevator. All of the destination floors of the people
     * in this container should be in the elevator's requests queue.
     * @return the Person objects that are currently located inside this object.
     */
    public ArrayList <Person> getPassengers();
    
    /**
     * Accessor for this elevator's ID number
     * @return the ID number of this elevator
     */
    public int getElevatorId();
    
    /**
     * Accessor for the current direction of this object.
     * @return a value from the Direction enum that indicates this object's current direction.
     */
    public Direction getDirection();
    
    /**
     * Shuts down this elevator so it may no longer move between floors or accept floor requests.
     */
    public void shutDown();
    
    /**
     * Accessor which returns a boolean indicating whether this object's doors are open. If they are, then Person objects may be moved to and from
     * this object.
     * @return true if the doors are open and Person objects are able to be moved to and from this object, otherwise false.
     */
    public boolean isOpen();
    
    /**
     * Accessor which returns a boolean indicating whether this implementing object is currently running and accepting floor requests
     * @return true if this object is currently handling floor requests, otherwise false.
     */
    public boolean isRunning();
    
    /**
     * Constant indicating the default amount of Person objects that may fit inside this object.
     */
    public static final int DEFAULT_ELEVATOR_CAPACITY = 10;
}
