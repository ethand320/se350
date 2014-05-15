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
import pImpls.*;
import pInterfaces.*;

/**
 *
 * @author Stouny
 */
public class FloorTest {
    
    public FloorTest() {
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
     * Test of addPersonToFloor method, of class Floor.
     */
    @Test
    public void testAddPersonToFloor() {
        System.out.println("addPersonToFloor");
        Person inPerson = null;
        Floor instance = null;
        instance.addPersonToFloor(inPerson);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of summonElevator method, of class Floor.
     */
    @Test
    public void testSummonElevator() {
        System.out.println("summonElevator");
        Direction directionToGo = null;
        Floor instance = null;
        instance.summonElevator(directionToGo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Floor.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Floor instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeFromFloor method, of class Floor.
     */
    @Test
    public void testRemoveFromFloor() {
        System.out.println("removeFromFloor");
        ElevatorInterface elevatorToEnter = null;
        Direction directionToGo = null;
        Floor instance = null;
        instance.removeFromFloor(elevatorToEnter, directionToGo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
