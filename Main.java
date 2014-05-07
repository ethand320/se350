import pImpls.SimulationEnvironment;
//test ocmment here 

//branch conflict here!!!
//blah blahconflicttt

//fuck bitches get money
public class Main 
{
    // sudo code for the exact implemenation we need] to turn in.
    
    
    //Create a Building with “16” floors and “4” elevators.
    
    
    // elevator 1 send to the 11th floor - as though someone pressed up on the 11th floor
    // 500ms per floor sleep time
    
    // open doors upon arrival
    
    // wait another 500ms then close (another 500ms)
    
    //  while ele 1 is moving  send elevator 2 to go to the 14th as tho someone pressed up on 14
    
    //upon arrival open doors, wait then close doors
    
    //  ^^ while elevator 2 is moving, send it another instruct ot go to the 13th direction up.  should stop there first then
    // continue on to the 14th
    
    //
    
    
    
    
	public static void main(String[] args)
	{
        try
        {
            SimulationEnvironment.getInstance(15,6);
            System.out.println("SimulationEnvironment created with 15 floors and 6 elevators.");
            Thread.sleep(1000);
            SimulationEnvironment.getInstance().getElevator(1).pressButton(5);
            Thread.sleep(10000);
            SimulationEnvironment.getInstance().getElevator(2).pressButton(14);
            SimulationEnvironment.getInstance().getElevator(2).pressButton(13);
            Thread.sleep(20000);
            SimulationEnvironment.getInstance().getElevator(1).pressButton(7); //since elevator has timed out, this should go through
            Thread.sleep(10000);
            SimulationEnvironment.getInstance().getElevator(1).pressButton(10);
            Thread.sleep(4000);
            SimulationEnvironment.getInstance().getElevator(1).pressButton(2);
            Thread.sleep(60000);
            System.out.println("Elevators are now shutting down");
            for (int i = 1; i <= 6; i++)
            {
                SimulationEnvironment.getInstance().getElevator(i).shutDownElevator();
            }
            System.out.println("Elevators have shut down");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
}


