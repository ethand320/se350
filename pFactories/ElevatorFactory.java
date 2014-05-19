package pFactories;
import pExceptions.NegativeCapacityException;
import pExceptions.NegativeFloorException;
import pImpls.Elevator;
import pInterfaces.ElevatorInterface;
import pImpls.Elevator;

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
     * @return A new elevator object with the given attributes
     * @throws NegativeCapacityException if the capacity of the elevator object would be less than 1
     * @throws NegativeFloorException 
     */
    public static ElevatorInterface createElevator(int elevatorId, int capacity, int maxFloors, int minFloors) throws NegativeCapacityException, NegativeFloorException
    {
        return new Elevator(elevatorId, capacity, maxFloors, minFloors);
    }
}
