package textmining;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author sandaly DIALLO
 */

public class SplitReferences{
	
	
	/**
    * This variable help to handle each item in this referencesByParagraphe.
    *  it's initialize to -1 So that at the first increment it becomes 0.
    */
	  
	   int j=-1;
	/**
    * This Array will contain the references by paragraph after the process.
    */
	public List<String> referencesByParagraphe = new ArrayList<String>(); 
	
	/**
	 * This method process references begin by a number for each paragraph.
	 * This Array contains all of references from process of PDF by NILS.
	 * @param referenceArray: Contains references be for processing  
	 * @return referencesByParagraphe: contain references by paragr.ph.
	 */
	
	public ArrayList<Reference> splitReferences(List<String> referenceArray)
	{
		
		if(referenceArray.get(0).substring(0, 1).equals("[")&& Objects.equals(referenceArray.get(0).substring(2, 3), new String("]")))
//			if(Objects.equals(referenceArray.get(0).substring(0, 1), "[")&& Objects.equals(referenceArray.get(0).substring(2, 3), new String("]")))
		{
			splitRefenceInArrayStringWithBracket(referenceArray);
		
		}
		
		if(Objects.equals(referenceArray.get(0).substring(0, 1), new String("1")))
		{
			splitRefenceInArrayStringWithBracket(referenceArray);
			
		}
		
		
		ArrayList<Reference> ret = new ArrayList<Reference>();
		
		TraitementReference tr = new TraitementReference();
		
		for(String ref : referencesByParagraphe)
		{
			ret.add(tr.go(ref));
		}
		
		return ret;
	}
	
	public List<String> splitRefenceInArrayStringWithBracket(List<String> referenceArray)
	{
		String firstChar,thirdChar;
	
		for(int i = 0; i< referenceArray.size();i++)
		{
			firstChar = referenceArray.get(i).substring(0,1);
			thirdChar = referenceArray.get(i).substring(2, 3);
			
			
			if(Objects.equals(firstChar, new String("[")) && Objects.equals(thirdChar, new String("]")))
			{
				referencesByParagraphe.add(referenceArray.get(i));
				j++;
			}
			else
			{
				referencesByParagraphe.set(j, (referencesByParagraphe.get(j)+referenceArray.get(i)));
			}
				
		}
		return referencesByParagraphe;
	}
	
	/**
	 * This method process references begin by Bracket for each paragraph.
	 * This Array contains all of references from process of PDF by NILS.
	 * @param referenceArray: Contains references be for processing  
	 * @return referencesByParagraphe: contain references by paragr.ph.
	 */
	
	public List<String> splitRefenceInArrayStringWithNumber(List<String> referenceArray)
	{	   
		   
		   
		   /**
		    * The loop to process references who begin by a number at the first character.
		    * Processing references to put them in an Arraylist for supply to <Kudret>
		    */
		   
		   for(int i = 0; i < referenceArray.size(); i++) {
			   
			   int index= referenceArray.get(i).indexOf( '.' );
			   if (index>0)
			   {
				   String lineReference=referenceArray.get(i).substring(0, index);
				   try {
					   Integer.parseInt(lineReference);
					   referencesByParagraphe.add(referenceArray.get(i));
					   j++;
					   
				   } catch (Exception e) {
					   
					   referencesByParagraphe.set(j, (referencesByParagraphe.get(j)+referenceArray.get(i)));
				   }
			   }
			   else{
				   referencesByParagraphe.set(j, (referencesByParagraphe.get(j)+referenceArray.get(i)));
			   }
			}
		   return referencesByParagraphe;
	   }
	}
