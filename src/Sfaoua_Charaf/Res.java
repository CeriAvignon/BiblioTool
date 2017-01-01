package Sfaoua_Charaf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Res {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// instantie la classe article
		     Article ar=new Article();
		     
		    //création d'une arraylist dans laquelle je recupere les resultat de la fonction recupurl()
		     
		List<String> f= new ArrayList<>(ar.recupurl());
		
		  String saveDir= "C:/Users/admin/Desktop/l3"; 
		  
		  // parcourir sur la liste pour telecharger chaque url et le stocker localement 
		  
		  for (int i = 0; i<f.size(); i++){
			 try {
				  String val=f.get(i);
				 // System.out.println(f.get(i));
			  Article.downloadFile(val,saveDir);
			  
			  }catch(IOException e){};
			
		}
	}
}
	
		  

	

