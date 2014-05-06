package pFactories;
import pImpls.Elevator;
import pInterfaces.ElevatorInterface;
import pImpls.Elevator;

/**
 * The ElevatorFactory class is aptly named, serving as a factory for elevators. All of its methods
 * are public and static to facilitate this behavior. It is impossible to instantiate a Factory object.
 */
public class ElevatorFactory
{
    /*
    *
    */
    private ElevatorFactory()
    {
        
    }
    
        /**
     * This method creates and returns a new elevator object based upon the parameters.
     * @param capacity A positive integer representing the number total number of people the elevator can hold.
     * @param elevatorID A positive integer representing the indentifier number of the elevator
     * @throw NegativeCapacityException if capacity or elevatorID are less than zero
     * @return A new elevator object with the given attributes
     */
    public static ElevatorInterface createElevator(int elevatorId, int capacity)
    {
        return new Elevator(elevatorId, capacity);
    }
}
