package pInterfaces;

import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pImpls.Direction;
import pImpls.Person;

/**
 * The ControlModuleInterface dictates the actions required by any object that would serve as an elevator controller. Since the elevator controller
 * knows about the entire state of the simulation, the methods declared here help share that information with other objects/classes in the simulation
 * as necessary.
 */
public interface ControlModuleInterface
{
	/**
	 * This method should be called by a FloorInterface object whenever a Person object is placed inside the FloorInterface and needs to call an
	 * elevator.
	 * @param floorNumber The floor number that is issuing the call. This value must be between 1 and the number of floors inside the simulation, inclusive.
	 * @param directionRequest The direction that the Person making the call from the floor needs to go. Must be either UP or DOWN.
	 * @throws NegativeFloorException if floorNumber is outside the required bounds.
	 */
	public void elevatorCallReceiver(int floorNumber, Direction directionRequest) throws NegativeFloorException;

	/**
	 * Accessor for retrieving an elevator at the specified index.
	 * @param index the elevator number to retrieve. Must be between 1 and the maximum number of elevators in the simulation, inclusive.
	 * @return the ElevatorInterface object at the position specified by index.
	 */
	public ElevatorInterface getElevator(int index);

	/**
	 * Issues the command to all of the elevators that an implementation of this interface owns to shut down immediately.
	 */
	public void shutDown();

	/**
	 * 
	 * @param elevator the ElevatorInterface object that has just opened its doors, which will trigger Person movement between the floor and
	 * elevator.
	 * @param floorNumber the number of the floor that the ElevatorInterface object is currently located while its doors are opening. Used to facilitate
	 * the movement of Person objects.
	 * @throws NegativeFloorException if floorNumber is outside the bounds of the simulation environment.
	 */
	public void elevatorDoorsOpened(ElevatorInterface elevator, int floorNumber) throws NegativeFloorException;

	/**
	 * Helper function which places a Person object on a specified floor. Once inside the floor in question, the Person object will force the floor
	 * to summon an elevator
	 * @param inPerson the Person object to place inside the floor. Must not be null
	 * @param floorNum the floor number to place the Person object into. Must be between 1 and the maximum number of floors in the simulation, inclusive.
	 * @throws NullPassengerException if inPerson is null
	 * @throws NegativeFloorException if floorNum is outside the bounds of the simulation.
	 */
	public void addPersonToFloor(Person inPerson, int floorNum) throws NullPassengerException, NegativeFloorException;

	/**
	 * Accessor which returns the number of floors that the implementing object is responsible for
	 * @return the maximum number of floors in the simulation
	 */
	public int getMaxFloors();

	/**
	 * Accessor which returns the number of elevators that the implementing object is responsible for
	 * @return the number of elevators owned by this implementing object
	 */
	public int getElevatorNum();
}