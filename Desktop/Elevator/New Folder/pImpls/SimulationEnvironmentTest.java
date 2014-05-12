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
public class SimulationEnvironmentTest {
    
    public SimulationEnvironmentTest() {
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
     * Test of getInstance method, of class SimulationEnvironment.
     */
    @Test
    public void testGetInstance_int_int() {
        System.out.println("getInstance");
        int numFloors = 0;
        int numElevators = 0;
        SimulationEnvironment expResult = null;
        SimulationEnvironment result = SimulationEnvironment.getInstance(numFloors, numElevators);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInstance method, of class SimulationEnvironment.
     */
    @Test
    public void testGetInstance_0args() {
        System.out.println("getInstance");
        SimulationEnvironment expResult = null;
        SimulationEnvironment result = SimulationEnvironment.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startSimulation method, of class SimulationEnvironment.
     */
    @Test
    public void testStartSimulation() {
        System.out.println("startSimulation");
        SimulationEnvironment instance = null;
        instance.startSimulation();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
