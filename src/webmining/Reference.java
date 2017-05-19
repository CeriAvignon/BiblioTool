package webmining;

// la classe reference
public class Reference {

	public int idArt;    //identifiant de l'article source ;
	 public int idArt_Article; // identifiant de l'article reference;
	 
	  public Reference (int idArt, int idArt_Article) // le constructeur de la classe
	  {
		  this.idArt = idArt;
		  this.idArt_Article = idArt_Article;
	  }
	    public int getId()
	   {
	   return idArt;
	   }
	   
	   public void setId(int idArt)
	   {
		   this.idArt = idArt; }
	   
	   public int getIdArt(int idArt_Article){
		   return idArt_Article;
	   } 
	   public void setIdArt(int idArt_Article){
		   this.idArt_Article = idArt_Article;
	   }   
	   public void AfficheRef() // methode d'affichage
	 	{
	 	    System.out.println("---------------------------------------------------------------" );
	 		System.out.println("l'identifiant de l'article source : " + this.idArt);
	 		System.out.println("---------------------------------------------------------------" );
	 		System.out.println("l'identifiant de l'article reference  : " + this.idArt_Article );
	 		System.out.println("---------------------------------------------------------------" );
	 	    
	 	}
}
