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

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.HashMap;


public class XmlParser {
  
    private static File xmlFile;
    private static HashMap inputHash;
    
    public XmlParser(){
        
      //  this.xmlFile = new File("src/xmlInputs.xml");
      //  this.getInputs();
       
    }
    
    
    public static void getInputs(){ 
       if (inputHash == null)
         inputHash = new HashMap();
    
    try{    
        
        //XML docuement initialization for parsing
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         xmlFile = new File("xmlInputs.xml");
         
         Document doc = dBuilder.parse(xmlFile);

         NodeList nodeList = doc.getElementsByTagName("Input");

       //  Looping through all of the nodes under "Input" tags

             for (int temp = 0; temp <nodeList.getLength(); temp++ ){
                 Node nNode = nodeList.item(temp);
                 Element eElement = (Element) nNode;
               //Adding all the elements values to the hashmap
                System.out.println( eElement.getElementsByTagName("floors").item(0).getTextContent());
                
                 inputHash.put("floors", eElement.getElementsByTagName("floors").item(0).getTextContent());
                 inputHash.put("elevators", eElement.getElementsByTagName("elevators").item(0).getTextContent());
                 inputHash.put("elevTravelTime", eElement.getElementsByTagName("elevTravelTime").item(0).getTextContent());
                 inputHash.put("elevDoorTime", eElement.getElementsByTagName("elevDoorTime").item(0).getTextContent());
                 inputHash.put("peoplePerMin", eElement.getElementsByTagName("peoplePerMin").item(0).getTextContent());


             }



         } 
    catch (Exception e) { e.printStackTrace(); }
    
    
        
        
    }
    
    public static int getTotalElevatorNumber(){
       if (inputHash == null)
           getInputs();
       
        Integer elevators = Integer.parseInt((String)inputHash.get("elevators"));
        
        return  elevators ;
        
    }

    public static int getTotalFloorNumber(){
        if (inputHash == null)
           getInputs();
        
        Integer floors = Integer.parseInt( (String) inputHash.get("floors"));
        return floors;
        
    }
    public static double getElevTravelTime(){
      if (inputHash == null)
           getInputs();
      
        Double time = (Double) inputHash.get("elevTravelTime");
        return time;
    }
      
    public static int getElevDoorTime(){
        if (inputHash == null)
           getInputs();
        
        Integer doorTime = (Integer) inputHash.get("elevDoorTime");
        return doorTime;
        
    }
   
    public static double getPeoplePerMin(){
        if (inputHash == null)
           getInputs();
        
        Double peoplePerMin = (Double) inputHash.get("peoplePerMin");
        return peoplePerMin;
    }
        
        
        
    
}
