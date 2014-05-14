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
import pInterfaces.ElevatorInterface;

/**
 *
 * @author Stouny
 */
public class ElevatorFactoryTest {
    
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
        int elevatorId = 0;
        int capacity = 0;
        int maxFloors = 0;
        int minFloors = 0;
        ElevatorInterface expResult = null;
        ElevatorInterface result = ElevatorFactory.createElevator(elevatorId, capacity, maxFloors, minFloors);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
