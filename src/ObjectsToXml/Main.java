package ObjectsToXml;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.StringWriter;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Main {

	public static void main(String[] args) {
		ArticleXml toXml=new ArticleXml();
		// TODO Auto-generated method stub
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   final String DB_URL = "jdbc:mysql://localhost/bibliotool";

		   //  Database credentials
		   final String USER = "root";
		   final String PASS = "";
		
		
		   try{
			   Connection conn = null;
			   Statement stmt = null;
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      
		      stmt = conn.createStatement();
		      String sql = "SELECT * FROM article";
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      ListArticles list=new ListArticles();
		      while(rs.next()){
		    	  Article a= new Article();
		         //Retrieve by column name
		         int id  = rs.getInt("id");
		         String[] tabAuteur = rs.getString("auteur").split(", ", -1);
		         String[] tabReference = rs.getString("reference").split("; ", -1);

		         
		         a.setTitre(rs.getString("titre"));
		         a.setAuteur(tabAuteur);
		         a.setAnnee(Integer.parseInt(rs.getString("annee")));
		         a.setResume(rs.getString("resume"));
		         a.setMotsClefs(rs.getString("motclefs"));
		         a.setDescriptionAuteur(rs.getString("descriptionauteur"));
		         a.setSource(rs.getString("source"));
		         a.setReferences(tabReference);
		
		       list.addToListe(a);
		      }
		      
		      toXml.ListObjectToXml(list);
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
	
		
	}
}
