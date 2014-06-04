/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pImpls;

/**
 *
 * @author User
 */
public class DataLogger {
    
    private static double startTime;   // time object?
    private static double masterTime;   //needs to be a timestamp?
    
    
    
    
    
    public static void logPersonCreation(Person name){
        
    //    masterTime = current system time - start time
        System.out.println( masterTime + ":     Person " + name.getID() + "was created ");
        
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
        
        
    }
    
    
    
    
}
