package biblioTool;

import java.awt.List;

import biblioTool.Article;
import biblioTool.ListArticles;
public class test {

	public void modeLecture(ListArticles a,int id_article){
		   for( Article article : a.getArticles()){
			   if(article.getId()== id_article){
				   article.setStatus("True"); 
				   /*cette fonction permet de changer le statut
				    * de l article avec id_article  dans la BDD
				    * réalisée par le group de BDD*/
	      //ChangeStatus(id_article); 
				   
	
			   }}
	}
	
	
	  public static void main(String []args){
		
			 	String []auteur={"auteur1","auteur2","auteur3"};
			 	String []reference={"ref1","ref2","ref3"};
			    Article article1 =new Article(1,"titre1",auteur,"resume1","motclef1","descript1",2001,"source1",reference,"false");
			    Article article2 =new Article(2,"titre2",auteur,"resume2","motclef2","descript2",2006,"source2",reference,"false");
			    Article article3 =new Article(3,"titre3",auteur,"resume3","motclef3","descript3",2001,"source3",reference,"false");
			    Article article4 =new Article(4,"titre4",auteur,"resume4","motclef4","descript4",2001,"source4",reference,"false");
			    
			    ListArticles List=new ListArticles();
			    List.addToListe(article1,article2,article3,article4);
                test ab = new test();
			    ab. modeLecture(List,2);
			    for( Article article : List.getArticles())
			    {
			    	 System.out.println(article.getStatus());
			    	
			    	
			    }
			  
			  
		  }
	
}
