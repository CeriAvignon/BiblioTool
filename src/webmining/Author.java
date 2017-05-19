package webmining;

//la classe auteur
public class Author {

	  protected int idAut;    //identifiant de l'auteur;
	  protected String nameAut=""; // le nom de l'auteur;
	  protected String surnameAut=""; //le prenom de l'auteur;
	  protected String affiliation=""; 
	// le constructeur de la classe auteur
	  public Author(int idAut, String nameAut, String surnameAut,String affiliation) 
	  {
		  this.idAut =idAut;
		  this.nameAut = nameAut;
		  this.surnameAut = surnameAut;
		  this.affiliation = affiliation;
	  }
	     public int getId()
	     {
	    	 return idAut;
	     }
	     public void setId(int idAut)
	     {
	    	  this.idAut= idAut;
	     }  
	     public String getName()
	     {
	    	 return nameAut;
	     }
	     public void setName(String  nameAut)
	     {
	    	  this.nameAut = nameAut;
	     }
	     public String  getSurname()
	     {
	    	 return surnameAut;
	     }
	     public void setSurname(String surnameAut)
	     {
	    	  this.surnameAut = surnameAut;
	     }
	     public String getAffiliation(String affiliation)
	     {
	    	 return affiliation;
	     }
	     public void setAffiliation(String affiliation)
	     {
	    	  this.affiliation= affiliation;
	     }
	     
	     public void AfficheAuteur() //methode d'affichage
		 	{
		 	    System.out.println("---------------------------------------------------------------" );
		 		System.out.println("l'identifiant de l'auteur : " + this.idAut);
		 		System.out.println("---------------------------------------------------------------" );
		 		System.out.println("le nom de l'auteur : " + this.nameAut );
		 		System.out.println("---------------------------------------------------------------" );
		 		System.out.println("le prenom de l'auteur : " + this.surnameAut);
		 		System.out.println("---------------------------------------------------------------" );
		 		System.out.println("affiliation de l'auteur : " + this.affiliation );
		 	}
	     
}
