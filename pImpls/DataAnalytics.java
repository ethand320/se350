/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pImpls;

import java.util.ArrayList;

/**
 *
 * @author User
 */



public class DataAnalytics 
{
	private static class PersonDataEntry
	{
		/**
		 * The person's ID number
		 */
		public int personID;

		/**
		 * The person's start floor. Used for analytics related to inter-floor travel and total wait time
		 */
		public int personStartFloor;

		/**
		 * The person's destination floor. Used for analytics related to inter-floor travel and total wait time
		 */
		public int personEndFloor;

		/**
		 * The system time at which this object was created, measured in milliseconds
		 */
		public long creationTimeStamp;

		/**
		 * The system time at which this object enters an elevator, measured in milliseconds
		 */
		public long enterElevTimeStamp;

		/**
		 * The system time at which this object leaves the elevator and arrives at their destination floor, measured in milliseconds
		 */
		public long leaveElevTimeStamp;

		public PersonDataEntry()
		{

		}
	}

	private static ArrayList<PersonDataEntry> dataEntries = new ArrayList<PersonDataEntry>();

	public static void personCreationTimeStamp(int inPersonId, int inStartFloor, int inDestFloor)
	{
		PersonDataEntry newEntry = new PersonDataEntry();
    	newEntry.personID = inPersonId;
    	newEntry.personStartFloor = inStartFloor;
    	newEntry.personEndFloor = inDestFloor;
    	newEntry.creationTimeStamp = System.currentTimeMillis();
    	dataEntries.add(newEntry);
	}
	
	public static void elevAddTimeStamp(int inPersonId, int inElevatorId)
	{
		dataEntries.get(inPersonId).enterElevTimeStamp = System.currentTimeMillis();
	}

	public static void elevLeaveTimeStamp(int inPersonId, int inElevatorId)
	{
		dataEntries.get(inPersonId).leaveElevTimeStamp = System.currentTimeMillis();
	}
        
        public static void printFloorWaitTimeTable(){
            
            //Build array of floors
            int totalFloors = XmlParser.getTotalFloorNumber();
            int[] floorArray = new int[totalFloors];
            
            
            
            for (int i = 0; i <= totalFloors; i++)
                floorArray[i] = i;
            
            //table array for wait times
            long[][] floorWaitTimeList = new long[totalFloors][dataEntries.size()];
            
          
            //This is the loop to get all data  into the two dimensional array
            //First dimension is array by floor number
            // second dimension is each floor's array of total wait times (so averages can be calculated)
            
            int increm = 0;
            for (PersonDataEntry person : dataEntries)
            {
               floorWaitTimeList[person.personStartFloor][increm] = (person.creationTimeStamp - person.enterElevTimeStamp )/1000;
               increm++;
        
            }
            
            //Nested for loops to caclulate min/max/average
            
            long[][] floorMinMaxAvgArray = new long[totalFloors][3];
            /*  Array sturcture
            [floor index 0]   [ average, min wait time, max wait time]
            [floor index 1]   [ average, min wait time, max wait time]
            ..... for as many rows as there are floors
           
            
            */
            long floorMinWait = 10000000;  // huge number so first compares to be less
            long floorMaxWait = 0;
            long floorAvgWait = 0;
            long floorTotalPeople = 0;
            for (int i = 0; i < totalFloors; i++)
             
            {
                for (int j = 0; j < dataEntries.size(); j++)
                {
                    if (floorWaitTimeList[i][j] < floorMinWait) 
                    {
                        floorMinWait = floorWaitTimeList[i][j];
                        floorAvgWait += floorWaitTimeList[i][j];
                        floorTotalPeople++;
                        
                        if (floorWaitTimeList[i][j] >  floorMaxWait) floorMaxWait = floorWaitTimeList[i][j];
                        
                    }
                    else if (floorWaitTimeList[i][j] >  floorMaxWait)   // check if the cell is greater than the floors max wait time 
                    {
                        floorMaxWait = floorWaitTimeList[i][j];  // it is greater so make it the max
                        floorAvgWait += floorWaitTimeList[i][j];  // add the time also to the total average calculation
                        floorTotalPeople++;
                    }
                    else { floorAvgWait += floorWaitTimeList[i][j]; 
                            floorTotalPeople++;
                        }
                    
                }
                // Now we have looped through ever cell in the row, so time to do calculations and put final answers into the real table to print ( floorMinMaxAvgArray  )
                floorMinMaxAvgArray[i][0] = floorAvgWait/floorTotalPeople;  //calc for average wait time  all sum of wait times /  total people on the floor
                floorMinMaxAvgArray[i][1] = floorMinWait;
                floorMinMaxAvgArray[i][2] = floorMaxWait;
                
            }
            
           //Implementation to print the table we consturcted (floorMinMaxAvgArray)  below:
                
            for (int i = 0; i < totalFloors; i++)
                for (int j =0; j < 3; j++)
                {
                    System.out.printf("%5d ", floorMinMaxAvgArray[i][j]);
                    
                }
            
        }
}