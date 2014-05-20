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
import pImpls.Direction;
import pImpls.ElevatorControlModule;
import pImpls.Person;
import pInterfaces.ControlModuleInterface;
import pInterfaces.ElevatorInterface;

/**
 *
 * @author Stouny
 */
public class ElevatorControlModuleTest
{
    
    public ElevatorControlModuleTest()
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
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getInstance method, of class ElevatorControlModule.
     */
    @Test
    public void testGetInstance_0args()
    {
        System.out.println("getInstance");
        ControlModuleInterface expResult = null;
        ControlModuleInterface result = null;
		try
		{
			result = ElevatorControlModule.getInstance();
		}
		catch (NegativeFloorException | NegativeCapacityException
				| NegativeElevatorException e)
		{
			fail(e.getMessage());
		}
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInstance method, of class ElevatorControlModule.
     */
    @Test
    public void testGetInstance_int_int()
    {
        System.out.println("getInstance");
        int elevatorNum = 0;
        int floorNum = 0;
        ControlModuleInterface expResult = null;
        ControlModuleInterface result = null;
		try
		{
			result = ElevatorControlModule.getInstance(elevatorNum, floorNum);
		}
		catch (NegativeFloorException | NegativeCapacityException
				| NegativeElevatorException e)
		{
			fail(e.getMessage());
		}
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of elevatorCallReceiver method, of class ElevatorControlModule.
     */
    @Test
    public void testElevatorCallReceiver()
    {
        System.out.println("elevatorCallReceiver");
        int floorNumber = 0;
        Direction directionRequest = null;
        ElevatorControlModule instance = null;
        try
		{
			instance.elevatorCallReceiver(floorNumber, directionRequest);
		}
		catch (NegativeFloorException e)
		{
			fail(e.getMessage());
		}
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getElevator method, of class ElevatorControlModule.
     */
    @Test
    public void testGetElevator()
    {
        System.out.println("getElevator");
        int index = 0;
        ElevatorControlModule instance = null;
        ElevatorInterface expResult = null;
        ElevatorInterface result = instance.getElevator(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shutDown method, of class ElevatorControlModule.
     */
    @Test
    public void testShutDown()
    {
        System.out.println("shutDown");
        ElevatorControlModule instance = null;
        instance.shutDown();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of elevatorDoorsOpened method, of class ElevatorControlModule.
     */
    @Test
    public void testElevatorDoorsOpened()
    {
        System.out.println("elevatorDoorsOpened");
        ElevatorInterface elevator = null;
        int floorNumber = 0;
        ElevatorControlModule instance = null;
        try
		{
			instance.elevatorDoorsOpened(elevator, floorNumber);
		}
		catch (NegativeFloorException e)
		{
			fail(e.getMessage());
		}
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPersonToFloor method, of class ElevatorControlModule.
     */
    @Test
    public void testAddPersonToFloor()
    {
        System.out.println("addPersonToFloor");
        Person inPerson = null;
        int floorNum = 0;
        ElevatorControlModule instance = null;
        try
        {
			instance.addPersonToFloor(inPerson, floorNum);
		} 
        //TODO: once we ensure that instance is properly created, don't catch NullPointerException anymore because it's a runtime exception
        catch (NullPassengerException | NullPointerException | NegativeFloorException e)
        {
			fail(e.getMessage());
		}
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
