/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pImpls;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stouny
 */
public class ElevatorTest {
    
    public ElevatorTest() {
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
     * Test of addFloorToQueue method, of class Elevator.
     */
    @Test
    public void testAddFloorToQueue() {
        System.out.println("addFloorToQueue");
        int floorNum = 0;
        Elevator instance = null;
        instance.addFloorToQueue(floorNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPassenger method, of class Elevator.
     */
    @Test
    public void testAddPassenger() {
        System.out.println("addPassenger");
        Person inPassenger = null;
        Elevator instance = null;
        instance.addPassenger(inPassenger);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPassengers method, of class Elevator.
     */
    @Test
    public void testAddPassengers() {
        System.out.println("addPassengers");
        Person[] inPeople = null;
        Elevator instance = null;
        instance.addPassengers(inPeople);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of openDoors method, of class Elevator.
     */
    @Test
    public void testOpenDoors() {
        System.out.println("openDoors");
        Elevator instance = null;
        instance.openDoors();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeDoors method, of class Elevator.
     */
    @Test
    public void testCloseDoors() {
        System.out.println("closeDoors");
        Elevator instance = null;
        instance.closeDoors();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDirection method, of class Elevator.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        Elevator instance = null;
        Direction expResult = null;
        Direction result = instance.getDirection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePassenger method, of class Elevator.
     */
    @Test
    public void testRemovePassenger() {
        System.out.println("removePassenger");
        Person inPassenger = null;
        Elevator instance = null;
        instance.removePassenger(inPassenger);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePassengers method, of class Elevator.
     */
    @Test
    public void testRemovePassengers() {
        System.out.println("removePassengers");
        ArrayList<Person> inPeople = null;
        Elevator instance = null;
        instance.removePassengers(inPeople);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCapacity method, of class Elevator.
     */
    @Test
    public void testGetCapacity() {
        System.out.println("getCapacity");
        Elevator instance = null;
        int expResult = 0;
        int result = instance.getCapacity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassengers method, of class Elevator.
     */
    @Test
    public void testGetPassengers() {
        System.out.println("getPassengers");
        Elevator instance = null;
        ArrayList<Person> expResult = null;
        ArrayList<Person> result = instance.getPassengers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getElevatorId method, of class Elevator.
     */
    @Test
    public void testGetElevatorId() {
        System.out.println("getElevatorId");
        Elevator instance = null;
        int expResult = 0;
        int result = instance.getElevatorId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shutDown method, of class Elevator.
     */
    @Test
    public void testShutDown() {
        System.out.println("shutDown");
        Elevator instance = null;
        instance.shutDown();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class Elevator.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        Elevator instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
