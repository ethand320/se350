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
import pImpls.Person;

/**
 *
 * @author Stouny
 */
public class PersonFactoryTest {
    
    public PersonFactoryTest() {
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
     * Test of createPerson method, of class PersonFactory.
     */
    @Test
    public void testCreatePerson() {
        System.out.println("createPerson");
        int startFloor = 0;
        int destinationFloor = 0;
        Person expResult = null;
        Person result = PersonFactory.createPerson(startFloor, destinationFloor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
