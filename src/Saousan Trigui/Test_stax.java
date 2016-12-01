package test_stax;

/*
 * @author trigui
 * @version 3.0
 */


import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
//import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class Test_stax {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		/********/
      boolean name_plat = false;
      boolean price_plat = false;
      boolean description_plat = false;
      boolean calories_plat = false;
      int nbRacineNoeuds=4;
	  int nbElementFood=5;
      String [ ][ ] MatriceFoodElement = new String [nbRacineNoeuds][nbElementFood];
      
      try {
    	  //récupérer une instance de fabrique de parseur
         XMLInputFactory factory = XMLInputFactory.newInstance();
         XMLEventReader eventReader =factory.createXMLEventReader(new FileReader("simple.xml"));
       
         int i=0;
         int j=0;
            while(eventReader.hasNext())
            {
               XMLEvent event = eventReader.nextEvent();
               switch(event.getEventType()){
                  case XMLStreamConstants.START_ELEMENT:
                     StartElement startElement = event.asStartElement();
                     String qName = startElement.getName().getLocalPart();
                     if (qName.equalsIgnoreCase("food")) {
                        System.out.println("Start Element : food");
                        
                     } else if (qName.equalsIgnoreCase("name")) {
                       //
                        name_plat = true;
                     } else if (qName.equalsIgnoreCase("price")) {
                        price_plat = true;
                     } else if (qName.equalsIgnoreCase("description")) {
                        description_plat = true;
                     }
                     else if (qName.equalsIgnoreCase("calories")) {
                        calories_plat = true;
                     }				        
                     break;
                  case XMLStreamConstants.CHARACTERS:
                     Characters characters = event.asCharacters();
                     if(name_plat){
                    	 MatriceFoodElement[i][j]=characters.getData();
             		    System.out.println( MatriceFoodElement[i][j]);

                    	 i++;
                       // System.out.println("nom du plat: " 
                       // + characters.getData());
                        
                        name_plat = false;
                     }
                     if(price_plat){
                    	 MatriceFoodElement[i][j]=characters.getData();
                    	 i++;
                       // 
                       // System.out.println("price_plat" + characters.getData());
                         price_plat = false;
                     }
                     if(description_plat){
                    	 MatriceFoodElement[i][j]=characters.getData();
                    	 i++;
                       //
                        //System.out.println("description: " 
                       // + characters.getData());
                        description_plat = false;
                     }
                     if(calories_plat){
                    	 
                    	 MatriceFoodElement[i][j]=characters.getData();
                    	 i++;
             		    System.out.println( MatriceFoodElement[i][j]);

                       // System.out.println("Marks: " 
                        //+ characters.getData());
                        calories_plat = false;
                     }
                     break;
                  case  XMLStreamConstants.END_ELEMENT:
                     EndElement endElement = event.asEndElement();
                     if(endElement.getName().getLocalPart().equalsIgnoreCase("food")){
                        System.out.println("End Element : food");
                        System.out.println();
                     }
                     break;
               
                    
                }
               j++;
              
            
            }
           	
    		    
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (XMLStreamException e) {
            e.printStackTrace();
      }
   
      for(int k=0;k<=4;k++)
    		for (int m = 0; m<=5; m++) {
		    System.out.println( MatriceFoodElement[k][m]);
		    }  

		
		
		
		/*********/
	}

}
