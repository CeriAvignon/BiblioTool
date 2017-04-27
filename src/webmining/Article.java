
package webmining;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Article{
 
	  public int idArt;
	  public String titleArt="";
	  public String urlPage="";
	  public String urlPdf="";
	  public String doi="";
	  public int numIssue;
	  public int nbPage;
	  public Date yearPub;
	  public boolean status;
	  
	  public List<Author> listAuthor = new ArrayList<Author>(); //liste des auteurs
	  public List<Article> listArticle = new ArrayList<Article>();//liste des journals
	  public List<Reference> listReference= new ArrayList<Reference>(); //liste des article cité et l'article qui est cité
	  
	  
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
	  
	  public Article()
	  {
		  
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

	    	  public void setListAuthor(List<Author> listAuthor) {

	    	    this.listAuthor = listAuthor;
	    	  }
	     public List<Article> getListArticle(List<Article> listArticle) {
	    	 
	    	 return listArticle;
	     }
	     public void setListArticle(List<Article> listArticle) {

	    	    this.listArticle = listArticle;
	    	  }
	     
         public List<Reference> getListReference(List<Reference> listReference) {
	    	 
	    	 return listReference;
	     }
	     public void setListReference(List<Reference> listReference) {

	    	    this.listReference = listReference;
	     }
	     
	     public void addAuthor(Author author){
	    	 this.listAuthor.add(author);
	     }
	     
	     public void removeAuthor(Author author){
	    	 this.listAuthor.remove(author);
	     }
	     public void addArticle(Article article){
	    	 this.listArticle.add(article);
	     }	   
         
	     public void removeArticle(Article article){
	    	 this.listArticle.remove(article);
	     }	
	     public void addReference(Reference reference){
	    	 this.listReference.add(reference);
	     }
	     public void removeReference(Reference reference){
	    	 this.listReference.remove(reference);
	     }
}
	     
	    

