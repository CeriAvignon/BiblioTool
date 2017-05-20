package textmining;

public class Author {

	protected int idAut;
	  protected String nameAut="";
	  protected String surnameAut="";
	  protected String affiliation="";
	  
	  
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
}