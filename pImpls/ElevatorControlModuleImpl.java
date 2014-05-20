package pImpls;

import pExceptions.NegativeCapacityException;
import pExceptions.NegativeElevatorException;
import pExceptions.NegativeFloorException;
import pExceptions.NullPassengerException;
import pFactories.ElevatorFactory;
import pFactories.FloorFactory;
import pInterfaces.ControlModuleInterface;
import pInterfaces.ElevatorInterface;
import pInterfaces.FloorInterface;


/**
 * An implementation of the ControlModule interface. Implements a specific elevator selection algorithm. 
 */
public class ElevatorControlModuleImpl implements ControlModuleInterface
{
	/**
	 * The collection of ElevatorInterface objects that this Control Module Implementation is responsible for
	 */
	private ElevatorInterface[] elevators; 
	
	/**
	 * The collection of FloorInterface objects that this Control Module Implementation is responsible for
	 */
	private FloorInterface[] floors;
	
   /*  Don't need this one anymore!
 	* ElevatorControlModuleImpl creates floors and elevators with the given parameters.
	* @param elevatorNum The number of elevators to be created. 
 	* @param floorNum The number of floors to be created.
    * @throws NegativeFloorException if floorNum is less than or equal to 0
    * @throws NegativeCapacityException if any of the ElevatorInterface objects would have a capacity less than 1
    * @throws NegativeElevatorException if elevatorNum is less than 1
    */

        
        /*
        //Old imp]lementation to take with paraemeters, getting rid of it
        public ElevatorControlModuleImpl(int elevatorNum, int floorNum) throws NegativeFloorException, NegativeCapacityException, NegativeElevatorException
	{
		createFloors(floorNum);
		createElevators(elevatorNum, floorNum);		
	}
        
        */
        
        
        // New ECM constructor to take xml data for floors/elevators
         public ElevatorControlModuleImpl(){
             //maybe need a factory actually, so only one instance is declared
             XmlParser dataFeed = new XmlParser();
             
             int elevatorNum =  XmlParser.getTotalElevatorNumber();
             int floorNum = XmlParser.getTotalFloorNumber();
             try
             {
             createFloors(floorNum);
             createElevators(elevatorNum, floorNum);
             }
             catch (Exception e) { e.printStackTrace(); }
             
             
             
             
             
         }

   /**
    * This function is called whenever a Person object summons an elevator from a given floor. This method will compute the best elevator to
    * send to that floor and add that floor to its request queue.
    * @param floorNumber the ZERO-BASED floor number where the request is originating from.
    * @param directionRequest the the direction that will lead to the requested floor.
    * @throws NegativeFloorException if the floorNumber is outside the bounds of the floor collection. NOTE: this method uses ZERO-BASED indexing as it is an internal method
    */
	@Override
	public void elevatorCallReceiver(int floorNumber, Direction directionRequest) throws NegativeFloorException
	{
		if(floorNumber < 0 || floorNumber >= floors.length)
		{
			throw new NegativeFloorException("The floor object that called this method has an invalid ID number! (floorNumber: " + floorNumber + ")");
		}
		ElevatorInterface elevatorToSend;
		
		//this number is used as a fall-back value for the case where we have fewer than 4 elevators in our building. should help the selection algo to work when unit testing
		int elevatorNum = elevators.length;
		
		//SIMPSON TODO: for now, we're going to hard code the elevator logic to meet the test requirements of the first deliverable 
		//this MUST be changed for the next iteration

                
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

         /*  This was old hard coding of which elevator to send for requests. logic above 'should' take care of that
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
		elevatorToSend.addFloorToQueue(floorNumber + 1);
                
                */
                
	}
               
                

   /**
    * Returns the elevator at the index specified.
    * NOTE: this function uses ONE-BASED indexing, which means that 0 is not a valid value
    * @return The ElevatorInterface object located at the specified ONE-BASED index
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
    * @param elevator the elevator the will receive the requests.
    * @param floorNumber the floor number the elevator is receiving the request at. Because this method is called by an Elevator object, it uses ZERO-BASED indexing
    * @throws NegativeFloorException 
    */
	@Override
	public void elevatorDoorsOpened(ElevatorInterface elevator, int floorNumber) throws NegativeFloorException
	{
		if(floorNumber < 0 || floorNumber >= this.floors.length)
		{
			throw new NegativeFloorException();
		}
		this.floors[floorNumber].removeFromFloor(elevator, elevator.getDirection());
	}

   /**
    * addPersonToFloor moves them from the specified floor to the indicated elevator.
    * @param inPerson calls the method to add a person to the elevator.
    * @param floorNum the floor the person is located in. NOTE: this method uses ONE-BASED indexing, which means that 0 does not correspond
    * to the metaphorical first floor
    * @throws NullPassengerException if inPerson is null
    * @throws NegativeFloorException if floorNum is less than or equal to 0
    */
	@Override
	public void addPersonToFloor(Person inPerson, int floorNum) throws NullPassengerException, NegativeFloorException
	{
		if(floorNum <= 0 || floorNum > this.floors.length)
		{
			throw new NegativeFloorException("Attempting to add a person to an invalid floor. Remember that this method uses ONE-BASED indexing instead of ZERO-BASED indexing (Floor number: " + (floorNum + 1) + ").");
		}
		this.floors[floorNum-1].addPersonToFloor(inPerson);
	}
	
   /**
    * Creates the collection of elevators that this ElevatorControlModuleImpl object is responsible for
    * @param elevatorNum the number that will identify the elevator.
    * @param maxFloors the maximum number of floors the elevator may visit.
    * @throws NegativeCapacityException if the capacity value of the elevator objects is less than 1
    * @throws NegativeElevatorException if elevatorNum is less than 1
    * @throws NegativeFloorException 
    */
	private void createElevators(int elevatorNum, int maxFloors) throws NegativeCapacityException, NegativeElevatorException, NegativeFloorException
	{
		if(elevatorNum < 1)
		{
			throw new NegativeElevatorException("Attempting to create zero or a negative number of Elevators! (elevatorNum: " + elevatorNum + ")");
		}
		
		elevators = new ElevatorInterface[elevatorNum];
		for(int i = 0; i < elevatorNum; ++i)
		{
			elevators[i] = ElevatorFactory.createElevator(i, ElevatorInterface.DEFAULT_ELEVATOR_CAPACITY, maxFloors, 1);
		}
	}
	
   /**
    * Creates the collection of floors that this ElevatorControlModuleImpl object is responsible for
    * @param floorNum the number of floors being added.
    * @throws NegativeFloorException if floorNum is less than 1
    */
	private void createFloors(int floorNum) throws NegativeFloorException
	{
		if(floorNum < 1)
		{
			throw new NegativeFloorException("Attempting to create a control module without any floors! (floorNum: " + floorNum + ")");
		}
		
		floors = new FloorInterface[floorNum];
		for(int i = 0; i < floorNum; ++i)
		{
			floors[i] = FloorFactory.createFloor(i);
		}
	}
	
	/**
	 * Accessor for the total number of floors being managed by this Control Module Implementation
	 * @return the number of floors being managed by this Control Module Implementation
	 */
	@Override
	public int getMaxFloors()
	{
		return this.floors.length;
	}

	/**
	 * Accessor for the number of elevators currently being managed by this Control Module Implementation
	 * @return the number of elevators currently being managed by this Control Module
	 */
	@Override
	public int getElevatorNum()
	{
		return this.elevators.length;
	}
}
