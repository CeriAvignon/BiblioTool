package textmining;

import java.util.ArrayList;
public class Article
{	
	int maxRef = 1 ;
	int maxAuteur = 1 ;
	String mTitre;//Titre de l'article
	String [] mAuteur;//Les auteurs (il peut  y en avoir plusieur pour un même article)
	String mResume;//Petit resumé de l'article
	String mMotsClefs;//il y aura un separateur entre chaque mot-clefs. 
	String mDescriptionAuteur;//cf le sujet page 6 tout en haut
	int mAnnee;//Année de l'article
	String mDate;//date de l'article 
	String mSource;//Nom du journal par exemple
	ArrayList<Reference> mReferences;//Refercence liées a l'article
	public Article()
	{	
		this.mAuteur = new String[10];
		this.mReferences = new ArrayList<Reference>();
	}
	public  void SetTitre(String titre)
	{
		this.mTitre = titre;
	}
	public  void SetAuteur(String auteur)
	{
		maxAuteur = 1 ;
		this.mAuteur [0] = auteur;
	}
	public  void SetAuteur(String [] auteur)
	{
		maxAuteur = auteur.length;
		for(int i = 0 ; i< maxAuteur ; i++)
			this.mAuteur[i] = auteur[i];
	}
	public  void SetResume(String resume)
	{
		this.mResume = resume;
	}
	public  void SetDescriptionAuteur(String descriptionAuteur)
	{
		this.mDescriptionAuteur = descriptionAuteur;
	}
	public  void SetAnnee(int annee)
	{
		this.mAnnee = annee;
	}
	public  void SetSource(String Source)
	{
		this.mSource = Source ;	
	}
	public  void AddReferences(ArrayList<Reference> references)
	{
		mReferences = references;
	}
	public void ViewArticle()
	{
	    System.out.println("---------------------------------------------------------------" );
		System.out.println("Titre de l'article : " + this.mTitre);
		System.out.println("---------------------------------------------------------------" );
		for(int i = 0; i < maxAuteur ; i++ )
			System.out.println("Auteur de l'article : " + this.mAuteur[i]);
		System.out.println("---------------------------------------------------------------" );
		System.out.println("Resumé de l'article : " + this.mResume);
		System.out.println("---------------------------------------------------------------" );
		System.out.println("Description de l'auteur : "  + this.mDescriptionAuteur ) ;
		System.out.println("---------------------------------------------------------------" );
		System.out.println("Année de  publication de l'article : " + this.mAnnee );
		System.out.println("---------------------------------------------------------------" );
		for(int i = 0; i < mReferences.size() ; i++ )
			System.out.println("Reference de l'article : " + this.mReferences.get(i).toString() );
	    System.out.println("---------------------------------------------------------------" );
	}
	
}
