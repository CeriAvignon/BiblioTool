package textmining;

public class Journal {
	 
	  public int idJour;
	  public String nameJour="";
	  
	  
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
}