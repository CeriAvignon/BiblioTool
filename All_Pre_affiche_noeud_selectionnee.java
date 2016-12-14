package pre_affiche_noeud_selectionnee;
//import article.*;
public class Pre_affiche_noeud_selectionnee {
	
	int a;
  public static void main(String []args){
	 // 	ArticleXml a= new ArticleXml();
	 	String []auteur={"auteur1","auteur2","auteur3"};
	 	String []reference={"ref1","ref2","ref3"};
	 	String id0,id1,id2,id3;
        id0="doi.org/10.1109/5.771070";
	 	id1 = "doi.org/10.1109/5.771073";
	 	id2 ="doi.org/10.1109/5.771074";
	 	id3 = "doi.org/10.1109/5.771075";
	
	    Article article1 =new Article("titre1",auteur,"resume1","motclef1","descript1",2001,"source1",reference,id0);
       // a.ObjectToXml(article1);
        
		Article article2 =new Article("titre2",auteur,"resume2","motclef2","descript2",2006,"source2",reference,id1);
	    Article article3 =new Article("titre3",auteur,"resume3","motclef3","descript3",2001,"source3",reference,id2);
	    Article article4 =new Article("titre4",auteur,"resume4","motclef4","descript4",2001,"source4",reference,id3);
	    ListArticles List=new ListArticles();
	    List.addToListe(article2,article3,article4);

		Article noeudSelectionné=List.Retourne_noeud_selectionnee(List, id3);

	 //   a.ListObjectToXml(List);
		   
		System.out.println(noeudSelectionné.getTitre());
	  
  }
}