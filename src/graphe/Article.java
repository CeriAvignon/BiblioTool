package graphe;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Article")

public class Article {
	private int idArt;
	private String titleArt;
	private String doi;
	private List<Author> author;
	private int pubYear;
	private int numPage;
	private int nbPage;
	private int numVol;
	private int numIssue;
	private String journal;
	private String urlArt;
	private List<Reference> references;
	private Boolean status;

	public Article() { }

	public Article(int idArt, String titleArt,List<Author> author , String doi, int pubYear, int numPage, int nbPage, int numVol,
			int numIssue, String journal, String urlArt, List<Reference> references, Boolean status) {
		super();
		this.idArt = idArt;
		this.titleArt = titleArt;
		this.doi = doi;
		this.pubYear = pubYear;
		this.numPage = numPage;
		this.nbPage = nbPage;
		this.numVol = numVol;
		this.numIssue = numIssue;
		this.journal = journal;
		this.urlArt = urlArt;
		this.references = references;
		this.status = status;
		this.author = author;
	}

	public int getIdArt() {
		return idArt;
	}

	public void setIdArt(int idArt) {
		this.idArt = idArt;
	}

	public String getTitleArt() {
		return titleArt;
	}
	
	public List<Author> getAuthor() {
		return author;
	}

	public void setAuthor(List<Author> author) {
		this.author = author;
	}

	public void setTitleArt(String titleArt) {
		this.titleArt = titleArt;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public int getPubYear() {
		return pubYear;
	}

	public void setPubYear(int pubYear) {
		this.pubYear = pubYear;
	}

	public int getNumPage() {
		return numPage;
	}

	public void setNumPage(int numPage) {
		this.numPage = numPage;
	}

	public int getNbPage() {
		return nbPage;
	}

	public void setNbPage(int nbPage) {
		this.nbPage = nbPage;
	}

	public int getNumVol() {
		return numVol;
	}

	public void setNumVol(int numVol) {
		this.numVol = numVol;
	}

	public int getNumIssue() {
		return numIssue;
	}

	public void setNumIssue(int numIssue) {
		this.numIssue = numIssue;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getUrlArt() {
		return urlArt;
	}

	public void setUrlArt(String urlArt) {
		this.urlArt = urlArt;
	}

	public List<Reference> getReferences() {
		return references;
	}

	public void setReferences(List<Reference> references) {
		this.references = references;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	

	

}