package net.codejava.networking;

import java.sql.*;

public class base_donnée {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			try{ 
				// on charge le pilote JDBC 
               Class.forName("com.mysql.jdbc.Driver");
               // on crée une connection du package java.sql
               Connection conn=DriverManager.getConnection
        ("jdbc:mysql://localhost:3306/non_base données" , "root" ,"");
               
               //création d'un objet preparedstatement pour une requette qui récupére les url 
               PreparedStatement ps=conn.prepareStatement("select * from Recherche");
               // execution de la requette 
            		   ResultSet rs=ps.executeQuery();
            		  //parcourir ligne par ligne 
            		   
            		   while(rs.next()){
            			  System.out.println(rs.getString("url")+"t");
            		  }
            		   }catch (Exception e){
            			   e.printStackTrace();
				}
			}

}
