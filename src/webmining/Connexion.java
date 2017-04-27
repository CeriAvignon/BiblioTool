package webmining;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Tanguy
 */
public class Connexion {
  
   
public static void main(String[] args) {
      Connection cnx = ConnecterDB();
      
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
            System.out.println("connexion bien etablie");
            
            return cnx;
        }
        catch (ClassNotFoundException | SQLException e) {
            return null;
             }
   }
}