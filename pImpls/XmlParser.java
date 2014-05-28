package pImpls;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XmlParser
{
  
    private static File xmlFile;
    private static volatile HashMap<String, Integer> inputHash;   
    
    public static void getInputs()
    {
    	synchronized(XmlParser.class)
    	{
    		if (inputHash == null)
        	{
        		inputHash = new HashMap<String, Integer>();
  
				try
				{
        			//XML document initialization for parsing
        			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        			DocumentBuilder dBuilder;

					dBuilder = dbFactory.newDocumentBuilder();
					
        			xmlFile = new File(System.getProperty("user.dir") + "/xmlInputs.xml");
        		 
        			Document doc = dBuilder.parse(xmlFile);
        		
        			NodeList nodeList = doc.getElementsByTagName("Input");

               		//  Looping through all of the nodes under "Input" tags

        			for (int temp = 0; temp <nodeList.getLength(); temp++ )
        			{
        				Node nNode = nodeList.item(temp);
        				Element eElement = (Element) nNode;
        				
        				inputHash.put("floors", Integer.parseInt(eElement.getElementsByTagName("floors").item(0).getTextContent()));
        				inputHash.put("elevators", Integer.parseInt(eElement.getElementsByTagName("elevators").item(0).getTextContent()));
        				inputHash.put("elevTravelTime", Integer.parseInt(eElement.getElementsByTagName("elevTravelTime").item(0).getTextContent()));
        				inputHash.put("elevDoorTime", Integer.parseInt(eElement.getElementsByTagName("elevDoorTime").item(0).getTextContent()));
        				inputHash.put("peoplePerMin", Integer.parseInt(eElement.getElementsByTagName("peoplePerMin").item(0).getTextContent()));
        				inputHash.put("duration", Integer.parseInt(eElement.getElementsByTagName("duration").item(0).getTextContent()));
        				inputHash.put("elevCapacity",  Integer.parseInt(eElement.getElementsByTagName("elevCapacity").item(0).getTextContent()));
        				inputHash.put("doorSpeed",  Integer.parseInt(eElement.getElementsByTagName("doorSpeed").item(0).getTextContent()));
        			}
				}
				catch (ParserConfigurationException | SAXException | IOException e)
				{
					e.printStackTrace();
				}
        	}
    	}
    }
    
    public static int getTotalElevatorNumber()
    {
       if (inputHash == null)
           getInputs();
       
        Integer elevators = inputHash.get("elevators");
        
        return  elevators ;
        
    }

    public static int getTotalFloorNumber()
    {
        if (inputHash == null)
           getInputs();
        
        Integer floors = inputHash.get("floors");
        return floors;
        
    }
    public static int getElevTravelTime()
    {
      if (inputHash == null)
           getInputs();
      
        int time = inputHash.get("elevTravelTime");
        return time;
    }
      
    public static int getElevDoorTime()
    {
        if (inputHash == null)
           getInputs();
        
        Integer doorTime = inputHash.get("elevDoorTime");
        return doorTime;
        
    }
   
    public static int getPeoplePerMin()
    {
        if (inputHash == null)
           getInputs();
        
        Integer peoplePerMin = inputHash.get("peoplePerMin");
        
        return peoplePerMin;
    }
    public static int getDuration()
    {
    	if(inputHash == null)
    	{
    		getInputs();
    	}
    	int simulationDuration = inputHash.get("duration");
    	
    	//the value in the xml file is measured in minutes, not milliseconds
    	return simulationDuration * 60000;
    }   
    
    public static int getElevCapacity()
    {
    	if(inputHash == null)
    	{
    		getInputs();
    	}
    	return inputHash.get("elevCapacity");
    }
    
    public static int getDoorSpeed()
    {
      if (inputHash == null)
           getInputs();
      
        int dspeed = inputHash.get("doorSpeed");
        return dspeed;
    }
}
