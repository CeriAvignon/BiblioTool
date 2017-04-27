package webmining;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * author :HADDOU Ayoub	
 * 
 * */




public class Citeseerx {
	
	
	
	/*   liste des modèles Utilisé pour l'extraction des données ou pour la formulation des requetes*/
	    final static int MAX_PAGES_TO_LOAD = 8;
	    final static String QUERY_MARKER = "___QUERY___";
	    final static String URL_START = "http://citeseer.ist.psu.edu";
	    final static String SEARCH_URL = URL_START+"/search?q=";
	    final static String SEARCH_end = "&submit=Search&sort=rlv&t=doc";
	    final static Pattern CITE_LINK_PATTERN = Pattern.compile("<a class=\"remove doc_details\" href=\"(.*)\">");
	    final static String basePattern = "<meta name=\""+QUERY_MARKER+"\" content=\"(.*)\" />";
	    final static Pattern titlePattern = Pattern.compile(basePattern.replace(QUERY_MARKER, "citation_title"));
	    final static Pattern authorPattern = Pattern.compile(basePattern.replace(QUERY_MARKER, "citation_authors"));
	    final static Pattern yearPattern = Pattern.compile(basePattern.replace(QUERY_MARKER, "citation_year"));
	    final static Pattern abstractPattern = Pattern.compile("<h3>Abstract</h3>\\s*<p>(.*)</p>");
	    
	    
	    public static ArrayList<String> fields = new ArrayList<String>();
	    public static ArrayList<ArrayList<String>> _fields  = new ArrayList<ArrayList<String>>();
	    private static boolean stopFetching = false;
		//private static List<String> id;
	   
		
		public Citeseerx(){
			
		}
		
	/* cette fonction permet de filter la saisie si auteur titre ou les deux
	 * 
	 *   cette fonction retourne une Liste  d'articles "string"  enregistré  dans une liste pour chaque article   
	 *   
	 *   */	
		
