/**
 * The Building class is a Singleton which contains all of the Elevators and Floors needed for the simulation environment. The constructor is called upon invoking getInstance()
 * and Elevators/Floors are created through calls to static Factory methods.
 */
public final class Building
{
    private volatile static Building buildingInstance;
    private Floor[] floors;
    private Elevator[] elevators;
    private static final int DEFAULT_ELEVATORS = 3;
    private static final int DEFAULT_FLOORS = 6;
    
    /**
     * The constructor for the Building singleton that gets called by the <code>getInstance(int floors, int elevators)</code> method.
     * This method is private so as to follow the Singleton design pattern.
     * @param numberOfFloors A positive integer representing the number of floors that the Building singleton will contain. This value cannot be changed.
     * @param numberOfElevators A positive integer representing the number of elevators that the Building singleton will contain. This value cannot be changed.
     * @throws NegativeCapacityException if either numberOfFloors or numberOfElevators are negative.
     */
    private Building(int numberOfFloors, int numberOfElevators) throws NegativeCapacityException
    {
        populateFloors(numberOfFloors);
        populateElevators(numberOfElevators);
    }
    /**
     * The constructor for the Building singleton that gets called by the <code>getInstance()</code> method, which passes default values to the non-default constructor.
     * This method is private so as to follow the Singleton design pattern.
     */
    private Building() throws NegativeCapacityException
    {
        this(DEFAULT_FLOORS, DEFAULT_ELEVATORS);
    }
    /**
     * This method represents the point of contact between the Building singleton and the outside world. The first invocation of this method will construct a new Building object which will then be returned.
     * @return a newly instantiated Building instance or the previously constructed Building instance, depending on whether one exists or not at the time of invocation.
     */
    public static synchronized Building getInstance() throws NegativeCapacityException
    {
        if (buildingInstance == null)
        {
            synchronized (Building.class)
            {
                buildingInstance = new Building();
            }
        }
        return buildingInstance;
    }
    /**
     * This method represents the point of contact between the Building singleton and the outside world. This method is functionally equivalent <code>getInstance()</code> except that the parameters passed into this method
     * will be passed to the private Building constructor. Once the Building object has been created, this method is identical to <code>getInstnace()</code>.
     * @param floors The number of floors that the Building singleton will possess.
     * @param elevators The number of elevators that the Building singleton will possess.
     * @return a newly instantiated Building instance or the previously constructed Building instance, depending on whether one exists or not at the time of invocation.
     * @throws NegativeCapacityException if either <code>floors</code> or <code>elevators</code> are non-positive integers.
     */
    public static synchronized Building getInstance(int floors, int elevators) throws NegativeCapacityException
    {
        if (buildingInstance == null)
        {
            synchronized (Building.class)
            {
                buildingInstance = new Building(floors, elevators);
            }
        }
        return buildingInstance;
    }
    /**
     * This method creates the elevators that are inside of the Building.
     * @param numberOfElevators a positive integer value representing the number of elevators that will be inside of the Building.
     * @throws NegativeCapacityException if numberOfElevators is less than or equal to 0
     */
    private void populateElevators(int numberOfElevators) throws NegativeCapacityException
    {
        if (elevators == null)
        {
            if (numberOfElevators <= 0)
            {
                throw new NegativeCapacityException("A building must possess at least one elevator. The value passed was: " + numberOfElevators);
            }
            else
            {
                elevators = new Elevator[numberOfElevators];
                for (int i = 0; i < numberOfElevators; ++i)
                {
                    elevators[i] = Factory.createElevator("Agile", floors.length, i+1);
                }
            }
        }
    }
    /**
     * This method constructs the floor objects that the Building will encompass. Elevators travel between floors with people inside each elevator who disembark upon reaching their desired floor.
     * @param numberOfFloors A positive integer representing the number of floors to place inside the building.
     */
    private void populateFloors(int numberOfFloors) throws NegativeCapacityException
    {
        if (floors == null)
        {
            if (numberOfFloors <= 0)
            {
                throw new NegativeCapacityException("A building must possess at least one floor. The value passed was: " + numberOfFloors);
            }
            else
            {
                floors = new Floor[numberOfFloors];
                for (int i = 0; i < numberOfFloors; ++i)
                {
                    floors[i] = Factory.createFloor();
                }
            }
        }
    }
    /**
     * This method returns an Elevator object for purposes of issuing commands to individual elevators.
     * @param elevatorNumber The index of the elevator that you wish to issue a command to. This number must be positive and not greater than the number of elevators in the Building
     * @return An elevator object that
     * @throw NonExistantElevatorException if elevatorNumber is outside the bounds of the Elevator collection within the Building singleton
     */
    public Elevator getElevator(int elevatorNumber) throws NonExistantElevatorException
    //WARNING: Returns a reference. Fix for next iteration
    {
        if (elevatorNumber <= 0 || elevatorNumber > elevators.length)
        {
            throw new NonExistantElevatorException("Elevator number " + elevatorNumber + " does not exist.");
        }
        return elevators[elevatorNumber - 1];
    }
    /**
     * Accessor which returns the number of floors inside the Building singleton
     * @return a positive integer representing the number of floors that comprise the Building
     */
    public int getNumberOfFloors()
    {
        return floors.length;
    }
    /**
     * Accessor which returns the number of elevators inside the Building singleton
     * @return a positive integer representing the number of elevators that service the Building
     */
    public int getNumberOfElevators()
    {
        return elevators.length;
    }
}
