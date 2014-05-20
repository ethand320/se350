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
import pExceptions.NegativeFloorException;
import pFactories.FloorFactory;
import pInterfaces.FloorInterface;

/**
 *
 * @author Stouny
 */
public class FloorFactoryTest {
    
    public FloorFactoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createFloor method, of class FloorFactory.
     */
    @Test
    public void testCreateFloor() {
        System.out.println("createFloor");
        int floorId = 0;
        FloorInterface expResult = null;
        FloorInterface result = null;
		try
		{
			result = FloorFactory.createFloor(floorId);
		}
		catch (NegativeFloorException e)
		{
			fail(e.getMessage());
		}
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
