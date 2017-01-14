package biblioTool;

import javax.xml.bind.annotation.*;
@XmlRootElement(name="Article")
public class Article {
	private int id;
	private String Titre;
	private String [] Auteur; 
	private String Resume;
	private String MotsClefs;
	private String DescriptionAuteur;
	private int Annee;
	private String Source;
	private String [] References;
	private String Status;
	public Article(){
		
	}
	
	
	
	public Article(int Id,String titre, String[] auteur, String resume, String motsClefs, String descriptionAuteur, int annee,
			String source, String[] references,String statut) {
		super();
		id=Id;
		Titre = titre;
		Auteur = auteur;
		Resume = resume;
		MotsClefs = motsClefs;
		DescriptionAuteur = descriptionAuteur;
		Annee = annee;
		Source = source;
		References = references;
		Status=statut;
	}


    public void AfficherArticle(){
    	
    	for(int i=0;i<this.getAuteur().length;i++){
    		System.out.println(getAuteur()[i]);
    	}
    
    }
    @XmlElement
	public String getTitre() {
		return Titre;
	}
   
	public void setTitre(String titre) {
		Titre = titre;
	}
    @XmlElement
	public String[] getAuteur() {
		return Auteur;
	}
    
	public void setAuteur(String[] auteur) {
		Auteur = auteur;
	}
    @XmlElement
	public String getResume() {
		return Resume;
	}
    
	public void setResume(String resume) {
		Resume = resume;
	}
    @XmlElement
	public String getMotsClefs() {
		return MotsClefs;
	}
    
	public void setMotsClefs(String motsClefs) {
		MotsClefs = motsClefs;
	}
	public String getDescriptionAuteur() {
		return DescriptionAuteur;
	}
	public void setDescriptionAuteur(String descriptionAuteur) {
		DescriptionAuteur = descriptionAuteur;
	}
	@XmlElement
	public int getAnnee() {
		return Annee;
	}
	public void setAnnee(int annee) {
		Annee = annee;
	}
	public int getId() {
		return id;
	}
	public void setId(int Id) {
		id =Id;
	}
	@XmlElement
	public String getSource() {
		return Source;
	}
	public void setSource(String source) {
		Source = source;
	}
	
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String statut) {
		Status = statut;
	}
	@XmlElement
	public String[] getReferences() {
		return References;
	}
	public void setReferences(String[] references) {
		References = references;
	}
	
	

	
}


