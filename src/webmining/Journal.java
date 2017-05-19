package webmining;

// la classe journal
public class Journal {
 
	  public int idJour;    // identifiant de journal
	  public String nameJour=""; // le titr du journal;
	  
	  //le constructeur de la classe
	   public Journal(int idJour, String nameJour)
	    {
		 this.idJour = idJour;
		 this.nameJour = nameJour;
	    }
	   public int getId()
	   {
		   return idJour;
	   }
	    public void setId(int idJour)
	      {
	    	this.idJour = idJour;
	      }
	    public String getName()
	       {
	        return nameJour;
	       }
	     public void setName(String nameJour)
	        {
	    	 this.nameJour = nameJour;
	        }
	     
	     public void AfficheJournal() //methode d'affichage 
		 	{
		 	    System.out.println("---------------------------------------------------------------" );
		 		System.out.println("l'identifiant de journal : " + this.idJour);
		 		System.out.println("---------------------------------------------------------------" );
		 		System.out.println("le titre du journal : " + this.nameJour );
		 		System.out.println("---------------------------------------------------------------" );
		 	    
		 	}
}
