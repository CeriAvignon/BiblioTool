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
	private int i;
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
  		
  		 Map<String, String> String_var= new HashMap<>();;
  		String tampon="";
  		int mode=-1;
  		
  		String cle="";	
  		   
  		int compteur_acolade=0;  

		 for(i=0;i<str.length();i++){
		 
		 	if(str.charAt(i) == '@'){

		 		while(++i<str.length() && str.charAt(i)!= '{' && str.charAt(i)!= '(')
		 			tampon+=str.charAt(i);	
		 			
		 			
		 		if(i < str.length()){
		 		
		 			if(tampon.equalsIgnoreCase("Preamble") || tampon.equalsIgnoreCase("Comment") ){//commentaire ou preambule #onsenfou
		 				OnSenFou(str,str.charAt(i));
		 				
		 			}
		 			else{
		 				if(tampon.equalsIgnoreCase("String")){//C'est une varaible de type string
		 					 Extraire(str,str.charAt(i),String_var);
		 					
		 				}
		 				else{
		 					type_de_doc=tampon;
		 					
					 		//System.out.println(str.charAt(i));
					 		
					 		char init =str.charAt(i);
					 		
					 		while(++i<str.length() && str.charAt(i)!= ',' && str.charAt(i)!= '=')
					 			ref+=str.charAt(i);
					 		
					 		if(str.charAt(i) != ',')
					 			ref="";
		 					 Extraire(str,init,String_var);
					 	}
			 		}
			 	}
		 	}
		 	
		 		
		 			
		 	
		 	
		 	
		 }
	
 	
 	}
 	public void OnSenFou(String str,char init){
 	
 			
 		int nb_init=1;
 		char fin;
 		
 		if(init == '(')
 			fin=')';
 		else{
	 		
	 		if(init == '{')
	 			fin='}';
	 		else 
	 			return;
 		
 		}
 		while(++i<str.length()){
 		
 			if(str.charAt(i) == fin && str.charAt(i-1) != '\\'){ //nombre paire de \ pour anuler
 				nb_init--;		
 				
 				if(nb_init == 0){
 					i++;
 					return;
 					
 				}
 			}
 			if(str.charAt(i) == init && str.charAt(i-1) != '\\')//pour l'instant sa suffit
 				nb_init++;
 			
 		
 			
 		}
 	
 	
 	
 	}
 	
 	
 	//------------>
 	public boolean Extraire(String str,char init,Map<String, String> objet){
 	
 		String cle="";	
 		int nb_init=1;
 		char fin;
 		
 		if(init == '(')
 			fin=')';
 		else{
	 		
	 		if(init == '{')
	 			fin='}';
	 		else 
	 			return false;
 		
 		}
 		
		while(++i<str.length()){
			//System.out.println(str.charAt(i));
			if(str.charAt(i) == '='){
			
				while(++i<str.length() && str.charAt(i)!= '{' && str.charAt(i)!= '"');	//int sans rien "" ou {}

				if(i < str.length())
					Valeur( str,str.charAt(i),objet,cle);
				//else false
				
				System.out.println(cle);
				cle="";
			}
			else
				cle+=str.charAt(i);
			
			if(str.charAt(i) == ',')
				cle="";


			if(str.charAt(i) == fin && str.charAt(i-1) != '\\'){ //nombre paire de \ pour anuler
 				nb_init--;
 				
 				if(nb_init == 0){
 					i++;
 					return true;
 					
 				}

 			}
 			if(str.charAt(i) == init && str.charAt(i-1) != '\\')//pour l'instant sa suffit
 				nb_init++;
 			

		}
 		return false;//terminer sans fin
 	
 	
 	}
 	public boolean Valeur(String str,char init,Map<String, String> objet,String cle){
 	
 		//System.out.println("ok "+init);
 	
 		int nb_init=1;
 		char fin;
 		
 		if(init == '"')
 			fin='"';
 		else{
	 		
	 		if(init == '{')
	 			fin='}';
	 		else 
	 			return false;
 		
 		}
 		
		while(++i<str.length()){


			if(str.charAt(i) == fin && str.charAt(i-1) != '\\'){ //nombre paire de \ pour anuler
			
 				nb_init--;
 							
 				if(nb_init == 0){
 					i++;
 					return true;
 					
 				}
 				
 			}
 			if(str.charAt(i) == init && str.charAt(i-1) != '\\')//pour l'instant sa suffit
 				nb_init++;
 			
 			System.out.print(str.charAt(i));

		}
 	 	
 		return false;//terminer sans fin
 	
 	}
}


