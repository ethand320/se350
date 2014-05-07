package pInterfaces;

import pImpls.Direction;

public interface ControlModuleInterface
{
	public void elevatorCallReceiver(int floorNumber, Direction directionRequest);
	public ElevatorInterface getElevator(int index);
	public void shutDown();
}
//This class will be fleshed out for the second code deliverable
//this method should facilitate elevator selection and implement
//the strategy pattern via the classes ElevatorControlModule and
//ElevatorControlModuleImpl which should both implement this interface