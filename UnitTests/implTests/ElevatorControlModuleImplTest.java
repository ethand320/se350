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
import pInterfaces.ElevatorInterface;

/**
 *
 * @author Stouny
 */
public class ElevatorControlModuleImplTest
{
    
    public ElevatorControlModuleImplTest()
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
     * Test of elevatorCallReceiver method, of class ElevatorControlModuleImpl.
     */
    @Test
    public void testElevatorCallReceiver()
    {
        System.out.println("elevatorCallReceiver");
        int floorNumber = 0;
        Direction directionRequest = null;
        ElevatorControlModuleImpl instance = null;
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
     * Test of getElevator method, of class ElevatorControlModuleImpl.
     */
    @Test
    public void testGetElevator()
    {
        System.out.println("getElevator");
        int index = 0;
        ElevatorControlModuleImpl instance = null;
        ElevatorInterface expResult = null;
        ElevatorInterface result = instance.getElevator(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shutDown method, of class ElevatorControlModuleImpl.
     */
    @Test
    public void testShutDown()
    {
        System.out.println("shutDown");
        ElevatorControlModuleImpl instance = null;
        instance.shutDown();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of elevatorDoorsOpened method, of class ElevatorControlModuleImpl.
     */
    @Test
    public void testElevatorDoorsOpened()
    {
        System.out.println("elevatorDoorsOpened");
        ElevatorInterface elevator = null;
        int floorNumber = 0;
        ElevatorControlModuleImpl instance = null;
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
     * Test of addPersonToFloor method, of class ElevatorControlModuleImpl.
     */
    @Test
    public void testAddPersonToFloor()
    {
        System.out.println("addPersonToFloor");
        Person inPerson = null;
        int floorNum = 0;
        ElevatorControlModuleImpl instance;
		try
		{
			instance = new ElevatorControlModuleImpl(floorNum, 1);
			instance.addPersonToFloor(inPerson, floorNum);
		}
		catch (NegativeFloorException | NegativeCapacityException | NullPassengerException | NegativeElevatorException e)
		{
			fail(e.getMessage());
		}
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
