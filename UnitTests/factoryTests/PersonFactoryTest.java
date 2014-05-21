/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UnitTests.factoryTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import pExceptions.NegativeFloorException;
import pFactories.PersonFactory;
import pImpls.Person;

/**
 *
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
         SimulationEnvironment.FLOOR_NUM = 10;
        Person person = new Person(1,5);
        int startFloor = 1;
        int destinationFloor = 5;
        Person expResult = null;
        Person result = null;
        Person failresult = null;
        Person failboundry = null;
		try
		{
			result = PersonFactory.createPerson(startFloor, destinationFloor);
                        expResult = new Person(startFloor, destinationFloor);
                        failresult = PersonFactory.createPerson(startFloor+1,destinationFloor+1);
                       
		}
		catch (NegativeFloorException e)
		{
			fail(e.getMessage());
		}
        assertEquals(expResult.getCurrentFloor(), result.getCurrentFloor());
        assertEquals(expResult.getDestinationFloor(),result.getDestinationFloor());
        assertFalse(failresult.getCurrentFloor()==result.getCurrentFloor());
        assertFalse(failresult.getDestinationFloor()==result.getDestinationFloor());
    }
    
}
