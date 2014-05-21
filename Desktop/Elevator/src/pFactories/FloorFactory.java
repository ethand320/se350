package pFactories;
import pInterfaces.FloorInterface;
import pImpls.Floor;

/**
 * The Factory class is aptly named, serving as a factory for floors. All of its methods
 * are public and static to facilitate this behavior. It is impossible to instantiate a Factory object.
 */
public class FloorFactory
{
    private FloorFactory()
    {
        
    }
    
      /**
     * This method creates and returns a floor with a specified identification number.
     * @param floorId A positive integer representing the indentifier number of the floor
     * @throw NegativeCapacityException if numFloors or elevatorID are less than zero
     * @return A new floor object with the unique id
     */
     
    public static FloorInterface createFloor(int floorId)
    {
        return new Floor(floorId);
    }
}