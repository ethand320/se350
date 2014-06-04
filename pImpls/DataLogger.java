/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pImpls;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;


/**
 *
 * @author User
 */
public class DataLogger {
    
    private static long startTime;   // time object?
    private static long masterTime;   //needs to be a timestamp?
    
    
    //needs to be called at beginning of simulation.
    
    public static void setStartTime(){
        
        startTime = System.currentTimeMillis();
        
        
       // Calendar cal = Calendar.getInstance();
       // SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
       // System.out.println("Master time is set to " + sdf.format(cal.getTime()) );
        
        
    }
    
    
    public static String printTimeStamp()
    {
       
        //masterTime = current system time - start time
        
        masterTime = System.currentTimeMillis() - startTime;
        System.out.println("differnce of times in millis is " + masterTime);
        
        SimpleDateFormat sdf = new SimpleDateFormat("00:mm:ss");
        Date date = new Date(masterTime);
        
        
        
        return sdf.format(date);
        
        //return timestamp to be used in all below methods
        
    }
    
    
    public static void logPersonCreation(Person name){
        
    //    masterTime = current system time - start time
        System.out.println( masterTime + ":     Person " + name.getID() + "was created on floor " + name.getCurrentFloor() + " "
                + " heading to floor " + name.getDestinationFloor());
        
        
        // some logic to keep track of person that was created here
    }
    
    
    //when a perosn presses up/down on a floor  (right after they are created)
   
    public static void logFloorRequest(Person name, Direction dir, Floor floor){
        
        System.out.println("Person "+ name.getID() + "presses " + dir + " on Floor " + floor );
        
        
    }
    
    // when an elevator is assigned an up/down request from a floor
    public static void logElevatorDirectionRequest(Elevator elev, Floor floor, Direction dir){
        
        
      //  masterTime = systemtime - start time
        
        System.out.println(masterTime + "     Elevator " + elev.getElevatorId() + " going to floor" + floor.getId() + " for " + dir + " request");
        // also need to print out how many floor requests and how many rider requests??
        
        
    }
    
    //when a person inside an elevator presses a floor number button
    
    public static void logPersonElevatorFloorRequest(Elevator elev, Floor floor)
    {
        System.out.println("Elevator " + elev.getElevatorId() + " Rider Request made for Floor " + floor.getId() );
        
        
    }
    // When an elevator moves from one floor to another,
    public static void logElevatorMoving(Elevator elev, Floor to, Floor from) {
        
        System.out.println("Elevator " + elev.getElevatorId() + "Moving from " + from + "to floor " + to);
        
        
        
    }
    
    //when elevator arrives at a floor with an UP/DOWN request
    
    public static void logElevatorArriveAtRequestedFloor(Elevator elev, int floor){
        
        System.out.println(printTimeStamp() + ":     Elevator " + elev.getElevatorId() + " has arrived at floor " + floor);
        
    }
    
    
    //when elevator doors open
    public static void logElevatorOpenDoors(Elevator elev){
        
        System.out.println(printTimeStamp() + ":     Elevator " + elev.getElevatorId() + "opening doors");
                
    }
    
    
    // when elevator doors close
     public static void logElevatorCloseDoors(Elevator elev){
        
        System.out.println(printTimeStamp() + ":     Elevator " + elev.getElevatorId() + "closing doors");
                
    }
     
     //when a person enters an elevator
     // time person ID entered elevator elevID   [array of all riders]
     
     //When a person leaves an elevator
     // time person ID left elevator elevID   [array of all riders]
     
     //when person enters a floor
   //  logPersonAddToFloor(inperson, this, this.getWaitingPeople());
     
     public static void logPersonAddToFloor(Person p, Floor floor, ArrayList<Person> peopleList)
     {
         System.out.println(printTimeStamp() + ":     Person " + p.getID() + " added to floor " + floor.getId() + displayArrayList(peopleList) );
     }
     //time person persID has entered floor floorNum
     
     //when person leaves a floor
     // itme person pers]ID has left floor floorNum
     
     public static String displayArrayList(ArrayList<Person> list)
     {
         String myString = "[ ";
         for (Person person: list )
                 
         {
             myString += (Integer.toString(person.getID()) + " :");
         }
         myString += "]";
         
         return myString;
     }
     
}
