package textmining;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.*;
public class TraitementReference{
	char first,pred;
	String page,titre,annee;
	String[] info = new String[7];
	List author;
	List tmp;
	/*--------------------*/
	/*Cette fonction renvoie true si la cha�ne 
	 * pass�e en param�tre et un entier false sinon */
	public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	public void traitementOuvrage(String reference){
		int debut = 0;
		int index = 0;
		int fin= 0;
		int taille = reference.length();
		while(taille > fin){
			first = reference.charAt(fin);
			if(fin > 0)
				pred = reference.charAt(fin-1);
			if( (first == '.') && !(Character.isUpperCase(pred) ) ){
				info[index] = reference.substring(debut, fin);
				debut = fin + 1;
				index++;
			}
			fin++;
		}
	}
	public void traitementPage(){
		String tmp;
		int debut = 0;
		int fin = 0;
		int taille = info[2].length();
		while(taille > fin ){
			first = info[2].charAt(fin);
			if( first == ','){
				tmp = (info[2].substring(debut, fin));
				//System.out.println(tmp);
				if(tmp.contains("page"))
					page = tmp;
				debut = fin+1;
			}
			fin++;
		}
		tmp = (info[2].substring(debut, fin));
		if(tmp.contains("page"))
			page = tmp;
	}
	public void traitementAnnee(){
		tmp = new LinkedList();
		int debut = 0 ;
		int fin = 0 ;
		int taille = info[3].length();
		while(taille > fin ){
			first = info[3].charAt(fin);
			if( first == ','){
				tmp.add(info[3].substring(debut, fin));
				debut = fin+1;
			}
			fin++;
		}
		tmp.add(info[3].substring(debut, fin));
		for(int i = 0; i < tmp.size(); i++){
			 String temp = tmp.get(i).toString();
			 temp = tmp.get(i).toString().trim(); 
			if(!estUnEntier(temp)){
				tmp.remove(i);
			}
		}
		annee = tmp.get(0).toString();
	}
	public void traitementAuthor(){
		author = new LinkedList();
		String tmp;
		int debut = 0;
		int fin = 0;
		int taille = info[0].length();
		while(taille > fin ){
			first = info[0].charAt(fin);
			if( first == ','){
				author.add(info[0].substring(debut, fin));
				debut = fin+1;
			}
			fin++;
		}
		author.add(info[0].substring(debut,fin));
	    for(int i = 0; i < author.size(); i++){
	    	tmp = author.get(i).toString();
	    	if(tmp.contains("and")){
	    		tmp = tmp.substring(4,tmp.length());
	    		author.remove(i);
	    		author.add(tmp);
	    	}
	    }
	}
	public void traitementTitre(){
		titre = info[1];
	}
	public void Aff(){
		System.out.println("Titre de l'article:");
		System.out.println(titre);
		System.out.println("Voici le(s) auteur(s):");
		for(int i = 0; i < author.size(); i++){
	    	System.out.println(author.get(i));
	    }
		System.out.println("Ann�e : "+annee);
		System.out.println(page);
	}
	
	 public void analyse(String ref){
         traitementOuvrage(ref);
         traitementAuthor();
         traitementPage();
         traitementAnnee();
         traitementTitre();
         //Aff();
	 }
	
	public Reference go(String s){
		analyse(s);
		Reference r = new Reference(author,titre,page,annee);
		return r;
	}
		
}
