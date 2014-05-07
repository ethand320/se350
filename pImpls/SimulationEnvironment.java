package pImpls;

import pFactories.PersonFactory;
import pInterfaces.ElevatorInterface;

/* SimulationEnvironment class handles the creation of the elevator simulation given the number of floors and elevators.
*/
public class SimulationEnvironment
{
	private volatile static SimulationEnvironment instance;
	
	private SimulationEnvironment(int numFloors, int numElevators)
	{
		ElevatorControlModule.getInstance(numFloors, numElevators);
	}
	
	private SimulationEnvironment()
	{
		ElevatorControlModule.getInstance();
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
	
	public static SimulationEnvironment getInstance()
	{
		synchronized(SimulationEnvironment.class)
		{
			if(instance == null)
			{
				synchronized(SimulationEnvironment.class)
				{
					instance = new SimulationEnvironment();
				}
			}
			return instance;
		}	
	}
	
	public void startSimulation()
	{
		
		//PersonFactory.createPerson(11,0);
		try
		{
			// elevator 1 send to the 11th floor - as though someone pressed up on the 11th floor
	       
	        instance.getElevator(1).addFloorToQueue(11);
	        Thread.sleep(500);
	  
	        //  while ele 1 is moving  send elevator 2 to go to the 14th as tho someone pressed up on 14
	        instance.getElevator(2).addFloorToQueue(14);
	        Thread.sleep(500);


	        //  ^^ while elevator 2 is moving, send it another instruct ot go to the 13th direction up.  should stop there first then
	        // continue on to the 14th
	        instance.getElevator(2).addFloorToQueue(13);

	      	Thread.sleep(500);

	        instance.getElevator(2).addFloorToQueue(15);
	        // ^^ also while elevator 2 is moving , instruct it to go to the 15th floor (add 15th floor to its queue)
	        // should stop on all three floors
	        
	        
	        // wait for all elevators to complete travels (wait for them to go idle)  when they are idle they return to default floor
	        //timeout is 15000ms
	        Thread.sleep(15000);
	        
	        //then send elevator 3 to the 5th floor.
	        instance.getElevator(3).addFloorToQueue(5);
	        Thread.sleep(3000);
	        instance.getElevator(3).addFloorToQueue(16);
	        Thread.sleep(500);;
	        instance.getElevator(3).addFloorToQueue(1);
	        Thread.sleep(6000);
	        instance.getElevator(3).addFloorToQueue(2);
	        Thread.sleep(500);
	        
	        instance.getElevator(3).addFloorToQueue(5);
	        instance.getElevator(3).addFloorToQueue(3);
	        
	        Thread.sleep(60000);
	        
	        instance.stopSimluation();
	        
	        // once elevator 3 is at the 5th floor, send it to the 16th floor (button pressed from inside the elevator)
	        
	        //while going to the 16th floor, instruct it to go to the 1st floor],   REQUEST SHOULD GET IGNORED
	        
	        // once elevator 3 reaches the 16th floor send it down to 2 (inside button press) this will not be ignored
	        
	        
	        // while elevator 3 on its way down, add 5th and 3rd floors to the queue (inside button presses)  should now stop at each floor
	        
	        // wait for elevator to go idle, then timeout and return to defualt floor.
		}
		catch (InterruptedException e)
        {
            e.printStackTrace();
        }
		
	}

	public void stopSimluation()
	{
		ElevatorControlModule.getInstance().shutDown();
	}
	
	private ElevatorInterface getElevator(int i)
	{
		return ElevatorControlModule.getInstance().getElevator(i);
	}
	
}
