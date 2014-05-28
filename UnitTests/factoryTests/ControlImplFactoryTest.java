package UnitTests.factoryTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pExceptions.NegativeCapacityException;
import pExceptions.NegativeElevatorException;
import pExceptions.NegativeFloorException;
import pFactories.ControlImplFactory;
import pImpls.ElevatorControlModuleImpl;
import pInterfaces.ControlModuleInterface;

/**
 *
 */
public class ControlImplFactoryTest {
    private int elevatorNum;
    private int floorNum;
    public ControlImplFactoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        elevatorNum = 1;
        floorNum = 2;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createElevatorController method, of class ControlImplFactory.
     */
    @Test
    public void testCreateElevatorController() {
        System.out.println("createElevatorController");
        ControlModuleInterface expResult = null;
        ControlModuleInterface result = ControlImplFactory.createElevatorController();
        assertFalse(expResult==result);
    }
    
}
