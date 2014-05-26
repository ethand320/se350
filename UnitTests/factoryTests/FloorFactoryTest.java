/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UnitTests.factoryTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        int floorId = 1;
        FloorInterface expResult = null;
        FloorInterface result = null;
        FloorInterface failresult = null;
		try
		{
			result = FloorFactory.createFloor(floorId);
                        expResult = FloorFactory.createFloor(floorId);
                        failresult = FloorFactory.createFloor(floorId+1);
		}
		catch (NegativeFloorException e)
		{
			fail(e.getMessage());
		}
        assertEquals(expResult.getId(), result.getId());
        assertFalse(failresult.getId() == result.getId());
        assertNotNull(result.getId());
        assertNotNull(failresult.getId());
    }
    
}
