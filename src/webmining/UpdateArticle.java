
package webmining;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateArticle {
	
	// Déclaration

    public static ResultSet rs;
    public int nbr=1;

    String query = "";

    public Article articles;

  		Statement st=null ;


	 public Connection con;

    public void updateArticle() {
   	
  	  Connection con= null;
  	  
  	 
  		con = Connexion.ConnecterDB();
	   	  
  	  //Création d'un statement

 
	   	  // exécution requéte
  		
  		Scanner sc = new Scanner(System.in);
  		System.out.println("Veuillez saisir l'ID de l'article à Modifier :");
  		String str = sc.nextLine();
  		System.out.println("Vous avez saisi : " + str);
  		int entier= Integer.parseInt(str);
  		
  		//---------- String titreArt
  		System.out.println("Veuillez saisir titre article :");
  		String titleArt = sc.nextLine();
  		
  		//---------- String urlPage
  		
  		System.out.println("Veuillez saisir url de page :");
  		String urlPage = sc.nextLine();
  		//-----------String urlPdf
  		
  		System.out.println("Veuillez saisir url de pdf  :");
  		String urlPdf = sc.nextLine();
  		
  		//---------- String doi
  		System.out.println("Veuillez saisir de doi :");
  		String doi = sc.nextLine();
  		
  		//---------- int numVol
  		
  		System.out.println("Veuillez saisir num vol :");
  		String numVol = sc.nextLine();
  		//---------- int numIssue
  		System.out.println("Veuillez saisir num issue :");
  		String numIssue = sc.nextLine();
  		
  		//---------- int numPage
  		System.out.println("Veuillez saisir num page :");
  		String numPage = sc.nextLine();
  		
  		//---------- Date yearPub
  		System.out.println("Veuillez saisir year pub :");
  		String yearPub = sc.nextLine();
  		
  		//---------- String status
  		System.out.println("Veuillez saisir status :");
  		String status = sc.nextLine();
  		
  		//---------- int numPage
  		System.out.println("Veuillez saisir num page :");
  		String az = sc.nextLine();
  		
  		//---------- int numPage
  		System.out.println("Veuillez saisir nombre page :");
  		String nbPage = sc.nextLine();
  		
  		
  		
  	  try {
  		st = con.createStatement();
	   	   
	   	String sql="UPDATE article SET titleArt='"+titleArt+"',urlPage='"+urlPage+"',urlPdf='"+urlPdf+"',doi='"+doi+"',numVol='"+numVol+"',numIssue='"+numIssue+"',numPage='"+numPage+"',nbPage='"+nbPage+"',yearPub='"+yearPub+"',status='"+status+"' WHERE idArt='"+entier+"' ";


		st.executeUpdate(sql);
		
			

  	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
          
	   	   
  	     try { 

  	          //Etape 5: libérer ressources de la mémoire
  	          con.close();
  	         } catch (SQLException e) {
  	          e.printStackTrace();
  	          
  	          
  	         }

  	  



	
	
}




}
