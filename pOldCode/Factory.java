/**
 * The Factory class is aptly named, serving as a factory for elevators and floors. All of its methods
 * are public and static to facilitate this behavior. It is impossible to instantiate a Factory object.
 */
public class Factory
{
    /**
     * The constructor for the Factory class. This constructor should never be called, 
     * and nothing will happen if it is called.
     */
    private Factory()
    {
        
    }
    /**
     * This method creates and returns a new elevator object based upon the parameters.
     * @param type A string representing the properties of the elevator that you wish to create.
     * @param numFloors A positive integer representing the number of floors that the elevator should service
     * @param elevatorID A positive integer representing the indentifier number of the elevator
     * @throw NegativeCapacityException if numFloors or elevatorID are less than zero
     * @return A new elevator object with the given attributes
     */
    public static Elevator createElevator(String type, int numFloors, int elevatorID) throws NegativeCapacityException
    {
        if (type.equals("Agile"))
            return new Elevator(20,5, numFloors, elevatorID);
        else if (type.equals("Large"))
            return new Elevator(5,20, numFloors, elevatorID);
        else if (type.equals("Normal"))
            return new Elevator();
        else
            return null;
    }
    /**
     * This method creates and returns a new floor object with the given <code>capacity</code>.
     * @param capacity A postiive integer representing the number of people who can reside on this floor at any given time.
     * @throws NegativeCapacityException if <code>capacity</code> is less than or equal to zero.
     * @return a new floor object with its capacity set to the <code>capacity</code> parameter.
     */
    public static Floor createFloor(int capacity) throws NegativeCapacityException
    {
        if (capacity == 0)
        {
            return createFloor();
        }
        else if (capacity < 0)
        {
            throw new NegativeCapacityException("Floor Capacity cannot be negative." + capacity + " is invalid.");
        }
        return new Floor(capacity);
    }
    /**
     * This method creates and returns a new floor object with default arguments.
     * @return a new floor object with default capacity.
     */
    public static Floor createFloor()
    {
        return new Floor();
    }
}
