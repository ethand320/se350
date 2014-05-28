/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package implTests;

import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pExceptions.NegativeCapacityException;
import pExceptions.NegativeElevatorException;
import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pImpls.Direction;
import pImpls.Elevator;
import pImpls.ElevatorControlModuleImpl;
import pImpls.Person;
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
     * This test will be added for the last iteration.
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
    }
    */
    
    /**
     * Test of getElevator method, of class ElevatorControlModuleImpl.
     */
    @Test
    public void testGetElevator()
    {
        System.out.println("getElevator");
        int index = 1;
        ElevatorControlModuleImpl instance = new ElevatorControlModuleImpl();
        ElevatorInterface expResult = null;
        ElevatorInterface result = instance.getElevator(index);
        assertThat(expResult,not(equalTo(result)));
        assertNotNull(result);
    }

    /**
     * Test of shutDown method, of class ElevatorControlModuleImpl.
     */
    @Test
    public void testShutDown()
    {
        System.out.println("shutDown");
        ElevatorControlModuleImpl instance = new ElevatorControlModuleImpl();
        ElevatorControlModuleImpl instance2 = new ElevatorControlModuleImpl();
        instance.shutDown();
        assertNotNull(instance);
        assertThat(instance,not(equalTo(instance2)));
        
    }

    /**
     * Test of elevatorDoorsOpened method, of class ElevatorControlModuleImpl.
     */
    @Test
    public void testElevatorDoorsOpened()
    {
        System.out.println("elevatorDoorsOpened");
        try
	{
            ElevatorInterface elevator = new Elevator(1,10,10,15);
            int floorNumber = 0;
            ElevatorControlModuleImpl instance = new ElevatorControlModuleImpl();
            instance.elevatorDoorsOpened(elevator, floorNumber);
            assertNotNull(instance.getElevatorNum());
        }
        catch (NegativeFloorException | NegativeCapacityException e)
        {
                fail(e.getMessage());
        }
    }

    /**
     * Test of addPersonToFloor method, of class ElevatorControlModuleImpl.
     */
    @Test
    public void testAddPersonToFloor()
    {
        System.out.println("addPersonToFloor");
        try
        {
            Person inPerson = new Person(1,1,2);
            int floorNum = 1;
            ElevatorControlModuleImpl instance;
            instance = new ElevatorControlModuleImpl();
            instance.addPersonToFloor(inPerson, floorNum);
            assertNotNull(instance);
        }
        catch (NegativeFloorException | NullPassengerException e)
        {
                fail(e.getMessage());
        }
    }
    
}
