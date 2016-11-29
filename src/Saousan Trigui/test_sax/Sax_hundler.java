package test_sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Sax_hundler extends DefaultHandler {

   boolean platName = false;
   boolean  platPrice= false;
   boolean platDescription = false;
   boolean platCalories = false;

   @Override
   public void startElement(String uri, 
   String localName, String qName, Attributes attributes)
      throws SAXException {
      if (qName.equalsIgnoreCase("name")) {
    	  platName = true;
      } else if (qName.equalsIgnoreCase("price")) {
    	  platPrice = true;
      } else if (qName.equalsIgnoreCase("description")) {
    	  platDescription = true;
      }
      else if (qName.equalsIgnoreCase("calories")) {
    	  platCalories = true;
      }
   }

   @Override
   public void endElement(String uri, String localName, String qName)
	  throws SAXException {
      if (qName.equalsIgnoreCase("food")) {
    	  System.out.println("********");
          System.out.println("Debut element :" + qName);

         System.out.println("fin element :" + qName);
   	  System.out.println("********");

      }
   }
   
   @Override
   public void characters(char ch[], 
      int start, int length) throws SAXException {
      if (platName) {
         System.out.println("nom du plat: "+ new String(ch, start, length));
         platName = false;
      } else if (platPrice) {
         System.out.println("Prix: " + new String(ch, start, length));
         platPrice = false;
      } else if (platDescription) {
         System.out.println("détail:" + new String(ch, start, length));
         platDescription = false;
      } else if (platCalories) {
         System.out.println("calories: "  + new String(ch, start, length));
         platCalories = false;
      }
   }
}
