/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//add a comment here.  here is ourfix commit 

package UnitTests.factoryTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import pImpls.*;
import pExceptions.NegativeCapacityException;
import pExceptions.NegativeFloorException;
import pFactories.*;
import pInterfaces.ElevatorInterface;

/**
 *
 * @author Stouny
 */
public class ElevatorFactoryTest {
    private int elevatorId;
    private int capacity;
    private int maxFloors;
    private int minFloors;
    
    public ElevatorFactoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        elevatorId = 1;
        capacity = 10;
        maxFloors = 10;
        minFloors = 1;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createElevator method, of class ElevatorFactory.
     */
    @Test
    public void testCreateElevator() {
        System.out.println("createElevator");
        ElevatorInterface expResult = null;
        ElevatorInterface result = null;
        ElevatorInterface failResult = null;
		try
		{
			expResult = new Elevator(elevatorId, capacity, maxFloors, minFloors);
			result = ElevatorFactory.createElevator(elevatorId, capacity, maxFloors, minFloors);
	        failResult = ElevatorFactory.createElevator(elevatorId+1, capacity+1, maxFloors+1, minFloors+1);
		}
		catch (NegativeCapacityException | NegativeFloorException e)
		{
			fail(e.getMessage());
		}
        
        assertEquals(expResult.getElevatorId(), result.getElevatorId());
        assertEquals(expResult.getCapacity(), result.getCapacity());
        assertFalse(failResult.getCapacity()==expResult.getCapacity());
        assertFalse(failResult.getElevatorId()==expResult.getElevatorId());

    }
    
}
