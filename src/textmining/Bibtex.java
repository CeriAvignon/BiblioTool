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
	private Map<String, String> String_var;
	private int i;
	public  Bibtex() { 


		type_de_doc="";
		ref="";
		donne = new HashMap<>();
		String_var=new HashMap<>();
		i=0;

	}

 	 public void affiche(){
 	 System.out.println(type_de_doc+"  \n\nDONNE : ");
 	 affiche_objet(donne);
 	  System.out.println("  \n\nVARIABLE : ");
 	 affiche_objet(String_var);
 	 
 	  	  System.out.println("  \n\nTEST : ");
 	 
 	 if(String_var.get("foo") == null )
		 	System.out.println("VARIABLE NULL'foo'");
		 else 
		 	System.out.println(String_var.get("foo"));//geter si NULL // int
 	 }
 	private void affiche_objet(Map<String, String> objet){
 		Set<Entry<String, String>> setHm = objet.entrySet();

	      Iterator<Entry<String, String>> it = setHm.iterator();

	      while(it.hasNext()){

		 Entry<String, String> e = it.next();

		 System.out.println(e.getKey() + " : " + e.getValue()+"\n");

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

		 for(i=0;i<str.length();i++){
		 
		 	if(str.charAt(i) == '@'){

		 		while(++i<str.length() && str.charAt(i)!= '{' && str.charAt(i)!= '(')
		 			if(str.charAt(i)!= ' ')
		 				tampon+=str.charAt(i);	
		 			
		 		System.out.println("1T !: "+tampon);
		 		if(i < str.length()){
		 		
		 			if(tampon.equalsIgnoreCase("Preamble") || tampon.equalsIgnoreCase("Comment") ){//commentaire ou preambule #onsenfou
		 				OnSenFou(str,str.charAt(i));
		 				
		 			}
		 			else{
		 				if(tampon.equalsIgnoreCase("String")){//C'est une varaible de type string
		 					 Extraire(str,str.charAt(i),String_var);
		 					System.out.println("2T !: "+tampon);
		 				}
		 				else{
		 					System.out.println("3T !: "+tampon);
		 					type_de_doc=tampon;
		 					
					 		//System.out.println(str.charAt(i));
					 		
					 		char init =str.charAt(i);
					 		int in = i+1;
					 		while(++i<str.length() && str.charAt(i)!= ',' && str.charAt(i)!= '=')
					 			ref+=str.charAt(i);
					 		
					 		if(str.charAt(i) != ','){
					 			ref="";
					 			i=in;
					 		}
		 					 Extraire(str,init,donne);
					 	}
			 		}
			 	}
		 	}
		 	
		 		
		 			
		 	tampon="";
		 	
		 	
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
			
				//while(++i<str.length() && str.charAt(i)== ' ' && str.charAt(i)!= '"');	//int sans rien "" ou {}				
				i++;

				if(i < str.length())		//Si c'est le fichier est pas finie
					Valeur( str,fin,objet,cle);
				//else false
				
				//System.out.println(cle);
				cle="";
			}
			else
				if(str.charAt(i) != ' ')
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
 	public boolean Valeur(String str,char FIN_final,Map<String, String> objet,String cle){
 	
 		for(;i<str.length() && str.charAt(i) ==' ';i++);

		System.out.println(str.charAt(i) +"<--");
		
		int nb_init=1;
 		char fin;
 		char init=str.charAt(i);
 		
 		 String tmp="";
 		String Tampon="";
 		
 		if( init == '"')
 			fin='"';
 		else{
	 		
	 		if(init == '{')
	 			fin='}';
	 		else {
				fin='V';
				i--;
			}
 		
 		}
 		
		while(++i<str.length()){


			if(fin != 'V' && str.charAt(i) == fin && str.charAt(i-1) != '\\'){ //nombre paire de \ pour anuler
			
 				nb_init--;
 							
 				if(nb_init == 0){
 				
 					Tampon += tmp;
 					//System.out.println(cle +","+Tampon);
 					System.out.println("!! "+nb_init+" :: "+FIN_final);
 					
 					i++;

 					
 				}
 				
 			}
 			if(fin == 'V'){
 			
 				if((str.charAt(i) == ' ' || str.charAt(i) == '"' || str.charAt(i) == '{' ) && str.charAt(i-1) != '\\'){//beh NON fin # , FIN_final
 					
 					if(String_var.get(tmp) == null ){
 					
					 	System.out.println("VARIABLE NULL'"+tmp+"'");
					 	Tampon +=Integer.parseInt(tmp);
					 }
					 else 
					 	 Tampon +=String_var.get(tmp);//geter si NULL // int
					 	
 					
 					
 					//System.out.println(cle,Tampon);
					 nb_init=0;
 					 i++;
 				
 				}
 				
 			} 
 			if(nb_init !=0 && init == '{' && str.charAt(i) == init && str.charAt(i-1) != '\\')//pour l'instant sa suffit
 				nb_init++;
 				
 			if((str.charAt(i) == ',' || str.charAt(i) == FIN_final) && (fin =='V' || nb_init==0)){
 			
 				if(str.charAt(i) == FIN_final && nb_init==0)
					i--;
				
				 System.out.println(cle+" : "+tmp+":" +str.charAt(i)+" : "+nb_init);
				 
				if(nb_init > 0){
					if(fin == 'V'){
						 if(String_var.get(tmp) == null ){
						 	System.out.println("VARIABLE NULL'"+tmp+"'");
						 	Tampon +=Integer.parseInt(tmp);
						 }
						 else 
						 	 Tampon +=String_var.get(tmp);//geter si NULL // int
						 	
						 System.out.println("VARIABLE");
						 
					}
					else
						 Tampon += tmp; 
						 
				}	 
					 
				objet.put(cle,Tampon);

				return true;
 				
 			}
 			if(nb_init == 0 && str.charAt(i) == '#' ){
 			
 				System.out.println("IL Faut concatener");
 			
 			}	
 			tmp += str.charAt(i);
 			//System.out.println(tmp);

		}
 		return false;//terminer sans fin
 	
 	}
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
}

