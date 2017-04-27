
 /*     Copyright (C) 2016 Benhachani Mohamed
*
 *   This program is free software; you can redistribute it and/or
  *  modify it under the terms of the GNU Lesser General Public
   * License as published by the Free Software Foundation; either
    *version 2.1 of the License, or (at your option) any later version.
*
 *   This program is distributed in the hope that it will be useful,
  *  but WITHOUT ANY WARRANTY; without even the implied warranty of
   * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
    *Lesser General Public License for more details.
*
 *   You should have received a copy of the GNU Lesser General Public
  *  License along with this program; if not, write to the Free Software
   * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
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

 /**
 * Bibtex est une classe qui peut lire des fichier bibtex

 * 
 * @author Benhachani Mohamed
 * @version 1.0
 */
public class Bibtex {

	private String type_de_doc;
	private String ref;
	private Map<String, String> donne;
	private Map<String, String> String_var;
	private int i;
	
	
	
	/**
         * Constructeur de Bibtex. 
	 *
         * A la construction d'un objet Bibtex 

         */
	public  Bibtex() { 


		type_de_doc="";
		ref="";
		donne = new HashMap<>();
		String_var=new HashMap<>();
		i=0;

	}
	/*
	public static void main(String[] args) {
	
		//Exemple avec un fichier
			/*Bibtex B = new Bibtex();
			B.ReadFromFile("../science.bib");
			B.affiche();
		
			B.ArticleFromBibtex().ViewArticle();
			
		
		//Exemple avec un string 
		
		
		

 	}*/
 	/**
         * Affiche les donnee de l'objet Bibtex. 
	 *

         */
 	 public void affiche(){
	 	 System.out.println(type_de_doc+"  \n\nDONNE : ");
	 	 affiche_objet(donne);
	 	  System.out.println("  \n\nVARIABLE : ");
	 	 affiche_objet(String_var);
	 	 System.out.println("  \n\n\n");
	 
 	 }
 	private void affiche_objet(Map<String, String> objet){
 		Set<Entry<String, String>> setHm = objet.entrySet();

	      Iterator<Entry<String, String>> it = setHm.iterator();

	      while(it.hasNext()){

		 Entry<String, String> e = it.next();

		 System.out.println(e.getKey() + " : " + e.getValue()+"\n");

	      }
 	
 	}
 	/**
         * Affiche les donnee de l'objet Bibtex. 
         */
 	public Article ArticleFromBibtex(){
 	
 		Article A = new Article();
 		
 		 if(donne.get("author") != null ){
 		 		String [] t = donne.get("author").split("and");
			 	 A.SetAuteur(t[0]);
			 	 
		} 	 
		if(donne.get("title") != null ){

			 	 A.SetTitre(donne.get("title"));
			 	 
		} 		 	 
		if(donne.get("booktitle") != null ){

			 	 A.SetTitre(donne.get("booktitle"));
			 	 
		} 
		if(donne.get("abstract") != null ){

			 	 A.SetResume(donne.get("abstract"));
			 	 
		}
		if(donne.get("note") != null ){

			 	 A.SetDescriptionAuteur(donne.get("note"));
			 	 
		} 
		if(donne.get("year") != null ){

			 	 A.SetAnnee( Integer.parseInt(donne.get("year")));
			 	 
		}	 
			 		 	 
 		return A;
 	
 	}
 	/**
         * Renvoie le fichier dans un string. 
	 *

         */
 	public String FromFilePathToString(String Fichier){
 		String str="";
 		try{	
			BufferedReader buff = new BufferedReader(new FileReader(Fichier));
		
			String tmp = null;
			 while((tmp = buff.readLine()) != null)
			 	str+=tmp;

 			
		} catch (FileNotFoundException fnfe) {	
		   		
		   
		} catch (IOException ex) {
		
		}
		
 	
 		return str;
 	
 	}
 	/**
         * lit un fichier bibtex et implemente la classe 
	 *

         */
          public boolean ReadFromFile(String Fichier){
          
          	 	return	ReadFromString(FromFilePathToString(Fichier));
          	
          }
          /**
         * lit un String bibtex et implemente la classe 
	 *

         */
 	public boolean ReadFromString(String str){
 	
  		String tampon="";
  		int mode=-1;
  		
  		String cle="";	
  		   
  		int compteur_acolade=0;  

		 for(i=0;i<str.length();i++){
		 
		 	if(str.charAt(i) == '@'){

		 		while(++i<str.length() && str.charAt(i)!= '{' && str.charAt(i)!= '(')
		 			if(str.charAt(i)!= ' ')
		 				tampon+=str.charAt(i);	
		 			
		 		if(i < str.length()){
		 		
		 			if(tampon.equalsIgnoreCase("Preamble") || tampon.equalsIgnoreCase("Comment") ){//commentaire ou preambule #onsenfou
		 				OnSenFou(str,str.charAt(i));
		 				
		 			}
		 			else{
		 				if(tampon.equalsIgnoreCase("String")){//C'est une varaible de type string
		 					 if(!Extraire(str,str.charAt(i),String_var))
		 					 	return false;
		 				}
		 				else{
		 					type_de_doc=tampon;
					 		
					 		char init =str.charAt(i);
					 		int in = i+1;
					 		while(++i<str.length() && str.charAt(i)!= ',' && str.charAt(i)!= '=')
					 			ref+=str.charAt(i);
					 		
					 		if(str.charAt(i) != ','){
					 			ref="";
					 			i=in;
					 		}
		 					 if(!Extraire(str,init,donne))
		 					 	return false;
					 	}
			 		}
			 	}
		 	}
		 	
		 		
		 			
		 	tampon="";
		 	
		 	
		 }
		return true;
 	
 	}
 	private String OnSenFou(String str,char init){ 	//c'est les comentaire et preambule 
 	
 		for(;i<str.length() && str.charAt(i) ==' ';i++);	//j'avance tant qu'il ya un espace
 		
 		int nb_init=1;
 		char fin;
 		
 		String Tampon="";
 		
 		if(init == '(')
 			fin=')';
 		else{
	 		
	 		if(init == '{')
	 			fin='}';
	 		else 
	 			return Tampon;
 		
 		}
 		while(++i<str.length()){
 		
 			if(str.charAt(i) == fin && str.charAt(i-1) != '\\'){ //nombre paire de \ pour anuler
 				nb_init--;		
 				
 				if(nb_init == 0){
 					i++;
 					return Tampon;
 					
 				}
 			}
 			if(str.charAt(i) == init && str.charAt(i-1) != '\\')//pour l'instant sa suffit
 				nb_init++;
	
			Tampon +=str.charAt(i);
 		}
 		
 		 return Tampon;
	
 	}
 	
 	
 	//------------>
 	private boolean Extraire(String str,char init,Map<String, String> objet){
 	
 		String cle="";	
 		int nb_init=1;
 		char fin;
 		
 		if(init == '(')
 			fin=')';
 		else{
	 		
	 		if(init == '{')
	 			fin='}';
	 		else 
	 			return false;//bwef
 		
 		}
 		
		while(++i<str.length()){
		
			if(str.charAt(i) == '='){
					
				i++;

				if(i < str.length()){		//Si c'est le fichier est pas finie
					if(!Valeur( str,fin,objet,cle))
						return false;
				}
				else 
					return false;//terminer sans fin

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
 	private boolean Valeur(String str,char FIN_final,Map<String, String> objet,String cle){
 	
 		for(;i<str.length() && str.charAt(i) ==' ';i++);	//j'avance tant qu'il ya un espace
		
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

			if(nb_init>0 && fin != 'V' && str.charAt(i) == fin && str.charAt(i-1) != '\\'){ //nombre paire de \ pour anuler
			
 				nb_init--;
 							
 				if(nb_init == 0){
 				
 					Tampon += tmp;
 					i++;		
 				}
 				
 			}
 			if(fin == 'V'){
 			
 				if((str.charAt(i) == ' ' || str.charAt(i) == '#') && str.charAt(i-1) != '\\'){//je guette si c'est la fin d'une variable
 					
 					if(String_var.get(tmp) == null )
					 	Tampon +=Integer.parseInt(tmp);

					 else 
					 	 Tampon +=String_var.get(tmp);
					 	
					 nb_init=0;
					 if(str.charAt(i) != '#')
 					 	i++;
 				
 				}
 				
 			} 
 			if(nb_init !=0 && init == '{' && str.charAt(i) == init && str.charAt(i-1) != '\\')//pour l'instant sa suffit
 				nb_init++;
 				
 			if((str.charAt(i) == ',' || str.charAt(i) == FIN_final) && (fin =='V' || nb_init==0)){	//si un signe de fin 
 			
 				if(str.charAt(i) == FIN_final && nb_init==0)
					i--;
				 
				if(nb_init > 0){
					if(fin == 'V'){
						 if(String_var.get(tmp) == null )
						 	Tampon +=Integer.parseInt(tmp);
						 else 
						 	 Tampon +=String_var.get(tmp);
					}
					else
						 Tampon += tmp; 
						 
				}	 

				objet.put(cle.toLowerCase(),Tampon);

				return true;
 				
 			}
 			if(nb_init == 0 && str.charAt(i) == '#' ){
 			
 				while(++i<str.length() && (str.charAt(i) ==' ' || str.charAt(i) =='#'));
		
				nb_init=1;
		 		init=str.charAt(i);
		 		
		 		tmp="";
		 		
		 		if( init == '"'){
		 			fin='"';
		 			i++;
		 		}
		 		else{
			 		if(init == '{'){
			 			fin='}';
			 			i++;
			 		}
			 		else 
						fin='V';
		 		}
		 		
 			}	
 			tmp += str.charAt(i);

		}
 		return false;//terminer sans fin
 	
 	}
 	
	
 	
}






