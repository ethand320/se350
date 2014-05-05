package pImpls;

public class SimulationEnvironment
{
	private volatile static SimulationEnvironment instance;
	private SimulationEnvironment(int numFloors, int numElevators)
	{
		ElevatorControlModule.getInstance(numFloors, numElevators);
	}
	public static SimulationEnvironment getInstance(int numFloors, int numElevators)
	{
		synchronized(SimulationEnvironment.class)
		{
			if(instance == null)
			{
				synchronized(SimulationEnvironment.class)
				{
					instance = new SimulationEnvironment(numFloors, numElevators);
				}
			}
			return instance;
		}	
	}
}
