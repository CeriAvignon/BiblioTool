package webmining;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

	public class ConnexDB {
		   
		    	private static void affiche(String message) {
		    		System.out.println(message);
		    		}
		    	
		    		private static void arret(String message) {
		    		System.err.println(message);
		    		System.exit(99);
		    		}
		     
		    @SuppressWarnings("unused")
			public static void main(java.lang.String[] args){
		    	Connection con = null;
		    	ResultSet résultats = null;
		    	String requete = "";
		    	// chargement du pilote
		    	try {
		    	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		    	} 
		    	catch (ClassNotFoundException e) {
		    	arret("Impossible de charger le pilote jdbc:odbc");
		    	}
		    	//connection a la base de données
		    	affiche("connexion a la base de données");
		    	try {
		    		con = DriverManager.getConnection("jdbc:mysql://localhost/java","root", "root");
		    	}
		    	catch (SQLException e) {
		    	arret("Connection à la base de données impossible");
		}
}
}
	
	    