package webmining;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ShowLastArticles {
	
	// Déclaration

    public static ResultSet rs;
    public int nbr=1;

    String query = "";

    public Article articles;

  		Statement st=null ;


	 public Connection con;

    public Article getLastArticle() {
   	
  	  Connection con= null;
  	  
  	 
  		con = Connexion.ConnecterDB();
	   	  
  	  //Création d'un statement

 
	   	  // exécution requéte
  	  try {
  		st = con.createStatement();
	   	   String sql ="select * from article order by idArt DESC limit 1 ";

		rs= st.executeQuery(sql);

  	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
            // création d'un objet article		 			
	/*
		List<Article> list = new ArrayList<Article>();
   */
			 // Parcourir de la resultat de select
  	  
  	    try {
		while(rs.next()){ 
	         articles = new Article();

	         // Remplir de l'objet article
				 articles.idArt=rs.getInt("idArt");
				  articles.titreArt=rs.getString("titleArt");
				 articles.urlPage=rs.getString("urlPage");
				  articles.urlPdf=rs.getString("urlPdf");
				  articles.doi=rs.getString("doi");
				  articles.numVol=rs.getInt("numVol");
				  articles.numIssue=rs.getInt("numIssue");
				  articles.numPage=rs.getInt("numPage");
				  articles.nbPage=rs.getInt("nbPage");

				  articles.yearPub=rs.getDate("yearPub");
				  articles.status=rs.getString("status");
				  articles.dateInsertion=rs.getDate("dateInsertion");

				  


				//nbr++;
		}			 
  	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
				
	
	
	//Création d'une liste des articles
			
	
	// Remplir la liste avec des articles
	
			/*for(int i = 0; i < nbr-1; i++)
			{
				System.out.println("donnée à l'indice " + i + " = " + list.get(i));
			}
	*/
	   	   
  	     try { 

  	          //Etape 5: libérer ressources de la mémoire
  	          con.close();
  	         } catch (SQLException e) {
  	          e.printStackTrace();
  	          
  	          
  	         }

  	  
  	     
	
  	     System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");

  	     System.out.println(" l'id de l'article   "+articles.idArt);
  	     System.out.println("Le nombre de page    "+articles.nbPage);
  	     System.out.println("Le titre d'article   "+articles.titreArt);
  	     System.out.println("Date d'insertion     "+articles.dateInsertion);


  	     return articles;
	
	
}



}







