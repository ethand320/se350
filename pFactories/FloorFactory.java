package pFactories;
import pExceptions.NegativeFloorException;
import pInterfaces.FloorInterface;
import pImpls.Floor;

/**
 * The Factory class is aptly named, serving as a factory for floors. All of its methods
 * are public and static to facilitate this behavior. It is impossible to instantiate a Factory object.
 */
public class FloorFactory
{
    /**
     * This method creates and returns a floor with a specified identification number.
     * @param floorId A positive integer representing the identifier number of the floor
     * @return A new floor object with the unique id
     * @throws NegativeFloorException if the id is less than 0 or greater than or equal to the maximum number of floors in the simulation
     */
    public static FloorInterface createFloor(int floorId) throws NegativeFloorException
    {
        return new Floor(floorId);
    }
}
