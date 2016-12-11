package net.codejava.networking;

import java.io.IOException;
import java.sql.*;



	public class base_donnée{ 
		
       public static void main(String[] args) {
		try{
                    // on charge le pilote JDBC 
              Class.forName("com.mysql.jdbc.Driver");
              // on crée une connection du package java.sql		
            Connection conn=DriverManager.getConnection
         ("jdbc:mysql://localhost:3306/projetjava","root" ,"");

       //création d'un objet preparedstatement pour une requette qui récupére les url 
      //PreparedStatement statement=conn.prepareStatement("select url from Recherche");
                              
            String queryString = "select url from Recherche";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(queryString);
            
            String col[] = {"url"};
           String  cont[][] = new String[10][1];
            int i = 0;
            while (rs.next()) {
                String nom = rs.getString("url");
                cont[i][1] = nom ;
                   i++;
                   }
            }catch (Exception e)
            {}
             /**
              * @param URL du fichier a télécharger 
           	  * @param chemin du répértoire pour enregistre les fichier */
           		  
            		String fileURL =""; 
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


