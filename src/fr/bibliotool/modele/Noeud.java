package fr.bibliotool.modele;

public class Noeud {
	private int id;
	private Article article;
	private static int i=0;
	public Noeud() {
	}
	public Noeud( Article article) {
		this.id = i++;
		this.article = article;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	
	
	
}
