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
public class ControlModuleInterfaceTest {
    
    public ControlModuleInterfaceTest() {
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
     * Test of elevatorCallReceiver method, of class ControlModuleInterface.
     */
    @Test
    public void testElevatorCallReceiver() {
        System.out.println("elevatorCallReceiver");
        int floorNumber = 0;
        Direction directionRequest = null;
        ControlModuleInterface instance = new ControlModuleInterfaceImpl();
        instance.elevatorCallReceiver(floorNumber, directionRequest);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getElevator method, of class ControlModuleInterface.
     */
    @Test
    public void testGetElevator() {
        System.out.println("getElevator");
        int index = 0;
        ControlModuleInterface instance = new ControlModuleInterfaceImpl();
        ElevatorInterface expResult = null;
        ElevatorInterface result = instance.getElevator(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shutDown method, of class ControlModuleInterface.
     */
    @Test
    public void testShutDown() {
        System.out.println("shutDown");
        ControlModuleInterface instance = new ControlModuleInterfaceImpl();
        instance.shutDown();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of elevatorDoorsOpened method, of class ControlModuleInterface.
     */
    @Test
    public void testElevatorDoorsOpened() {
        System.out.println("elevatorDoorsOpened");
        ElevatorInterface elevator = null;
        int floorNumber = 0;
        ControlModuleInterface instance = new ControlModuleInterfaceImpl();
        instance.elevatorDoorsOpened(elevator, floorNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPersonToFloor method, of class ControlModuleInterface.
     */
    @Test
    public void testAddPersonToFloor() {
        System.out.println("addPersonToFloor");
        Person inPerson = null;
        int floorNum = 0;
        ControlModuleInterface instance = new ControlModuleInterfaceImpl();
        instance.addPersonToFloor(inPerson, floorNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ControlModuleInterfaceImpl implements ControlModuleInterface {

        public void elevatorCallReceiver(int floorNumber, Direction directionRequest) {
        }

        public ElevatorInterface getElevator(int index) {
            return null;
        }

        public void shutDown() {
        }

        public void elevatorDoorsOpened(ElevatorInterface elevator, int floorNumber) {
        }

        public void addPersonToFloor(Person inPerson, int floorNum) {
        }
    }
    
}
