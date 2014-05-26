/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UnitTests.implTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import pExceptions.NegativeCapacityException;
import pExceptions.NegativeElevatorException;
import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pImpls.*;
import pInterfaces.*;

import java.util.ArrayList;

/**
 *
 * @author Stouny
 */
public class FloorTest
{
	//floor creation variables
	private Floor instance;
	private int defaultFloorID = 0;
	private int failFloorID = 1;
	
	//person creation variables
	private int personSuccessID = 0;
	private int personSuccessStartFloor = 2;
	private int personSuccessDestinationFloor = 5;
	private int personFailID = 1;
	private int personFailStartFloor = 1;
	private int personFailDestinationFloor = 6;
	
	//elevator creation variables
	private int defaultElevID = 0;
	private int defaultElevCapacity = 5;
	private int defaultElevMinFloors = 1;
	private int defaultElevMaxFloors = 8;
    
    @BeforeClass
    public static void setUpClass()
    {
    	try
		{
			ControlModuleInterface ECM = ElevatorControlModule.getInstance();
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
			instance = new Floor(defaultFloorID);
		}
		catch (NegativeFloorException e)
		{
			fail(e.getMessage());
		}
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of addPersonToFloor method, of class Floor.
     */
    @Test
    public void testAddPersonToFloor()
    {
        System.out.println("addPersonToFloor");
        Person inPerson = null;
        try
		{
        	ArrayList<Person> expResult = new ArrayList<Person>();
        	ArrayList<Person> result = instance.getWaitingPeople();
			inPerson = new Person(personSuccessID, personSuccessStartFloor, personSuccessDestinationFloor);
			instance.addPersonToFloor(inPerson);
			expResult.add(inPerson);
			assertEquals(expResult, result);
		}
		catch (NullPassengerException | NegativeFloorException e)
		{
			fail(e.getMessage());
		}
    }

    /**
     * Test of summonElevator method, of class Floor.
     */
    @Test
    public void testSummonElevator()
    {
        System.out.println("summonElevator");
        Direction directionToGo = null;
        instance.summonElevator(directionToGo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Floor.
     */
    @Test
    public void testGetId()
    {
        System.out.println("getId");
        int expResult = defaultFloorID;
        
        //the ID is returned as a 1-based index externally, so we need to subtract 1 to get its zero-based representation
        int result = instance.getId() - 1;
        assertEquals(expResult, result);
        assertNotEquals(result, failFloorID);
    }

    /**
     * Test of removeFromFloor method, of class Floor.
     */
    @Test
    public void testRemoveFromFloor()
    {
        System.out.println("removeFromFloor");
        ElevatorInterface elevatorToEnter;
		try
		{
			//TODO: insert people into the floor object, making sure that their direction lines up properly with the elevator or else the person won't get added
			//should probably also make tests for adding in each direction (up, down, idle) to fully test this out
	        Direction directionToGo = Direction.UP;
			elevatorToEnter = new Elevator(defaultElevID, defaultElevCapacity, defaultElevMinFloors, defaultElevMaxFloors);
			elevatorToEnter.shutDown();
			
			ArrayList<Person> elevatorPassengers = elevatorToEnter.getPassengers();
			ArrayList<Person> floorPassengers = instance.getWaitingPeople();
			
			ArrayList<Person> expFloorResult = new ArrayList<Person>();
			ArrayList<Person> expElevatorResult = new ArrayList<Person>();
			
	        instance.removeFromFloor(elevatorToEnter, directionToGo);
	        
	        assertEquals(expFloorResult, floorPassengers);
	        assertEquals(expElevatorResult, elevatorPassengers);

		}
		catch (NegativeCapacityException | NegativeFloorException e)
		{
			fail(e.getMessage());
		}

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testGetWaitingPeople()
    {
    	System.out.println("getWaitingPeople");
    	Person personToAdd = null;
    	Person personToNotAdd = null;
    	ArrayList<Person> beforeExpResult = new ArrayList<Person>();
    	ArrayList<Person> afterExpResult = new ArrayList<Person>();
		try
		{
			personToAdd = new Person(personSuccessID, personSuccessStartFloor, personSuccessDestinationFloor);
	    	personToNotAdd = new Person(personFailID, personFailStartFloor, personFailDestinationFloor);
	    	
	    	//by calling the copy constructor instead of directly assigning before to the ArrayList returned by getWaitingPeople, we ensure that
	    	//the two variables are references to two objects with the same data instead of references to the exact same object
	    	ArrayList<Person> before = new ArrayList<Person>(instance.getWaitingPeople());
	    	assertFalse(before.contains(personToAdd));
			assertFalse(before.contains(personToNotAdd));
			assertEquals(before, beforeExpResult);
	    	
	    	instance.addPersonToFloor(personToAdd);
	    	afterExpResult.add(personToAdd);
	    	
	    	ArrayList<Person> after = new ArrayList<Person>(instance.getWaitingPeople());
	    	assertTrue(after.contains(personToAdd));
	    	assertFalse(after.contains(personToNotAdd));
	    	assertEquals(after, afterExpResult);
	    	assertNotEquals(after, before);
		}
		catch (NegativeFloorException | NullPassengerException e)
		{
			fail(e.getMessage());
		}
    }
}
