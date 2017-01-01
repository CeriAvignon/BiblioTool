package net.codejava.networking;

import java.sql.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StockageURL {
  
  
  	public static void main(String[] args) {
		
		    Connection con = null;

            ResultSet rs = null;

            String query = "";
		
		
		// chargement du pilote

        try {

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

        } catch (ClassNotFoundException e) {

            arret("Impossible de charger le pilote jdbc:odbc");

        }
		
		//connection a la base de données


        try {

 

            String DBurl = "jdbc:odbc:biblio-too";

            con = DriverManager.getConnection(DBurl);
 
        } catch (SQLException e) {

            arret("Connection à la base de données impossible");

        }
				
		
   		
		try {
		
			List<String> f= new ArrayList<>();
		
		    f = Article.recupurl();
		
		//insertion des URL dans la table articles
		
		    Statement stmt = conn.createStatement();
		    
			String query = "INSERT INTO articles VALUES (f,'url')";
		
		// Execution de la requete
		
		    rs = stmt.executeQuery(query);
		
		
            conn.close(); 
			
		} catch (Exception e) { 
		
		    System.err.println("Got an exception! "); 
		
            System.err.println(e.getMessage()); 
        } 
		
		
	}


}