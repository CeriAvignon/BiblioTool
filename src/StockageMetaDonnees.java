package net.codejava.networking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StockageMetaDonnees {
	
	
	private static String NULL;
	public static String titre_art= NULL ;
	public static String url_art= NULL ;
	public static String doi= NULL ;
	public static String num_vol= NULL ;
	public static String num_issu= NULL ;
	public static String num_pagedeb= NULL ;

	public static String num_pagefin= NULL ;
	public static String anne_pub= NULL ;
	public static String id_jour= NULL ;
	public static String nom_aut= NULL ;
	public static String pren_aut= NULL ;
	public static String affiliation= NULL ;
	public static String citation= NULL ;
	public static String titre_jour= NULL ;
	private static ResultSet rs;

	public static void main(String[] args) {
		
		    Connection con = null;


          String query = "";
		
          // table articles
          
         titre_art=SearchClass.getTitre_art();
          
         url_art=SearchClass.getUrl(); 
          
         doi=SearchClass.getDoi();
          
         num_vol=SearchClass.getUrl();
          
         num_issu=SearchClass.getNumIssu();
          
         num_pagedeb=SearchClass.getNumPageDeb();
          
         num_pagefin=SearchClass.getNumPageFin();
          
         anne_pub=SearchClass.getAnnePub(); 
          
         id_jour=SearchClass.getIdJour();
          
          // table acteurs
          
         nom_aut=SearchClass.getAuthor();
          
         pren_aut=SearchClass.getLastName();
          
         affiliation=SearchClass.getAffiliation();
          
          // table citations
          
         citation=SearchClass.getCitation();
          
          // table journal
          
         titre_jour=SearchClass.getTitre_jour();
		
		// chargement du pilote

      try {

          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

      } catch (ClassNotFoundException e) {

		    System.err.println("Impossible de charger le pilote jdbc:odbc"); 


      }
		
		//connection a la base de données


      try {



          String DBurl = "jdbc:odbc:biblio-too";

          con = DriverManager.getConnection(DBurl);

      } catch (SQLException e) {

		    System.err.println("Connection à la base de données impossible "); 


      }
				
		
 		
		try {
		
			//insertion des URL dans la table articles
		
		     Statement stmt = con.createStatement();
		    
			 query ="insert into articles (titre_art, url_art, doi, num_vol, num_issu, num_pagedeb, num_pagefin, anne_pub, id_jour)  Values (titre_art, url_art, doi, num_vol, num_issu, num_pagedeb, num_pagefin, anne_pub, id_jour)";
					 
			 query ="insert into acteurs (nom_aut, pren_aut, affiliation)  Values (nom_aut, pren_aut, affiliation)";
					 
			 query ="insert into citations (citation)  Values (citation)";
			 
			 query ="insert into journal (titre_jour)  Values (titre_jour)";



		// Execution de la requete
		
			 rs = stmt.executeQuery(query);
		
		
          con.close(); 
			
		} catch (Exception e) { 
		
		    System.err.println("Got an exception! "); 
		
          System.err.println(e.getMessage()); 
      } 
	}
}
		
		
	


