package net.codejava.networking;
 
public class Recherche {
	private int id;
	private String chemin;
	private String nom;
	private String url ;
	public Recherche(int id, String chemin, String nom, String url) {
		super();
		this.id = id;
		this.chemin = chemin;
		this.nom = nom;
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getnom() {
		return nom;
	}
	public void setnom(String nom) {
		this.nom = nom;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	} 
	public String getchemin() {
		return chemin;
	}
	public void setchemin(String chemin) {
		this.chemin = chemin;
	}
	
}
