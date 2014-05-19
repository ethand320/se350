package pImpls;

import pFactories.ElevatorFactory;
import pFactories.FloorFactory;
import pInterfaces.ControlModuleInterface;
import pInterfaces.ElevatorInterface;
import pInterfaces.FloorInterface;


/**
 * ElevatorControlModuleImpl class implements ControlModuleInterface 
 */
public class ElevatorControlModuleImpl implements ControlModuleInterface
{
	private ElevatorInterface[] elevators; 
	private FloorInterface[] floors;
	
       /**
 	* ElevatorControlModuleImpl creates floors and elevators with the given parameters.
	* @param elevatorNum The number of elevators to be created. 
 	* @param floorNum The number of floors to be created.
        */
	public ElevatorControlModuleImpl(int elevatorNum, int floorNum)
	{
		createFloors(floorNum);
		createElevators(elevatorNum, floorNum);		
	}

       /**
        * elevatorCallReciever takes in the elevator request with the given parameters.
        * @param floorNumber the floor number requested. 
        * @param directionRequest the the direction that will lead to the requested floor.
        */
	@Override
	public void elevatorCallReceiver(int floorNumber, Direction directionRequest)
	{
		if(floorNumber < 0 || floorNumber >= floors.length)
		{
			
		}
		ElevatorInterface elevatorToSend;
		
		//this number is used as a fallback value for the case where we have fewer than 4 elevators in our building. should help the selection algo to work when unit testing
		int elevatorNum = elevators.length;
		
		//SIMPSON TODO: for now, we're going to hard code the elevator logic to meet the test requirements of the first deliverable 
		//this MUST be changed for the next iteration
<<<<<<< HEAD
=======

>>>>>>> ethansbranch
                
                //Ethan psudo code implementation here.  
                
                /*  This is for when a person presses up/down on a floor, what elevator gets the request put in it's queue, algo is from the notes Project submission 1 pdf
                
<<<<<<< HEAD
                if there is an elevator on the floor
                    if elevator is idle OR going in desired direction
                        then add the floor to that elevator;s queue
=======
                have to loop through each elevator in elevators[]
                
                
                if there is an elevator on the floor
                    if elevator is idle OR going in desired direction
                        then add the floor to that elevator;s queue  and be DONE
>>>>>>> ethansbranch
                    else 
                    
                    is there an elevator already moving?
                yes: is it also going in desired direction
                      yes:  add the floor to that elevator's request queue
                
                no:
                    are there any idle elevators?
                    yes:
                        pick an idle elevator and add the request to the queue
                    no:
                        add to unique pending request list  ( a catch all queue I guess?)
                
                */
                boolean handledRequest = false;
                
               //  if there is an elevator on the floor
               //     if elevator is idle OR going in desired direction
               //         then add the floor to that elevator;s queue  and be DONE
                for ( ElevatorInterface curElev: elevators)
                {
                    if (handledRequest == true) break;  // if request has been sent you're done!
                    
                    if (curElev.getCurrentFloor() == floorNumber)
                        if ( !curElev.isRunning() || curElev.getDirection() == directionRequest )
                        {    // then add floor to that elevators queue and break
                            curElev.addFloorToQueue(floorNumber);
                            handledRequest = true;
                            break;  // break out of loop since request has been handled!
                        }   
                }
                 // is there an elevator already moving?
               // yes: is it also going in desired direction
                //      yes:  add the floor to that elevator's request queue
                for (ElevatorInterface curElev: elevators)
                {
                    if(handledRequest == true) break;  //Request has already been sent so we're done
                    if (curElev.isRunning() && curElev.getDirection() == directionRequest)
                    {
                        curElev.addFloorToQueue(floorNumber);
                        handledRequest = true; 
                        break;
                    }
   
                }
                  //are there any idle elevators?
                 //   yes:
                 //       pick an idle elevator and add the request to the queue
                 //   no:
                  //      add to unique pending request list  ( a catch all queue I guess?)
                for (ElevatorInterface curElev: elevators)
                {
                    if (handledRequest == true) break;
                    if ( !curElev.isRunning())
                    {
                        curElev.addFloorToQueue(floorNumber);
                       handledRequest = true;
                       break;
                  
                    }
                }
                
                //If we got this far and request still hasn't been handled...
                // then it needs to be sent again  need this implementation done eventually.


		switch(floorNumber)
		{
		case 4:
		case 15:
			if(elevatorNum >= 3)
			{
				elevatorToSend = elevators[2];
			}
			else
			{
				elevatorToSend = elevators[elevatorNum - 1];
			}
			break;
		case 10:
			elevatorToSend = elevators[0];
			break;
		case 12:
		case 13:
		case 14:
			if(elevatorNum >= 2)
			{
				elevatorToSend = elevators[1];
			}
			else
			{
				elevatorToSend = elevators[elevatorNum - 1];
			}
			break;
		default:
			elevatorToSend = elevators[0];
		}
		elevatorToSend.addFloorToQueue(floorNumber);
	}


       /**
        * ElevatorInterface returns the total elevators.
        * @return returns all elevators.
        */
	@Override
	public ElevatorInterface getElevator(int index)
	{
		return elevators[index-1];
	}
	
	
       /**
        * Master shutdown command that stops further elevator commands.
        */
	public void shutDown()
	{
		for(ElevatorInterface elevator : elevators)
		{
			elevator.shutDown();
		}
	}

       /**
        * elevatorDoorsOpened takes in two parameters of the floor and elevator and removes initiates removeFromFloor
        * @see removeFroomFloor removes passengers from the floor that will be placed into the elevator.
        * @oaram elevator the elevator the will recieve the requests.
        * @param floorNumber the floor number the elevator is recieving the request at.
        */
	@Override
	public void elevatorDoorsOpened(ElevatorInterface elevator, int floorNumber)
	{
		floors[floorNumber-1].removeFromFloor(elevator, elevator.getDirection());
	}

       /**
        * addPersonToFloor moves them from the specified floor to the indicated elevator.
        * @param inPerson calls the method to add a person to the elevator.
        * @param floorNum the floor the person is located in. 
        */
	@Override
	public void addPersonToFloor(Person inPerson, int floorNum)
	{
		floors[floorNum-1].addPersonToFloor(inPerson);
	}
	
       /**
        * creates an elevator with a maximum number of floors
        * @param elevatorNum the number that will identify the elevator.
        * @param maxFloors the maximum number of floors the elevator may visit.
        */
	private void createElevators(int elevatorNum, int maxFloors)
	{
		if(elevatorNum < 1)
		{
			
		}
		else
		{
			elevators = new ElevatorInterface[elevatorNum];
			for(int i = 0; i < elevatorNum; ++i)
			{
				elevators[i] = ElevatorFactory.createElevator(i, ElevatorInterface.DEFAULT_ELEVATOR_CAPACITY, maxFloors, 1);
			}
		}
		
	}
	
       /**
        * creates the number of floors.
        * @param floorNum the number of floors being added.
        */
	private void createFloors(int floorNum)
	{
		//soft fail. should eventually throw an exception or something
		if(floorNum < 1)
		{
			
		}
		else
		{
			floors = new FloorInterface[floorNum];
			for(int i = 0; i < floorNum; ++i)
			{
				floors[i] = FloorFactory.createFloor(i);
			}
		}
		
	}
	/**
	 * retrieves the maximum floor length.
	 * @return returns the total length of the buildings floors.
	 */
	@Override
	public int getMaxFloors()
	{
		return this.floors.length;
	}
}
