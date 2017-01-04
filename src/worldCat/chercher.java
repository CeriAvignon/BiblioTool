package searchingmodule.libraries.worldCat;

public class chercher {
	  public chercher() {
	        siteFactory();
	    }

	    public void siteFactory() {
	        
	        String worldCatOutput = "outputWorldCat.xml";
	       
	        String author = "Mihai Alex;Diana Trandabat";
	        String article = "";
	        
	       
	        WorldCat worldCatSearch = new WorldCat(author, article, worldCatOutput);
	        
	     

	        /*
	         * 4 Threads
	         */

	       
	        new Thread(worldCatSearch).start();
	 //       new Thread(dblpSearch).start();
	    }
	}