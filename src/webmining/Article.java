package webmining;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
/**
*
* @author Tanguy Goussoumalet
*/
//la classe article
public class Article{
	  public int idArt;      //identifiant de l'article;
	  public String titleArt=""; // le titre de l'article;
	  public String urlPage=""; // url de la page de l'article;
	  public String urlPdf="";  // url de pdf de l'article;
	  public String doi="";    // num�ro d'identifiant num�rique d'objet;
	  public int numIssue;     // num�ro d'�mission de l'artile;
	  public int nbPage;      // nombre de page de l'article;
	  public Date yearPub;    // ann�e de publication de l'article;
	  public boolean status;  // status de l'article si l'article a �t� lue ou pas; 
	  public List<Author> listAuthor = new ArrayList<Author>(); //liste des auteurs associ� � cette article;
	  public List<Journal> listJournal = new ArrayList<Journal>();//liste des journals
	  public List<Reference> listReference= new ArrayList<Reference>(); //liste des article cit� et l'article qui est cit�
	  
	  //le constructeur de la classe article avec des arguments; 
	  public Article(int idArt, String titleArt, String urlPage,String urlPdf,String doi,int numIssue,
			  int nbPage,Date yearPub,boolean status)
	  {
		  this.idArt =idArt;
		  this.titleArt = titleArt;
		  this.urlPage = urlPage;
		  this.urlPdf = urlPdf;
		  this.doi = doi;
		  this.numIssue = numIssue;
		  this.nbPage = nbPage;
		  this.yearPub = yearPub;
		  this.status = status;
	  }
	  //le constructeur sans argument de la classe article
	   public Article(){
		   
	   }
	   
	     public int getId()
	     {
	    	 return idArt;
	     }
	     public void setId(int idArt)
	     {
	    	  this.idArt= idArt;
	     }
	  
	     public String getTittle()
	     {
	    	 return titleArt;
	     }
	     public void setTitle(String  titleArt)
	     {
	    	  this.titleArt = titleArt;
	     }
	     public String  getUrlPage()
	     {
	    	 return urlPage;
	     }
	     public void setUrlPage(String urlPage)
	     {
	    	  this.urlPage = urlPage;
	     }
	     
	     public String  getUrlPdf()
	     {
	    	 return urlPdf;
	     }
	     public void setUrlPdf(String urlPdf)
	     {
	    	  this.urlPdf = urlPdf;
	     } 
	     public String  getdoi()
	     {
	    	 return doi;
	     }
	     public void setdoi(String doi)
	     {
	    	  this.doi = doi;
	     } 
	     public int  getnumIssue()
	     {
	    	 return numIssue;
	     }
	     public void setnumIssue(int numIssue)
	     {
	    	  this.numIssue = numIssue;
	     }
	     
	     public int  getnbPage()
	     {
	    	 return nbPage;
	     }
	     public void setnbPage(int nbPage)
	     {
	    	  this.nbPage = nbPage;
	     }
	     public Date getYearPub(Date yearPub)
	     {
	    	 return yearPub;
	     }
	     public void setYearPub(Date yearPub)
	     {
	    	  this.yearPub = yearPub;
	     }
	     public boolean getStatus(boolean status)
	     {
	    	 return status;
	     }
	     public void setStatus(boolean status)
	     {
	    	  this.status= status;
	     }
	     public List<Author> getListAuthor() {

	    	    return listAuthor;
	    	  }

	    	  public void setListAuthor(List<Author> listauthor) {

	    	    this.listAuthor = listauthor;
	    	  }
	     public List<Journal> getListJournal(List<Journal> listjournal) {
	    	 
	    	 return listjournal;
	     }
	     public void setListJournal(List<Journal> listjournal) {

	    	    this.listJournal = listjournal;
	    	  } 
         public List<Reference> getListReference(List<Reference> listreference) {
	    	 
	    	 return listreference;
	     }
	     public void setListReference(List<Reference> listreference) {

	    	    this.listReference = listreference;
	     }
	     public void addAuthor(Author author){
	    	 this.listAuthor.add(author);
	     } 
	     public void addJournal(Journal journal){
	    	 this.listJournal.add(journal);
	     }	   
          	
	     public void addReference(Reference reference){
	    	 this.listReference.add(reference);
	     }  
	     public void AfficheArticle() //methode d'affichage
	 	{
	 	    System.out.println("---------------------------------------------------------------" );
	 		System.out.println("Titre de l'article : " + this.titleArt);
	 		System.out.println("---------------------------------------------------------------" );
	 		System.out.println("Ann�e de  publication de l'article : " + this.yearPub );
	 		System.out.println("---------------------------------------------------------------" );
	 		for(int i = 0; i <listAuthor.size() ; i++ )
	 			System.out.println("Auteur de l'article : " + this.listAuthor.get(i).toString());
	 		System.out.println("---------------------------------------------------------------" );
	 		for(int i = 0; i < listReference.size() ; i++ )
	 			System.out.println("Reference de l'article : " + this.listReference.get(i).toString() );
	 	    System.out.println("---------------------------------------------------------------" );
	 	}
	     
}
	     
	    

