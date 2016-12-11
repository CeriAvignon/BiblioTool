package prog;
import article.*;
public class Main {
	
	
  public static void main(String []args){
	 ArticleXml a= new ArticleXml();
	 	String []auteur={"auteur1","auteur2","auteur3"};
	 	String []reference={"ref1","ref2","ref3"};
	    Article article1 =new Article("titre1",auteur,"resume1","motclef1","descript1",2001,"source1",reference);
        a.ObjectToXml(article1);
	    Article article2 =new Article("titre2",auteur,"resume2","motclef2","descript2",2006,"source2",reference);
	    Article article3 =new Article("titre3",auteur,"resume3","motclef3","descript3",2001,"source3",reference);
	    Article article4 =new Article("titre4",auteur,"resume4","motclef4","descript4",2001,"source4",reference);
	    ListArticles List=new ListArticles();
	    List.addToListe(article1,article2,article3,article4);
	    a.ListObjectToXml(List);
	  
	  
  }
}
