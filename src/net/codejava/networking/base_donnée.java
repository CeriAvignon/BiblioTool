package net.codejava.networking;

import java.io.IOException;
import java.sql.*;



	public class base_donnée{ 
		
       public static void main(String[] args) {
		// TODO Auto-generated method stub
			try{ 
				// on charge le pilote JDBC 
               Class.forName("com.mysql.jdbc.Driver");
               // on crée une connection du package java.sql
               Connection conn=DriverManager.getConnection
        ("jdbc:mysql://localhost:3306/projetjava",	"root" ,"");
               
               //création d'un objet preparedstatement pour une requette qui récupére les url 
               PreparedStatement ps=conn.prepareStatement("select url from Recherche");
               // execution de la requette 
            		   ResultSet rs=ps.executeQuery();
            		   while(rs.next(){
            			   String fileURL=rs.getString("url");
            		   }
            /** ResultSetMetaData rsmd=rs.getMetaData();
            for (int i=1;i<=rsmd.getColumnCount();i++){
             //parcourir ligne par ligne 
            		   while(rs.next()){
            			  System.out.println(rs.getString("url"));
                                       }*/
            		  /**
           		     * @param URL du fichier a télécharger 
           		     * @param chemin du répértoire pour enregistre les fichier */
           		  
            		   String fileURL="";
           	        String saveDir = "C:/Users/admin/Desktop";
           	            try {
           	                  test.downloadFile(fileURL, saveDir);
           	               } catch (IOException ex) {
           	                ex.printStackTrace();
           	                            }
           	    }catch (Exception e){
            			   e.printStackTrace();
				}
			}
       }


