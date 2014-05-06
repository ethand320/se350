/**
 * The Elevator class represents a moveable container that navigates through various floors within the Building singleton. Elevators can hold a certain number of people and interpret
 * requests to visit certain floors. Each Elevator object runs in its own thread and will continue to run until manually shut down.
 */
public class Elevator implements Runnable, CallBox
{
    private int speed, capacity, currentFloor, id;
    private boolean running;
    
    private boolean goingUp = true;
    private boolean doorsOpen = false;
    private boolean idle = false;
    private boolean floorDestinations[];
    
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_SPEED = 10;
    private static final int DEFAULT_FLOORS = 10;
    private static final int DEFAULT_ID = 0;
    
    private Thread elevatorThread = new Thread(this);
    /**
     * Constructor which allows for properties such as the speed, capacity, and floor range of the elevator to be customized.
     * @param paramCapacity The number of people this elevator can hold at a given time. When the elevator hits capacity, no more people may board the elevator.
     * @param numFloors The number of floors that this elevator services. This value cannot be larger than the number of floors in the building that owns it, but it may be smaller
     * @param idNumber The unique identifier number. This number cannot be negative, but it need not be in consecutive order compared to other elevators in the building.
     * @throws NegativeCapacityException if any of the values passed into it are negative
     */
    public Elevator(int paramSpeed, int paramCapacity, int numFloors, int idNumber) throws NegativeCapacityException
    {
        setID(idNumber);
        setSpeed(paramSpeed);
        setCapacity(paramCapacity);
        createFloorArray(numFloors);
        elevatorThread.start();
    }
    /**
     * Default constructor. Calls the non-default constructor with default values passed in as parameters.
     */
    public Elevator() throws NegativeCapacityException
    {
        this(DEFAULT_SPEED, DEFAULT_CAPACITY, DEFAULT_FLOORS, DEFAULT_ID);
    }
    /**
     * Handles the movement of the elevator. 
     * Will continue to run until <code>shutDownElevator()</code> is called.
     */
    public void run()
    {
        System.out.println("Elevator " + getID() + " has started");
        running = true;
        while (running)
        {
            synchronized(this)
            {
                if (!hasDestinations(floorDestinations))
                {
                    try
                    {
                        idle = true;
                        wait(10000);
                        returnToDefault();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            synchronized(this)
            {
                if (hasDestinations(floorDestinations)) 
                {
                    try
                    {
                        wait(3000);
                        if (goingUp && currentFloor < getNumOfFloors())
                            currentFloor++;
                        else if (currentFloor > 1)
                            currentFloor--;
                        System.out.println("Elevator " + getID() + " passing floor " + currentFloor);
                        if (floorDestinations[currentFloor])
                        {
                            doorsOpen = true;
                            System.out.println("Elevator " + getID() + " reached destination: " + currentFloor);
                            wait(3000);
                            doorsOpen = false;
                            floorDestinations[currentFloor] = false;
                        }
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    /**
     * Simulates the act of pressing a button while inside an elevator. 
     * @param floorNum The floor that the elevator should visit, as an integer
     * @throws InvalidFloorException If <code>floorNum</floor> is less than one
     * or greater than the number of floors that the elevator services.
     */
    public void pressButton(int floorNum) throws InvalidFloorException

    {
        synchronized(this)
        {
            if (floorNum < 0 || floorNum > getNumOfFloors()) //bounds checking
            {
                throw new InvalidFloorException("Floor " + floorNum + " does not exist.");
            }
            switchDirections(floorNum); //update elevator direction, accounting for the case when the elevator is idle and can accept requests going in either direction
            if ((floorNum > currentFloor && !goingUp) || (floorNum < currentFloor && goingUp) || floorNum == currentFloor) //direction checking
            {
                System.out.println("Elevator " + getID() + "'s request for floor " + floorNum + " denied. Elevator is travelling in the opposite direction.");
                return;
            }
            System.out.println("Destination added for Elevator " + getID() + ": Floor " + floorNum);
            floorDestinations[floorNum] = true;
            idle = false; //in case elevator was in the middle of going back to the default floor
            notifyAll();
        }
    }
    /**
     * If the elevator stays put for 10 seconds, this method returns the elevator to
     * the default floor while staying available for future floor requests.
     */
    private void returnToDefault()
    {
        while (idle && currentFloor > 1)
        { 
            synchronized(this)
            {
                try
                {
                    System.out.println("Elevator " + getID() + " is returning to default floor");
                    wait(3000);
                    currentFloor--;
                    System.out.println("Elevator " + getID() + " is currently on floor: " + currentFloor);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        synchronized(this)
        {
            idle = false;
        }
    }
    /**
     * This method checks for valid floor destination requests iteratively.
     * @param The array holding this elevator's destination list
     * @return <code>True</code> if there is at least one destination in the list,
     * <code>False</code> otherwise.
     */
    private boolean hasDestinations(boolean[] destinations)
    {
        synchronized(this)
        {
            for (int i = 0; i < destinations.length; i++)
            {
                if (destinations[i])
                {
                    return true;
                }
            }
        }
        return false;
    }
       /**
         * This method switches the direction that the elevator is travelling based 
         * on certain conditions. These conditions include being at the lowest or 
         * highest floor or the idle status of the elevator.
         */
    private void switchDirections(int floorNum)
    {
        synchronized(this)
        {
            if (1 == currentFloor)
            {
                goingUp = true;
            }
            else if (getNumOfFloors() == currentFloor)
            {
                goingUp = false;
            }
            else if (idle)
            {
                if (currentFloor > floorNum)
                {
                    goingUp = false;
                }
                else
                {
                    goingUp = true;
                }
            } 
        }
    }
       /**
         * Retrieves the current floor that this particular elevator object is located.
         * @return A positive integer representing the current floor that the elevator is on. This number may not be larger than the number of floors that this elevator services.
         */
    public int getCurrentFloor()
    {
        return currentFloor;
    }
       /**
         * Retrieves the number of floors that this elevator object services.
         * Unless the simulation is investigating elevators that do not service the entire building,
         * this value should be equal to the floors parameter in Building.
         * @return An integer representing the number of floors that this elevator services, which 
         * may not necessarily be the number of flooors in the building that owns it.
         */
    private int getNumOfFloors()
    {
        return floorDestinations.length;
    }
    /**
     * Retrieves the identifier number assigned to this elevator.
     * @return A positive integer representing the ID number of this elevator object. No two elevators should have the same ID number.
     */
    private int getID()
    {
        return id;
    }
    /**
     * Shuts down the <code>run()</code> method and stops the elevator from processing
     * requests.
     */
    public synchronized void shutDownElevator()
    {
        running = false;
        notifyAll();
    }
    /**
     * Sets the speed of the elevator.
     * @param paramSpeed an integer representing the speed of the elevator. Smaller integers result in slower elevators. paramSpeed must be positive.
     * @throws NegativeCapacityException if paramSpeed is negative.
     */
    private void setSpeed(int paramSpeed) throws NegativeCapacityException
    {
        if (paramSpeed <= 0)
        {
            throw new NegativeCapacityException("The speed value of an elevator cannot be negative. The passed value was: " + paramSpeed);
        }
        speed = paramSpeed;
    }
    /**
     * Sets the capacity of the elevator.
     * @param paramCapacity an integer representing how many people can fit in this elevator at one time. No people may enter the elevator once it reaches capacity.
     * @throws NegativeCapacityException if paramCapacity is less than 1.
     */
    private void setCapacity(int paramCapacity) throws NegativeCapacityException
    {
        if (paramCapacity <= 0)
        {
            throw new NegativeCapacityException("The capacity value " + paramCapacity + " is less than zero.");
        }
        capacity = paramCapacity;
    }
    /**
     * Creates the destination list
     * @param arrayLen an integer representing how many floors this elevator services. This number may not be larger than the number of floors that the building object has.
     * @throws NegativeCapacityException if arrayLen is a non-positive integer value.
     */
    private void createFloorArray(int arrayLen) throws NegativeCapacityException
    {
        if (arrayLen < 1)
        {
            throw new NegativeCapacityException ("The capacity of the floors array cannot be less than 1. The passed value was: " + arrayLen);
        }
        floorDestinations = new boolean[arrayLen];
    }
    /**
     * Sets the identifier number of the elevator object.
     * @param idParam the integer which will be associated with this elevator object. This value cannot be negative.
     * @throws NegativeCapacityException if idParam is less than zero.
     */
    private void setID(int idParam) throws NegativeCapacityException
    {
        if (idParam < 0)
        {
            throw new NegativeCapacityException("The id number cannot be negative. The value passed was: " + idParam);
        }
        id = idParam;
    }
}
