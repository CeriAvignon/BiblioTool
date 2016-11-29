package test_sax;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

//import org.xml.sax.Attributes;
//import org.xml.sax.SAXException;
//import org.xml.sax.helpers.DefaultHandler;

public class Test_Sax {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		      try {	
		         File inputFile = new File("simple.xml");
		         SAXParserFactory factory = SAXParserFactory.newInstance();
		         SAXParser saxParser = factory.newSAXParser();
		         Sax_hundler detectionEvenement = new Sax_hundler();
		         saxParser.parse(inputFile, detectionEvenement);     
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		   }   
		}
		/****/
		/* public class Sax_hundler extends DefaultHandler {

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
		   public void endElement(String uri, 
		   String localName, String qName) throws SAXException {
		      if (qName.equalsIgnoreCase("food")) {
		         System.out.println("End Element :" + qName);
		      }
		   }
		   
		   @Override
		   public void characters(char ch[], 
		      int start, int length) throws SAXException {
		      if (platName) {
		         System.out.println("First Name: " 
		            + new String(ch, start, length));
		         platName = false;
		      } else if (platPrice) {
		         System.out.println("Last Name: " 
		            + new String(ch, start, length));
		         platName = false;
		      } else if (platDescription) {
		         System.out.println("Nick Name: " 
		            + new String(ch, start, length));
		         platDescription = false;
		      } else if (platCalories) {
		         System.out.println("Marks: " 
		            + new String(ch, start, length));
		         platCalories = false;
		      }
		   }
		}

		*/
		
		/****/

		
		
		
		

