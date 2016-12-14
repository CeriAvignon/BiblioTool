package pre_affiche_noeud_selectionnee;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

import java.util.ListIterator;

@XmlRootElement(name="articles")

public class ListArticles {
	private ArrayList<Article> Articles=new ArrayList<Article>();

	//addToListe :methode permet d'ajouter les articles dans une liste
	public void addToListe(Article... a){
		for(Article i : a){
			Articles.add(i);
		}
	}
	@XmlElement(name="Articles")
	public ArrayList<Article> getArticles() {
		return Articles;
	}

	public void setArticles(ArrayList<Article> articles) {
		Articles = articles;
	}

	public Article Retourne_noeud_selectionnee (ListArticles  x, String  id_DOI_noeud_selectionnee)
	{
		
			
		Article b = null;
		ListIterator <Article> iterator_liste_article = (x.getArticles()).listIterator();
		 while(iterator_liste_article.hasNext()) 
		{
			 Article a =iterator_liste_article.next();
			 if (a.getId()==id_DOI_noeud_selectionnee)
				
				 b=a;
		}
		
			return b;

	}

	
	
	
}
