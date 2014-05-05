package pImpls;

import pInterfaces.ControlModuleInterface;

public class ElevatorControlModule implements ControlModuleInterface 
{
	private ElevatorControlModuleImpl delegate;


	@Override
	public void elevatorCallReceiver() {
		// TODO Auto-generated method stub
		delegate.elevatorCallReceiver();
	}
	
	
}
