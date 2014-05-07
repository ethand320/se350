package pFactories;
import pImpls.ElevatorControlModuleImpl;
import pInterfaces.ControlModuleInterface;

public class ControlImplFactory
{
	public static ControlModuleInterface createElevatorController(int elevatorNum, int floorNum)
	{
		return new ElevatorControlModuleImpl(elevatorNum, floorNum);
	}
}
