/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pInterfaces;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pImpls.Direction;
import pImpls.Person;

/**
 *
 * @author Stouny
 */
public class FloorInterfaceTest {
    
    public FloorInterfaceTest() {
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
     * Test of summonElevator method, of class FloorInterface.
     */
    @Test
    public void testSummonElevator() {
        System.out.println("summonElevator");
        Direction directionToGo = null;
        FloorInterface instance = new FloorInterfaceImpl();
        instance.summonElevator(directionToGo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class FloorInterface.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        FloorInterface instance = new FloorInterfaceImpl();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeFromFloor method, of class FloorInterface.
     */
    @Test
    public void testRemoveFromFloor() {
        System.out.println("removeFromFloor");
        ElevatorInterface elevatorToEnter = null;
        Direction directionToGo = null;
        FloorInterface instance = new FloorInterfaceImpl();
        instance.removeFromFloor(elevatorToEnter, directionToGo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPersonToFloor method, of class FloorInterface.
     */
    @Test
    public void testAddPersonToFloor() {
        System.out.println("addPersonToFloor");
        Person inPerson = null;
        FloorInterface instance = new FloorInterfaceImpl();
        instance.addPersonToFloor(inPerson);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class FloorInterfaceImpl implements FloorInterface {

        public void summonElevator(Direction directionToGo) {
        }

        public int getId() {
            return 0;
        }

        public void removeFromFloor(ElevatorInterface elevatorToEnter, Direction directionToGo) {
        }

        public void addPersonToFloor(Person inPerson) {
        }
    }
    
}
