/**
 * @author issam
 *
 */

package webmining;

// import webmining.SearchDblp;

import java.sql.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;



public class Insertion  {
	
	Connexion connexion;
 
    Article article;
    Journal journal;
    Author auteur;
    
    private static Statement state;
	

public Insertion() {

	connexion=new Connexion();
	
	article = new Article(0, null, null, null, null, 0, 0, null, false);
	journal = new Journal(0, null);
	auteur = new Author(0, null, null, null);
	
/* Connection effective */
	
/**/    try {
    Class.forName("com.mysql.jdbc.Driver").newInstance();
       
    state=(Statement) Connexion.ConnecterDB();

    }catch (Exception ex) {
        // handle the error
    	System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
        System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
    }
}
/*	
	//	SearchDblp search_dblp=new SearchDblp();
		
	//	int id=2;
		
		String urlP1=search_dblp.getUrl(null);
		String titl1e=search_dblp.getTitle(null);
		String year1=search_dblp.getYear(null);
*/		
    
	
	
	public void insert() throws SQLException{  // Une mÃ©thode pour inserer dans les tables 
		                                       // article et author sur la base de donnees
		
/*		
		String requete1 ="INSERT INTO article VALUES('"+donnee.getAuteur()+"','"+titleArt+"','"+urlPage+"','"
		         +yearPub+"')";
state.executeUpdate(requete1);   
*/
        
	String requete1 ="INSERT INTO article (titleArt, urlPage, urlPdf, doi, numIssue, nbPage, yearPub, status) VALUES('"+article.getTittle()+"','"
	+article.getUrlPage()+"','"+article.getUrlPdf()+"','"+article.getdoi()+"','"+"','"+article.getnumIssue()+"','"+article.getnbPage()+"','"
	+article.getYearPub(null)+"','"+article.getStatus(false)+"')";
	state.executeUpdate(requete1);
	

    String requete2 ="INSERT INTO author (nameAut,surnameAut,affiliation) VALUES('"+auteur.getName()+"','"+auteur.getSurname()+"','"+auteur.getAffiliation(null)+"')";
    state.executeUpdate(requete2);

    String requete3 ="INSERT INTO journal (nameJour) VALUES('"+journal.getName()+"')";
    state.executeUpdate(requete2);

                 System.out.println("\n Les donnÃ©es sont bien inserÃ©");

    }
	
	


	public void  insert_modifier(int id) throws SQLException{  // Une mÃ©thode pour modifer dans les tables 
                                                               // article et author sur la base de donnees
	     

	int executeUpdate1 = state.executeUpdate("UPDATE article set titleArt='"+article.getTittle()+"',urlPage='"+
			article.urlPage+"',urlPdf='"+article.urlPdf+"',doi='"+article.getdoi()+"',numIssue='"+
			article.getnumIssue()+"',nbPage='"+article.getnbPage()+"',yearPub='"+article.getYearPub(null)+
			"',statuts='"+article.getStatus(false)+"' where idArt='"+id+"'");
	
	
	int executeUpdate2 = state.executeUpdate("UPDATE author set nameAut='"+auteur.getName()+"',surnameAut='"+auteur.getSurname()+
                                                 "',affiliation='"+auteur.getAffiliation(null)+"' where idAut='"+id+"'");  
	
	int executeUpdate3 = state.executeUpdate("UPDATE journal set nameJour='"+journal.getName()+"' where idJour='"+id+"'");
    
	        	System.out.println("\n Les donnÃ©es sont bien modifiÃ©"+executeUpdate1+"dans la table article ,"+executeUpdate2+"dans la table author");

    }
}
