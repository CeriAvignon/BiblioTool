/**
 * @author issam
 *
 */

package webmining;
// import textmining.Article;
import textmining.Meta_Donnes;
// import webmining.SearchDblp;

import java.sql.*;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;



public class Insertion  {
	
	private static Statement state;
	private static Connection conn;	
	
    Meta_Donnes donnee;
	

public Insertion() {

	
    try {
Class.forName("com.mysql.jdbc.Driver").newInstance();
 conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/etd","root","mdp");
 state = (Statement) conn.createStatement();

/* Connection effective */

    }catch (Exception ex) {
        // handle the error
    	System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
        System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
    }
}


	/*	
	private int idArt;
	private String titleArt;
	private String urlPage; //  
	private String urlPdf;  
	private String doi;
	private int numVol;
	private int numIssue;
	private String numPage;  // 
	private int nbPage;  
	private String yearPub;
	private boolean status;
	private int idJour;
*/	

		
	//	SearchDblp search_dblp=new SearchDblp();
		
	//	int id=2;
		
/*		String urlP1=search_dblp.getUrl(null);
		String titl1e=search_dblp.getTitle(null);
		String year1=search_dblp.getYear(null);
*/		
    
	
	
	public void insert() throws SQLException{  // Une méthode pour inserer dans les tables 
		                                       // article et author sur la base de donnees
		
/*		
		String requete1 ="INSERT INTO article VALUES('"+donnee.getAuteur()+"','"+titleArt+"','"+urlPage+"','"
		         +yearPub+"')";
state.executeUpdate(requete1);   
*/
        
	String requete1 ="INSERT INTO article (titleArt, nbPage, yearPub) VALUES('"+donnee.getTitre()+"','"+donnee.getNbPage()+
			                                      "','"+donnee.getDate()+"')";
	state.executeUpdate(requete1);
	

    String requete2 ="INSERT INTO author (nameAut, surnameAut, affiliation) VALUES('"+donnee.getAuteur()+"','"+donnee.getCreateur()+
    		                                     "','"+donnee.getSujet()+"')";
    state.executeUpdate(requete2);


                 System.out.println("\n Les données sont bien inseré");

    }
	
	


	public void  insert_modifier(int id) throws SQLException{  // Une méthode pour modifer dans les tables 
                                                               // article et author sur la base de donnees
	     

	int executeUpdate1 = state.executeUpdate("UPDATE article set titleArt='"+donnee.getTitre()+"',nbPage='"+
				                                  donnee.getNbPage()+"',yearPub='"+donnee.getDate()+"' where idArt='"+id+"'");
	
	
	int executeUpdate2 = state.executeUpdate("UPDATE author set nameAut='"+donnee.getAuteur()+"',surnameAut='"+donnee.getCreateur()+
                                                 "',affiliation='"+donnee.getSujet()+"' where idArt='"+id+"'");  
	
	
	        	System.out.println("\n Les données sont bien modifié"+executeUpdate1+"dans la table article ,"+executeUpdate2+"dans la table author");

    }
}
