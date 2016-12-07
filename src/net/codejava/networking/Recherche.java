package net.codejava.networking;
 
public class Recherche {
	private int id;
	private String type;
	private String nom;
	private String url ;
	public Recherche(int id, String type, String nom, String url) {
		super();
		this.id = id;
		this.type = type;
		this.nom = nom;
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	} 
	
	
}
