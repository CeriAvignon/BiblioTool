package fatima;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class WorldCatSearch {

    public WorldCatSearch(String author, String title, String output) {
         try {
            /*
             * Obtenir une connexion à l'URL et démarrer un lecteur tamponné.
             */

            /*
             * Le modèle complet d'une requête OpenSearch est:
              * Http://worldcat.org/webservices/catalog/search/opensearch?q=[query]&format=[atom|rss]&start=[start
              * Position] & count = [nombre maximum d'enregistrements à
              * Return] & cformat = [format de citation] & wskey = [votre clé]
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
            Configurer un graveur de fichier tampon pour écrire ce que nous lisons à partir du
              * site Internet.
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

            System.out.println("terminé. " + (new Integer(totalBytesRead).toString()) + "Octets lus(" + (new Long(endTime - startTime).toString()) + " millsecondes).\n");
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
