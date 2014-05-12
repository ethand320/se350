/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pImpls;

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
public class PersonTest {
    
    public PersonTest() {
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
     * Test of getDestinationFloor method, of class Person.
     */
    @Test
    public void testGetDestinationFloor() {
        System.out.println("getDestinationFloor");
        Person instance = null;
        int expResult = 0;
        int result = instance.getDestinationFloor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentFloor method, of class Person.
     */
    @Test
    public void testGetCurrentFloor() {
        System.out.println("getCurrentFloor");
        Person instance = null;
        int expResult = 0;
        int result = instance.getCurrentFloor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDestinationFloor method, of class Person.
     */
    @Test
    public void testSetDestinationFloor() {
        System.out.println("setDestinationFloor");
        int inDest = 0;
        Person instance = null;
        instance.setDestinationFloor(inDest);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
