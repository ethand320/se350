/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UnitTests.implTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pExceptions.NegativeCapacityException;
import pExceptions.NegativeElevatorException;
import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pExceptions.PassengerNotFoundException;
import pImpls.Direction;
import pImpls.Elevator;
import pImpls.ElevatorControlModule;
import pImpls.Person;
import pImpls.XmlParser;
import pInterfaces.ControlModuleInterface;

/**
 * Tests the main functionality of the elevator system.
 */
public class ElevatorTest
{
    public Elevator elevator;
    private int currentFloor = 1;
    public boolean isrunning = true;
    public static final int FLOOR_ONE = 1;
    public static final int FLOOR_TEN = 10;
    public static final int BASEMENT = 0;
    private boolean bDoorsOpen = true;
    private Person singleResult, singleResultFail;
    private ArrayList <Person> multiResult, multiResultFail;
    private static ControlModuleInterface ECM;
    
    @BeforeClass
    public static void setUpClass()
    {
		try
		{
			ECM = ElevatorControlModule.getInstance();
	    	ECM.shutDown();
		}
		catch (NegativeFloorException | NegativeCapacityException
				| NegativeElevatorException e)
		{
			fail(e.getMessage());
		}
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
        try
		{
			elevator = new Elevator(0,XmlParser.getElevCapacity(), 15, 1);
			elevator.shutDown();
	        singleResult = new Person(0,1,2);
			singleResultFail = new Person(1,3,4);               
	        multiResult = new ArrayList <Person>();
	        multiResultFail = new ArrayList <Person>();
	        for(int i = 0; i < 5; ++i)
	        {
	            multiResult.add(new Person(i, 1, 4));
	        }
	        
	        for(int i = 0; i < 5; ++i)
	        {
	            multiResultFail.add(new Person(i + 1, 2, 7));
	        }
		}
		catch (NegativeCapacityException | NegativeFloorException e)
		{
			fail(e.getMessage());
		}
        
    }
    
    @After
    public void tearDown()
    {
    	//unit tests which test the running state of the elevator need to shut down the elevator so its movement doesn't mess with other tests. most of the tests
    	//assume that there are no actively moving elevators
    	elevator.shutDown();
    	ECM.shutDown();
    }

    /**
     * Test of addFloorToQueue method, of class Elevator.
     */
    @Test
    public void addFloorToQueueTest()
    {
        try
        {
            elevator.addFloorToQueue(FLOOR_ONE);
            assertSame(FLOOR_ONE, currentFloor);
            assertNotSame(FLOOR_TEN, currentFloor);
        }
        catch( NegativeFloorException e )
        {
        	fail(e.getMessage());
        }
        try
		{
			elevator.addFloorToQueue(BASEMENT);
			fail("The preceeding call was supposed to throw an exception!");
		}
		catch (NegativeFloorException e)
		{
			//eat the exception because it is expected behavior when adding an invalid floor
		}
        if (currentFloor <= 0)
        {
        	fail("Floor does not exist");
        }

    }

    /**
     * Test of addPassenger method, of class Elevator.
     */
    @Test
    public void addPassengerTest()
    {  
        System.out.println("addPassenger");
        try
        {
			elevator.addPassenger(singleResult);
		}
        catch (NullPassengerException | NegativeFloorException e)
        {
        	fail(e.getMessage());
        }
        ArrayList<Person> elevatorPassenger = elevator.getPassengers();
        assertTrue(elevatorPassenger.contains(singleResult));
        assertFalse(elevatorPassenger.contains(singleResultFail));
    }

    /**
     * Test of addPassengers method, of class Elevator.
     */
    @Test
    public void addPassengersTest()
    {
        System.out.println("addPassenger");
        
        //prevent the elevator from moving
        elevator.shutDown();
        try
		{
			elevator.addPassengers(multiResult);
		}
        catch (NullPassengerException | NegativeFloorException e)
		{
        	fail(e.getMessage());
		}
        ArrayList<Person> elevatorPassenger = elevator.getPassengers();
        for (Person passenger : multiResult)
        {
            assertTrue(elevatorPassenger.contains(passenger));
        }
        for (Person passenger : multiResultFail)
        {
            assertFalse(elevatorPassenger.contains(passenger));
        }
    }

    /**
     * Test of openDoors method, of class Elevator.
     */
    @Test
    public void openDoorsTest()
    {
        System.out.println("openDoors");
        elevator.openDoors();
        assertEquals(true,elevator.isOpen());
        try
        {
            if (elevator.isOpen() != true)
                fail("The doors will not open");
        }catch( IllegalArgumentException e ) {
        }
    }

