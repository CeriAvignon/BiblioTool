package textmining;
import java.io.BufferedReader;
import java.io.FileReader ;
import java.io.FileNotFoundException;
import java.io.IOException;


import java.util.HashMap;
import java.util.Map;

import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

public class Bibtex {

	private String type_de_doc;
	private String ref;
	private Map<String, String> donne;

	public  Bibtex() { 


		type_de_doc="";
		ref="";
		donne = new HashMap<>();

	}
 	public void affiche(){
 		Set<Entry<String, String>> setHm = donne.entrySet();

	      Iterator<Entry<String, String>> it = setHm.iterator();

	      while(it.hasNext()){

		 Entry<String, String> e = it.next();

		 System.out.println(e.getKey() + " : " + e.getValue());

	      }
 	
 	}
 	public void lire(String Fichier){
 	
 		String str="";
	
	
		try{	
			BufferedReader buff = new BufferedReader(new FileReader(Fichier));
		
			String tmp = null;
			 while((tmp = buff.readLine()) != null)
			 	str+=tmp;

 			
		} catch (FileNotFoundException fnfe) {	
		   		
		 
		} catch (IOException ex) {
		
		}
		
		
  	//	System.out.println(str);
  		
  		
  		String tampon="";
  		int mode=-1;
  		
  		String cle="";	
  		   
  		int compteur_acolade=0;  

		 for(int i=0;i<str.length();i++){
		 
		 	if(str.charAt(i) == '@'){

		 		while(str.charAt(++i)!= '{' && i<str.length())
		 			type_de_doc+=str.charAt(i);	
		 			
		 			
		 		if(str.charAt(i)== '{'){
			 		i++;
			 		compteur_acolade=1;
			 		mode=0;	
			 		
			 	}
		 	}
		 	
		 	if(compteur_acolade > 0){
		 	
		 		
		 		if(mode >=0){
		 		
		 			
		 			if(str.charAt(i) == ',' && mode == 0){ // arret du mode 0

		 				 //System.out.println("référence dans la source : " + tampon);
		 				 
		 				 ref=tampon;
		 				tampon="";	 
			 			
			 			i++;
			 			
			 		}
			 		if(str.charAt(i) == '=' && mode == 0 ){	// debut mode 2 fin pour mode 1
			 		
		 				// System.out.println("colonne1 : " + tampon);
		 				
		 				cle = tampon;
		 				tampon="";
		 				mode=2;
						while(str.charAt(++i)!= '"' && str.charAt(i)!= '{' && i<str.length()) ; // debut de la chaine pour le mode 3
						
						if(str.charAt(i)== '{')
							compteur_acolade++;
						i++;

			 		}
			 		if((compteur_acolade == 2 && str.charAt(i)== '}') || (compteur_acolade == 1 && str.charAt(i) == '"') && str.charAt(i-1) != '\\' && mode==2){//fin pour mode 2
						
						
			 			// System.out.println("colonne2 : " + tampon);
			 			
			 			if(str.charAt(i)== '}')
			 				compteur_acolade--;
			 				
						donne.put(cle,tampon);			 			
			 			
			 			tampon="";	
			 			
						while(str.charAt(++i)!= ',' && str.charAt(i) != '}'  && i<str.length());
						
						if(str.charAt(i) == ',')
			 				i++;
			 				
			 			mode = 0;
			 		}
			 		
			 		if(str.charAt(i)== '{' && str.charAt(i-1) != '\\')
							compteur_acolade++;
			 		if(str.charAt(i)== '}' && str.charAt(i-1) != '\\')
		 				compteur_acolade--;
		 				
		 				
		 			tampon+=str.charAt(i);
		 			//System.out.println(tampon);
		 		}
		 			
		 	
		 	}
		 	
		 	
		 }
	
 	
 	}
}


