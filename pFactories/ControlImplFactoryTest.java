/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pFactories;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pInterfaces.ControlModuleInterface;

/**
 *
 * @author Stouny
 */
public class ControlImplFactoryTest {
    
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
        int elevatorNum = 0;
        int floorNum = 0;
        ControlModuleInterface expResult = null;
        ControlModuleInterface result = ControlImplFactory.createElevatorController(elevatorNum, floorNum);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
