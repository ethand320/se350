package pFactories;
import pExceptions.NegativeCapacityException;
import pExceptions.NegativeElevatorException;
import pExceptions.NegativeFloorException;
import pImpls.ElevatorControlModuleImpl;
import pInterfaces.ControlModuleInterface;

/**
 * Factory class for creating various implementations of the ControlModule interface. As new implementations of the ControlModule interface
 * are created, a corresponding factory method should be added to this class.
 */
public class ControlImplFactory
{
	/**
	 * Public factory method for created a ControlModule implementation. This method can be modified or overloaded to accommodate different
	 * ControlModule implementations.
	 * 
	 * @return A new ControlModule implementation that owns the number of elevators and floors specified by elevatorNum and floorNum respectively.
	 * @throws NegativeFloorException if floorNum is less than 1.
	 * @throws NegativeCapacityException if the elevators that are created as part of initialization are passed a negative value for passenger capacity.
	 * @throws NegativeElevatorException
	 */
	public static ControlModuleInterface createElevatorController() throws NegativeFloorException, NegativeCapacityException, NegativeElevatorException
	{
		return new ElevatorControlModuleImpl();
	}
}
