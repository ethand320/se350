/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UnitTests.factoryTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pExceptions.NegativeFloorException;
import pFactories.PersonFactory;
import pImpls.Person;

/**
 *
 */
public class PersonFactoryTest
{

	public PersonFactoryTest()
	{
	}

	@BeforeClass
	public static void setUpClass()
	{
	}

	@AfterClass
	public static void tearDownClass()
	{
	}

	@Before
	public void setUp()
	{
	}

	@After
	public void tearDown()
	{
	}

	/**
	 * Test of createPerson method, of class PersonFactory.
	 */
	@Test
	public void testCreatePerson()
	{
		System.out.println("createPerson");
		int startFloor = 1;
		int destinationFloor = 5;
		int successID = 0;
		int failID = 1;
		Person expResult = null;
		Person result = null;
		Person failResult = null;
		try
		{
			expResult = new Person(successID, startFloor, destinationFloor);
			result = PersonFactory.createPerson(successID, startFloor, destinationFloor);
			failResult = PersonFactory.createPerson(failID, startFloor+1,destinationFloor+1);     
		}
		catch (NegativeFloorException e)
		{
			fail(e.getMessage());
		}
		assertTrue(expResult.getCurrentFloor() == result.getCurrentFloor());
		assertTrue(expResult.getDestinationFloor() == result.getDestinationFloor());
		assertTrue(expResult.getID() == result.getID());
		assertFalse(failResult.getCurrentFloor() == result.getCurrentFloor());
		assertFalse(failResult.getDestinationFloor() == result.getDestinationFloor());
		assertFalse(failResult.getID() == result.getID());
		assertNotNull(result.getCurrentFloor());
		assertNotNull(result.getDestinationFloor());
	}

}
