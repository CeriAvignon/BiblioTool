/**
@author fatima*/
package webmining;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.omg.CORBA.portable.InputStream;

public class SearchIeee {
	

	    public SearchIeee(String outFileName, String title, String author) {

	        try {
	            /*
	             * Obtenez une connexion à l'URL et lancez un lecteur reader.
	             */
	            long startTime = System.currentTimeMillis();

	            String sLink = "http://ieeexplore.ieee.org/gateway/ipsSearch.jsp?";
	            String maxNumber = "2000";


	            String tit = title.trim();
	            String aut = author.trim();

	            title = formatInput(title);
	            author = formatInput(author);

	            String querry = new String();
	            if (tit.length() > 0 && aut.length() > 0) {
	                querry = "ti=" + title + "&au=" + author;
	            } else if (tit.length() > 0 && aut.length() == 0) {
	                querry = "ti=" + title;
	            } else if (tit.length() == 0 && aut.length() > 0) {
	                querry = "au=" + author;
	            }
	            sLink = sLink + querry + "&hc=" + maxNumber;


	            System.out.println(sLink);


	            URL url = new URL(sLink);
	            url.openConnection();
	           java.io.InputStream reader =   url.openStream();

	            /*
	             *Configurez un éditeur de fichiers tampon pour écrire ce que nous lisons à partir du
                 * site Internet.
	             */
	            FileOutputStream writer = new FileOutputStream("brutIeee.xml");
	            byte[] buffer = new byte[153600];
	            int totalBytesRead = 0;
	            int bytesRead = 0;

	            while ((bytesRead = reader.read(buffer)) > 0) {
	                writer.write(buffer, 0, bytesRead);
	                buffer = new byte[153600];
	                totalBytesRead += bytesRead;
	            }

	            long endTime = System.currentTimeMillis();

	            System.out.println("Done. " + (new Integer(totalBytesRead).toString()) + " bytes read (" + (new Long(endTime - startTime).toString()) + " millseconds).\n");
	            writer.close();
	            reader.close();          
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	   new ParseIeee("brutIeee.xml", outFileName);
	    }

	    private String formatInput(String input) {
	        if (input.contains(" ")) {
	            input = input.replace(" ", "%20");
	        }
	        return input;
	    }
	}


