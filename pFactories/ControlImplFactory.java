package pFactories;
import pExceptions.NegativeCapacityException;
import pExceptions.NegativeElevatorException;
import pExceptions.NegativeFloorException;
import pImpls.ElevatorControlModuleImpl;
import pInterfaces.ControlModuleInterface;

public class ControlImplFactory
{
	public static ControlModuleInterface createElevatorController(int elevatorNum, int floorNum) throws NegativeFloorException, NegativeCapacityException, NegativeElevatorException
	{
		return new ElevatorControlModuleImpl(elevatorNum, floorNum);
	}
}
