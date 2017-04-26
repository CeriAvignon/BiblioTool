package webmining;

public class Reference {

	public int idArt;
	 public int idArt_Aritcle;
	 
	  public Reference (int idArt, int idArt_Article)
	  {
		  this.idArt = idArt;
		  this.idArt_Aritcle = idArt_Article;
	  }
	  
	   public int getId()
	   {
	   return idArt;
	   }
	   
	   public void setId(int idArt)
	   {
		   this.idArt = idArt;
	   }
	   
	   public int getIdArt(int idArt_Article){
		   return idArt_Article;
	   }
	   
	   public void setIdArt(int idArt_Article){
		   this.idArt_Aritcle = idArt_Article;
	   }
}
