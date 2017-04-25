
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
	  public List<Journal> listJournal = new ArrayList<Journal>();//liste des journals
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
	     public List<Journal> getListJournal(List<Journal> listJournal) {
	    	 
	    	 return listJournal;
	     }
	     public void setListJournal(List<Journal> listJournal) {

	    	    this.listJournal = listJournal;
	    	  }
	     
         public List<Reference> getListReference(List<Reference> listReference) {
	    	 
	    	 return listReference;
	     }
	     public void setListReference(List<Reference> listReference) {

	    	    this.listReference = listReference;
	     }
	     
	     public void addAuthor(Author Author){
	    	 this.listAuthor.add(Author);
	     }
	     
	     public void removeAuthor(Author Author){
	    	 this.listAuthor.remove(Author);
	     }
	     public void addJournal(Journal Journal){
	    	 this.listJournal.add(Journal);
	     }	   
         
	     public void removeJournal(Journal Journal){
	    	 this.listJournal.remove(Journal);
	     }	
	     public void addReference(Reference Reference){
	    	 this.listReference.add(Reference);
	     }
	     public void removeReference(Reference Reference){
	    	 this.listReference.remove(Reference);
	     }
}
	     
	    

