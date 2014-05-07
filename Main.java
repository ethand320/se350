import pImpls.SimulationEnvironment;
//test ocmment here 

//branch conflict here!!!
//blah blahconflicttt

//fuck bitches get money
public class Main 
{
    // sudo code for the exact implemenation we need] to turn in.
    
    
    //Create a Building with “16” floors and “4” elevators.

   
  
    

    SimulationEnvironment.getInstance(16,4);
 

    
    // elevator 1 send to the 11th floor - as though someone pressed up on the 11th floor
    // 500ms per floor sleep time
    SimulationEnvironment.getInstance().getElevator(1).pressButton(11);
    Thread.sleep(500);
    // open doors upon arrival
    SimulationEnvironment.getInstance().getElevator(1).openDoors();
     
    // wait another 500ms then close (another 500ms)
    Thread.sleep(500);
    SimulationEnvironment.getInstance().getElevator(1).closeDoors();
    Thread.sleep(500);
    //  while ele 1 is moving  send elevator 2 to go to the 14th as tho someone pressed up on 14
    SimulationEnvironment.getInstance().getElevator(2).pressButton(14);
    //upon arrival open doors, wait then close doors

    
    

    SimulationEnvironment.getInstance().getElevator(2).openDoors();
    Thread.sleep(500);
    SimulationEnvironment.getInstance().getElevator(2).closeDoors();    

    //  ^^ while elevator 2 is moving, send it another instruct ot go to the 13th direction up.  should stop there first then
    // continue on to the 14th
    SimulationEnvironment.getInstance().getElevator(2).addFloorToQueue(13);
    SimulationEnvironment.getInstance().getElevator(2).addFloorToQueue(14);
    SimulationEnvironment.getInstance().getElevator(2).addFloorToQueue(15);
    // ^^ also while elevator 2 is moving , instruct it to go to the 15th floor (add 15th floor to its queue)
    // should stop on all three floors
    
    // wait for all elevators to complete travels (wait for them to go idle)  when they are idle they return to default floor
    //timeout is 15000ms
    Thread.sleep(15000);
    
    //then send elevator 3 to the 5th floor.
    
    // once elevator 3 is at the 5th floor, send it to the 16th floor (button pressed from inside the elevator)
    
    //while going to the 16th floor, instruct it to go to the 1st floor],   REQUEST SHOULD GET IGNORED
    
    // once elevator 3 reaches the 16th floor send it down to 2 (inside button press) this will not be ignored
    
    
    // while elevator 3 on its way down, add 5th and 3rd floors to the queue (inside button presses)  should now stop at each floor
    
    // wait for elevator to go idle, then timeout and return to defualt floor.
    
    
    
    
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


