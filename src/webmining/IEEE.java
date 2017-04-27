package webmining;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import webmining.SearchIeee;


public class IEEE  implements Runnable{
    private String author;
    private String article;
    private String uri;
    
    public IEEE(String author, String article, String uri) {
        System.out.println("IEEE Search");
        this.author = author;
        this.article = article;
        this.uri = uri;
    }

    @Override
    public void run() {
        System.out.println("IEEE Implementation");
        SearchIeee i = new SearchIeee(uri, article, author);
     i.toString();
  
    }
   /* public static void main(String[] args) {
		  String ieeeOutput = "outputIeee.xml";
	        
	        String author = "";
	        String article = "informatique";
	        
	        IEEE ieeeSearch = new IEEE(author, article, ieeeOutput);
	      


	        new Thread(ieeeSearch).start();
		 
	 }*/

    
}
