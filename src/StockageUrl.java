package net.codejava.networking;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class StockageURL {
	  
	  
  	

	private static List<String> f;

	public static void main(String[] args) {
		
		    Connection con = null;


            String query = "";
		
		
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
		
			setF(new ArrayList<>());
		
		    setF(SearchClass.getUrl());
		
		//insertion des URL dans la table articles
		
		    Statement stmt = con.createStatement();
		    
			 query = "INSERT INTO articles(url) VALUES (f)";
		
		// Execution de la requete
		
			 ResultSet rs = stmt.executeQuery(query);
		
		
            con.close(); 
			
		} catch (Exception e) { 
		
		    System.err.println("Got an exception! "); 
		
            System.err.println(e.getMessage()); 
        } 
		
		
	}

	public static List<String> getF() {
		return f;
	}

	public static void setF(List<String> f) {
		StockageURL.f = f;
	}


}