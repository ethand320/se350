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
  
    public static void main(String args[]){ 
   // public HashMap getInputs() {
    HashMap inputHash = new HashMap();
    
    try{    
        
    File xmlFile = new File("src/xmlInputs.xml");
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(xmlFile);
    
    NodeList nodeList = doc.getElementsByTagName("Input");
    
  //  Element elem = (Element) nodeList;
    
    for (int temp = 0; temp <nodeList.getLength(); temp++ ){
        Node nNode = nodeList.item(temp);
        Element eElement = (Element) nNode;
      //  System.out.println( eElement.getElementsByTagName("floors").item(0).getTextContent());
        inputHash.put("floors", eElement.getElementsByTagName("floors").item(0).getTextContent());
        inputHash.put("elevators", eElement.getElementsByTagName("elevators").item(0).getTextContent());
        inputHash.put("elevTravelTime", eElement.getElementsByTagName("elevTravelTime").item(0).getTextContent());
        inputHash.put("elevDoorTime", eElement.getElementsByTagName("elevDoorTime").item(0).getTextContent());
        inputHash.put("peoplePerMin", eElement.getElementsByTagName("peoplePerMin").item(0).getTextContent());
        
        
    }
    
   
    
    System.out.println(inputHash.get("elevators"));
    
    
  
    
   // System.out.println(rootElement.getAttribute("floors"));
    
    
   // inputHash.put("floors", rootElement.getAttribute("floors"));
    
    String key = "floors";
    
   // System.out.println(inputHash.get(key));
    
  //  return inputHash;
    
    
    
  
    
    } catch (Exception e) { e.printStackTrace(); }
    
  //  return inputHash;
        
        
    }
    
    
    
    
}
