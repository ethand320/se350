package pFactories;
import pExceptions.NegativeCapacityException;
import pExceptions.NegativeFloorException;
import pImpls.ElevatorControlModuleImpl;
import pInterfaces.ControlModuleInterface;

public class ControlImplFactory
{
	public static ControlModuleInterface createElevatorController(int elevatorNum, int floorNum) throws NegativeFloorException, NegativeCapacityException
	{
		return new ElevatorControlModuleImpl(elevatorNum, floorNum);
	}
}
