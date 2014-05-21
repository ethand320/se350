import pImpls.SimulationEnvironment;
public class Main 
{
	public static void main(String[] args)
	{
        try
        {
            SimulationEnvironment.getInstance().startSimulation();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
}


