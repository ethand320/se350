package pFactories;
import pExceptions.NegativeCapacityException;
import pExceptions.NegativeFloorException;
import pImpls.Elevator;
import pInterfaces.ElevatorInterface;

/**
 * The ElevatorFactory class is aptly named, serving as a factory for elevators. All of its methods
 * are public and static to facilitate this behavior. It is impossible to instantiate a Factory object.
 */
public class ElevatorFactory
{    
	/**
	 * This method creates and returns a new elevator object based upon the parameters.
	 * @param capacity A positive integer representing the number total number of people the elevator can hold.
	 * @param elevatorId A positive integer representing the identifier number of the elevator
	 * @param maxFloors the maximum number of the floors the elevator will be able to visit.
	 * @param minFloors the minimum number of the floors the elevator will be able to visit.
	 * @return A new elevator object with the given attributes
	 */
	public static ElevatorInterface createElevator(int elevatorId, int capacity, int maxFloors, int minFloors) throws NegativeCapacityException, NegativeFloorException
	{
		return new Elevator(elevatorId, capacity, maxFloors, minFloors);
	}
}
