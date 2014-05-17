/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UnitTests.implTests;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import pExceptions.NegativeCapacityException;
import pExceptions.NullPassengerException;
import pExceptions.PassengerNotFoundException;
import pImpls.*;
import pInterfaces.ElevatorInterface;

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
    private Person p, pfail;
    private ArrayList <Person> b, bfail;
    public ElevatorTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
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
			elevator = new Elevator(0,10, 15, 0);
		}
		catch (NegativeCapacityException e)
		{
			fail(e.getMessage());
		}
        pfail = new Person(3,4);               
        p = new Person(1,2);
        b = new ArrayList <Person>();
        bfail = new ArrayList <Person>();
        for(int i = 0; i < 5; ++i)
        {
            b.add(new Person(1,i+1));
        }
        
        for(int i = 0; i < 5; ++i)
        {
            bfail.add( new Person(2,i+1) );
        }
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of addFloorToQueue method, of class Elevator.
     */
    @Test
    public void addFloorToQueueTest()
    {
        elevator.addFloorToQueue(FLOOR_ONE);
        assertSame(FLOOR_ONE, currentFloor);
        assertNotSame(FLOOR_TEN, currentFloor);
        try
        {
        elevator.addFloorToQueue(BASEMENT);
            if (currentFloor <= 0)
                fail("Floor does not exist");
        }
        catch( IllegalArgumentException e )
        {
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
			elevator.addPassenger(p);
		}
        catch (NullPassengerException e)
        {
        	fail(e.getMessage());
        }
        ArrayList<Person> elevatorPassenger = elevator.getPassengers();
        assertTrue(elevatorPassenger.contains(p));
        assertFalse(elevatorPassenger.contains(pfail));
    }

    /**
     * Test of addPassengers method, of class Elevator.
     */
    @Test
    public void addPassengersTest()
    {
        System.out.println("addPassenger");
        try
		{
			elevator.addPassengers(b);
		}
        catch (NullPassengerException e)
		{
        	fail(e.getMessage());
		}
        ArrayList<Person> elevatorPassenger = elevator.getPassengers();
        for (Person passenger : b)
        {
            assertTrue(elevatorPassenger.contains(passenger));
        }
        for (Person passenger : bfail)
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
		catch (NegativeCapacityException e)
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
			elevator.addPassenger(p);
			ArrayList<Person> elevatorPassenger = elevator.getPassengers();
	        assertTrue(elevatorPassenger.contains(p));
	        elevator.removePassenger(p);
	        assertFalse(elevatorPassenger.contains(p));
		}
		catch (NullPassengerException | PassengerNotFoundException e)
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
			elevator.addPassengers(b);
			elevator.addPassengers(bfail);
	        ArrayList<Person> elevatorPassenger = elevator.getPassengers();
	        for (Person passenger : b)
	        {
	            assertTrue(elevatorPassenger.contains(passenger));
	        }
	        elevator.removePassengers(b);
	        for (Person rPassengers : b)
	        {
	            assertFalse(elevatorPassenger.contains(rPassengers));
	        }
	        for (Person passenger : bfail)
	        {
	            assertTrue(elevatorPassenger.contains(passenger));
	        }
		}
		catch (NullPassengerException | PassengerNotFoundException e)
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
        Elevator instance = null;
		try
		{
			instance = new Elevator(1,10,10,1);
		}
		catch (NegativeCapacityException e)
		{
			fail(e.getMessage());
		}
        int testResult = 10;
        int result = instance.getCapacity();
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
		catch (NegativeCapacityException e)
		{
			fail(e.getMessage());
		}
        ArrayList<Person> passengers = new ArrayList<Person>();
        ArrayList<Person> result = gPassengers.getPassengers();
        assertEquals(passengers, result);
        // TODO review the generated test code and remove the default call to fail.
        try
        {
	        if (passengers == null)
	        {
	        	fail("No Passengers in this elevator");
	        }
	    }
        catch(IllegalArgumentException e)
        {
        }
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
			eID = new Elevator(1,10,10,1);
		}
		catch (NegativeCapacityException e)
		{
			fail(e.getMessage());
		}
        int elevatorId = eID.getElevatorId();
        assertEquals(elevatorId, elevator.getElevatorId());
    }

    /**
     * Test of shutDown method, of class Elevator.
     */
    @Test
    public void testShutDown()
    {
        System.out.println("shutDown");
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
        assertEquals(true, elevator.isRunning());
        try
        {
            if (!elevator.isRunning())
                fail("Systems are Online without call!");
        }
        catch(IllegalArgumentException e)
        {
        }
    }   
}
