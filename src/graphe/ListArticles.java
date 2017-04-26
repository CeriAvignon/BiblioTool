package graphe;

import javax.xml.bind.annotation.*;

import java.util.ArrayList;

@XmlRootElement(name = "articles")

public class ListArticles {
	public ArrayList<Article> Articles = new ArrayList<Article>();

	// addToListe :methode permet d'ajouter les articles dans une liste
	public void addToListe(Article... a) {
		for (Article i : a) {
			Articles.add(i);
		}
	}

	@XmlElement(name = "Articles")
	public ArrayList<Article> getArticles() {
		return Articles;
	}

	public void setArticles(ArrayList<Article> articles) {
		Articles = articles;
	}

}