		public static List<ArrayList<String>> Citeseerx(String author ,String title){
			
			   if (title.length() > 0 && author.length() > 0) {
		            String sLink = SEARCH_URL+"title%3A" + title + "+AND+author%3A" + author +"&sort=cite&t=doc";
		            System.out.println(sLink);
		            processQuery(sLink);
		        } else if (title.length() > 0 && author.length() == 0) {
		            String sLink = SEARCH_URL+"title%3A" + title + "&sort=cite&t=doc";
		            System.out.println(sLink);
		            processQuery(sLink);
		        } else if (title.length() == 0 && author.length() > 0) {
		            String sLink = SEARCH_URL+"author%3A" + author +"&sort=cite&t=doc";
		            System.out.println(sLink);
		            processQuery(sLink);
		        }
			   
			return _fields;   
		}
		/* permet l'affichage des liste d'articles enregistrée */
		public static void affiche(){	
			
			for (ArrayList<String> f : _fields) {
	    		  for (String e : f ){
		    		    System.out.println(e);
	    		  		}	
		        }
	
		}
	/* fonction principale qui en */
		public static boolean processQuery(String query) {

	        try {
	            List<String> citations = getCitations(query);
	            
	            for (String citation : citations) {
	            	System.out.println(citation);
	                	
	                 fields=getSingleCitation(citation);
	                 
	                  _fields.add(fields);
	                  System.out.println(_fields);    
	            }

	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

		public String getTitle() {
	        return "CiteSeer";
	    }

	    public String getKeyName() {
	        return "CiteSeer";
	    }

	 

	    public String getHelpPage() {
	        return "CiteSeerHelp.html";
	    }

	  

	    public void stopFetching() {
	        stopFetching = true;
	    }

	/*retu*/	
	
		protected static List<String> getCitations(String query) throws IOException {
	        String urlQuery;
	        ArrayList<String> ids = new ArrayList<String>();
	        try {
	            urlQuery = query;
	            int count = 1;
	            String nextPage = null;
	            while (((nextPage = getCitationsFromUrl(urlQuery, ids)) != null)
	                    && (count < MAX_PAGES_TO_LOAD)) {
	                urlQuery = nextPage;
	                count++;
	                System.out.println(count);
	                if (stopFetching)
	                    break;
	            }
	            return ids;
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException(e);
	        }
	    }
	



	static String getCitationsFromUrl(String urlQuery, List<String> ids) throws IOException {
		    URL url = new URL(urlQuery);
		    URLDownload ud = new URLDownload(url);
		    ud.download();

		    String cont = ud.getStringContent();
		    //System.out.println(cont);
		    Matcher m = CITE_LINK_PATTERN.matcher(cont);
		    while (m.find()) {
		        ids.add(URL_START+m.group(1));
		    }

		    return null;
		}


	  
	    
	    
	    
	    protected static ArrayList<String> getSingleCitation(String urlString) throws IOException {

	        URL url = new URL(urlString);
	        URLDownload ud = new URLDownload(url);
	        ud.setEncoding("UTF8");
	        ud.download();

	        String cont = ud.getStringContent();

	 
	        Matcher m = titlePattern.matcher(cont);
	        if (m.find()) {

	              
	        	 fields.add("title : "+m.group(1));


	            // Find authors:
	            m = authorPattern.matcher(cont);
	            if (m.find()) {
	                String authors = m.group(1);
	                fields.add("author : "+authors);
	            }

	            m = yearPattern.matcher(cont);
	            if (m.find())
	            	 fields.add("year : "+m.group(1));
	            
	            m = abstractPattern.matcher(cont);
	            if (m.find())
	           
	            fields.add("abstract : "+m.group(1));
	          
	       
	            fields.add("url : "+urlString);
	            
	            fields.add("------------------------------------------------------------------------------------");
	           
	            
	        }
			return fields;

	    }


	    
	   
	    /*
	
	    public static void main (String[] args) throws IOException {
	    	
	    	String author="nicolam";
	    	String title="dijkstra";
	    	 String sLink = "http://citeseerx.ist.psu.edu/search?q=author%3A" + author +"&sort=cite&t=doc";
	    	 //String sLink = "http://citeseerx.ist.psu.edu/search?q=title%3A" + title + "&sort=cite&t=doc";
	    	 System.out.println(sLink);
	    	// id = new ArrayList<String>();
	    	  // getCitations(author);
	    	 

	    	 //Citeseerx.processQuery("yass");
	    	  //Citeseerx.getSingleCitation("http://citeseerx.ist.psu.edu/search?q=title%3A" + title + "&sort=cite&t=doc");
	    	  
	    	 
	    	  //id =Citeseerx.getCitations("dijkstra");
	           
	    	 // System.out.println(id);
	    	 
	   		 // Citeseerx.processQuery(i);
	  	   // List<Map<String , String>> _fields  = new ArrayList<Map<String,String>>();
	    	 List<ArrayList<String>> exec =new ArrayList<ArrayList<String>>();
	    	
 	           exec= Citeseerx("nicola","");
	    	  /*for (Map.Entry<String,String> e :  exec.entrySet()){
	    		    System.out.println(e.getKey() + " : " + e.getValue());
	    		}
	    	  
	    	  for (ArrayList<String> map : exec) {
	    		  for (String e : map ){
		    		    System.out.println(e);
	    	                  
	    	    }
	    	  }
	    	  Citeseerx.affiche();
	    	Citeseerx.getSingleCitation("http://citeseer.ist.psu.edu/viewdoc/summary;jsessionid=503DA67FCD9B61480821EAAD1A26CCE3?doi=10.1.1.332.3710&rank=2");
	    	  Citeseerx.affiche();
	    	  Citeseerx.getSingleCitation("http://citeseer.ist.psu.edu/viewdoc/summary;jsessionid=15A4C11C79AC9243151AF72CEE60B388?doi=10.1.1.473.581&rank=7");
	    	  Citeseerx.affiche();
	    }	    */

}

