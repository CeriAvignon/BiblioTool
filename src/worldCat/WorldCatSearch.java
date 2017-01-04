package searchingmodule.libraries.worldCat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Cristian
 */
public class WorldCatSearch {

    public WorldCatSearch(String author, String title, String output) {
         try {
            /*
             * Get a connection to the URL and start up a buffered reader.
             */

            /*
             * The complete pattern for an OpenSearch request is:
             * http://worldcat.org/webservices/catalog/search/opensearch?q=[query]&format=[atom|rss]&start=[start
             * position]&count=[maximum number of records to
             * return]&cformat=[citation format]&wskey=[your key]
             */
            long startTime = System.currentTimeMillis();

            String Querry1 = author + " " + title;
            Querry1 = Querry1.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", " ");
            Querry1 = Querry1.replace(" ", "%20");

            String APIKey = "hqHYXhCAUbREeV0AauMLymWi9OSgI3P01fAwagkqEWHfLqPIkf5XPOg4FTiFa6iJSrM5JXP758MByoEg";
            String OutputFormat = "atom";
            String maxNumber = "500";

            URL url = new URL("http://worldcat.org/webservices/catalog/search/worldcat/opensearch?q=" + Querry1 + "&start=1&count=" + maxNumber + "&format=" + OutputFormat + "&wskey=" + APIKey);
            url.openConnection();
            InputStream reader = url.openStream();

            /*
             * Setup a buffered file writer to write out what we read from the
             * website.
             */
            FileOutputStream writer = new FileOutputStream("WorldCatSearchOutput.xml");
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
        WorldCatXML PXML = new WorldCatXML(output);
    }       
}
