package article;
import javax.xml.bind.annotation.*;
@XmlRootElement(name="Article")
public class Article {

	private String Titre;
	private String [] Auteur; 
	private String Resume;
	private String MotsClefs;
	private String DescriptionAuteur;
	private int Annee;
	private String Source;
	private String [] References;
	
	public Article(){
		
	}
	
	
	
	public Article(String titre, String[] auteur, String resume, String motsClefs, String descriptionAuteur, int annee,
			String source, String[] references) {
		super();
		Titre = titre;
		Auteur = auteur;
		Resume = resume;
		MotsClefs = motsClefs;
		DescriptionAuteur = descriptionAuteur;
		Annee = annee;
		Source = source;
		References = references;
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
	@XmlElement
	public String getSource() {
		return Source;
	}
	public void setSource(String source) {
		Source = source;
	}
	@XmlElement
	public String[] getReferences() {
		return References;
	}
	public void setReferences(String[] references) {
		References = references;
	}
	
	

	
}


