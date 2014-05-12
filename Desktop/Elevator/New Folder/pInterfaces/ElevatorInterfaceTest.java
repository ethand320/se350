/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pInterfaces;

import java.util.ArrayList;
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
public class ElevatorInterfaceTest {
    
    public ElevatorInterfaceTest() {
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
     * Test of addFloorToQueue method, of class ElevatorInterface.
     */
    @Test
    public void testAddFloorToQueue() {
        System.out.println("addFloorToQueue");
        int floorNum = 0;
        ElevatorInterface instance = new ElevatorInterfaceImpl();
        instance.addFloorToQueue(floorNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPassenger method, of class ElevatorInterface.
     */
    @Test
    public void testAddPassenger() {
        System.out.println("addPassenger");
        Person inPassenger = null;
        ElevatorInterface instance = new ElevatorInterfaceImpl();
        instance.addPassenger(inPassenger);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPassengers method, of class ElevatorInterface.
     */
    @Test
    public void testAddPassengers() {
        System.out.println("addPassengers");
        Person[] inPeople = null;
        ElevatorInterface instance = new ElevatorInterfaceImpl();
        instance.addPassengers(inPeople);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of openDoors method, of class ElevatorInterface.
     */
    @Test
    public void testOpenDoors() {
        System.out.println("openDoors");
        ElevatorInterface instance = new ElevatorInterfaceImpl();
        instance.openDoors();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeDoors method, of class ElevatorInterface.
     */
    @Test
    public void testCloseDoors() {
        System.out.println("closeDoors");
        ElevatorInterface instance = new ElevatorInterfaceImpl();
        instance.closeDoors();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePassenger method, of class ElevatorInterface.
     */
    @Test
    public void testRemovePassenger() {
        System.out.println("removePassenger");
        Person inPassenger = null;
        ElevatorInterface instance = new ElevatorInterfaceImpl();
        instance.removePassenger(inPassenger);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePassengers method, of class ElevatorInterface.
     */
    @Test
    public void testRemovePassengers() {
        System.out.println("removePassengers");
        ArrayList<Person> inPeople = null;
        ElevatorInterface instance = new ElevatorInterfaceImpl();
        instance.removePassengers(inPeople);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCapacity method, of class ElevatorInterface.
     */
    @Test
    public void testGetCapacity() {
        System.out.println("getCapacity");
        ElevatorInterface instance = new ElevatorInterfaceImpl();
        int expResult = 0;
        int result = instance.getCapacity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassengers method, of class ElevatorInterface.
     */
    @Test
    public void testGetPassengers() {
        System.out.println("getPassengers");
        ElevatorInterface instance = new ElevatorInterfaceImpl();
        ArrayList<Person> expResult = null;
        ArrayList<Person> result = instance.getPassengers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getElevatorId method, of class ElevatorInterface.
     */
    @Test
    public void testGetElevatorId() {
        System.out.println("getElevatorId");
        ElevatorInterface instance = new ElevatorInterfaceImpl();
        int expResult = 0;
        int result = instance.getElevatorId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDirection method, of class ElevatorInterface.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        ElevatorInterface instance = new ElevatorInterfaceImpl();
        Direction expResult = null;
        Direction result = instance.getDirection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shutDown method, of class ElevatorInterface.
     */
    @Test
    public void testShutDown() {
        System.out.println("shutDown");
        ElevatorInterface instance = new ElevatorInterfaceImpl();
        instance.shutDown();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ElevatorInterfaceImpl implements ElevatorInterface {

        public void addFloorToQueue(int floorNum) {
        }

        public void addPassenger(Person inPassenger) {
        }

        public void addPassengers(Person[] inPeople) {
        }

        public void openDoors() {
        }

        public void closeDoors() {
        }

        public void removePassenger(Person inPassenger) {
        }

        public void removePassengers(ArrayList<Person> inPeople) {
        }

        public int getCapacity() {
            return 0;
        }

        public ArrayList<Person> getPassengers() {
            return null;
        }

        public int getElevatorId() {
            return 0;
        }

        public Direction getDirection() {
            return null;
        }

        public void shutDown() {
        }
    }
    
}
