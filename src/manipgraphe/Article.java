package manipgraphe;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Article")

public class Article {
    private int id;
	private String titre;
	private List<String> auteur; 
	private int annee;
	private String page;
	private int volume;
	private String journal;
	private String url;
	private List<String> references;
	private String statut;
	private static int i=0;
	public Article(){
	}
	
	public Article(int id, String titre, List<String> auteur, int annee, String page, int volume, String journal, String url,
			 List<String> references, String statut) {
		this.id=id;
		this.titre = titre;
		this.auteur = auteur;
		this.annee = annee;
		this.page = page;
		this.volume = volume;
		this.journal = journal;
		this.url = url;
		this.references = references;
		this.statut="false";
	}
	
	public void AfficherArticle(){
    	
    	for(int i=0;i<this.getAuteur().size();i++){
    		//System.out.println(getAuteur()[i]);
    	}
    
    }
	
    public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	 public String getStatut() {
		return this.statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	@XmlElement

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
    @XmlElement

	public List<String> getAuteur() {
		return auteur;
	}

	public void setAuteur(List<String> auteur) {
		this.auteur = auteur;
	}
    @XmlElement

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}
    @XmlElement

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
    @XmlElement

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
    @XmlElement

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}
    @XmlElement

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    @XmlElement

	public  List<String> getReferences() {
		return references;
	}

	public void setReferences( List<String> references) {
		this.references = references;
	}
 
	

	
}