    /**
     * Test of closeDoors method, of class Elevator.
     */
    @Test
    public void closeDoorsTest()
    {
        System.out.println("closeDoors");
        elevator.closeDoors();
        assertEquals(false,elevator.isOpen());
        if (elevator.isOpen() != false)
            fail("The doors will not close");
    }

    /**
     * Test of getDirection method, of class Elevator.
     */
    @Test
    public void getDirectionTest()
    {
        System.out.println("getDirection");
        Elevator instance = null;
		try
		{
			instance = new Elevator(1, 10, 1, 1);
		}
		catch (NegativeCapacityException | NegativeFloorException e)
		{
			fail(e.getMessage());
		}
        Direction direction = Direction.IDLE;
        Direction result = instance.getDirection();
        assertEquals(direction, result);
        
    }

    /**
     * Test of removePassenger method, of class Elevator.
     */
    @Test
    public void testRemovePassenger()
    {
        System.out.println("removePassenger");
        try
		{
			elevator.addPassenger(singleResult);
			ArrayList<Person> elevatorPassenger = elevator.getPassengers();
	        assertTrue(elevatorPassenger.contains(singleResult));
	        elevator.removePassenger(singleResult);
	        assertFalse(elevatorPassenger.contains(singleResult));
		}
		catch (NullPassengerException | PassengerNotFoundException | NegativeFloorException e)
		{
			fail(e.getMessage());
		}
        
    }
    
    /**
     * Test of removePassengers method, of class Elevator.
     */
    @Test
    public void testRemovePassengers()
    {
        System.out.println("removePassengers");
        try
		{
			elevator.addPassengers(multiResult);
			elevator.addPassengers(multiResultFail);
	        ArrayList<Person> elevatorPassenger = elevator.getPassengers();
	        for (Person passenger : multiResult)
	        {
	            assertTrue(elevatorPassenger.contains(passenger));
	        }
	        elevator.removePassengers(multiResult);
	        for (Person rPassengers : multiResult)
	        {
	            assertFalse(elevatorPassenger.contains(rPassengers));
	        }
	        for (Person passenger : multiResultFail)
	        {
	            assertTrue(elevatorPassenger.contains(passenger));
	        }
		}
		catch (NullPassengerException | PassengerNotFoundException | NegativeFloorException e)
		{
			fail(e.getMessage());
		} 
    }
    
    /**
     * Test of getCapacity method, of class Elevator.
     */
    @Test
    public void testGetCapacity()
    {
        System.out.println("getCapacity");

        int testResult = XmlParser.getElevCapacity();
        int result = elevator.getCapacity();
        assertEquals(testResult, result);
    }

    /**
     * Test of getPassengers method, of class Elevator.
     */
    @Test
    public void getPassengersTest()
    {
        System.out.println("getPassengers");
        Elevator gPassengers = null;
		try
		{
			gPassengers = new Elevator(1,10,10,1);
		}
		catch (NegativeCapacityException | NegativeFloorException e)
		{
			fail(e.getMessage());
		}
        ArrayList<Person> passengers = new ArrayList<Person>();
        ArrayList<Person> result = gPassengers.getPassengers();
        assertEquals(passengers, result);
    }

    /**
     * Test of getElevatorId method, of class Elevator.
     */
    @Test
    public void getElevatorIdTest()
    {
        System.out.println("getElevatorId");
        Elevator eID = null;
		try
		{
			eID = new Elevator(0,10,10,1);
			eID.shutDown();
		}
		catch (NegativeCapacityException | NegativeFloorException e)
		{
			fail(e.getMessage());
		}
        int elevatorId = eID.getElevatorId();
        int result = elevator.getElevatorId();
        assertEquals(elevatorId, result);
    }

    /**
     * Test of shutDown method, of class Elevator.
     */
    @Test
    public void testShutDown()
    {
        System.out.println("shutDown");
        
        //since the running state of the elevator is what's being tested, we'll restart the thread by re-assigning elevator to a new elevator object
		try
		{
			elevator = new Elevator(0,10, 15, 1);
		}
		catch (NegativeCapacityException | NegativeFloorException e)
		{
			fail(e.getMessage());
		}

        elevator.shutDown();
        assertEquals(false,elevator.isRunning());
    }
    /**
     * Test of run method, of class Elevator.
     */
    @Test
    public void testRun()
    {
        System.out.println("run");
        //since the running state of the elevator is what's being tested, we'll restart the thread by re-assigning elevator to a new elevator object
		try
		{
			elevator = new Elevator(0,10, 15, 1);
		}
		catch (NegativeCapacityException | NegativeFloorException e)
		{
			fail(e.getMessage());
		}
        assertEquals(true, elevator.isRunning());
    }   
}
