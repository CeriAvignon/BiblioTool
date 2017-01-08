/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;
import java.sql.*;
/**
 *
 * @author Tanguy
 */
public class Connexion {
  
   
  public static void main(String[] args) {
      Connection cnx= ConnecterDB();
      
    }
   public static Connection ConnecterDB(){
         String url="jdbc:mysql://localhost:3306/bd-biblio";
         String user="root";
         String password="";  
         
         
        try{
            //chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver ok");
            //recuperation de la connexion
            Connection cnx= DriverManager.getConnection(url,user,password);
            System.out.println("connexion bien établie");
            
            return cnx;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
             }
   }
}
 