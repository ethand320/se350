/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UnitTests.factoryTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import pExceptions.NegativeCapacityException;
import pExceptions.NegativeFloorException;
import pFactories.ControlImplFactory;
import pInterfaces.ControlModuleInterface;
import pImpls.*;

/**
 *
 * @author Stouny
 */
public class ControlImplFactoryTest {
    private int elevatorNum;
    private int floorNum;
    public ControlImplFactoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        elevatorNum = 1;
        floorNum = 2;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createElevatorController method, of class ControlImplFactory.
     */
    @Test
    public void testCreateElevatorController() {
        System.out.println("createElevatorController");

        ControlModuleInterface expResult = null;
        ControlModuleInterface result = null;
        ControlModuleInterface failResult = null;
        try
        {
        	failResult = ControlImplFactory.createElevatorController(elevatorNum+1, floorNum+1);
            expResult = new ElevatorControlModuleImpl(elevatorNum, floorNum);
            result = ControlImplFactory.createElevatorController(elevatorNum, floorNum);
        }
        catch (NegativeFloorException | NegativeCapacityException e)
		{
			fail(e.getMessage());
		}
        assertTrue(expResult.getMaxFloors() == result.getMaxFloors());
        assertTrue(expResult.getElevatorNum() == result.getElevatorNum());
        assertFalse(failResult.getMaxFloors() == expResult.getMaxFloors());
        assertFalse(failResult.getElevatorNum() == expResult.getElevatorNum());
    }
    
}
