
package webmining;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
/**
 * Citeseerxparser est la classe qui gere les pdf de Citeseerx
 * 
 * @author DAOU Ahmed Amine
 * @version 1.0
 */
public class Citeseerxparser {
   /**
 * recupere l'url de la page web 
 * 
 * @return L'URL du pdf
 * @param Citeseerlink
 *          l'url de la bibliotheque s'il  existe 
 * @throws MalformedURLException  Si jamais l'url est mal formée ou inexistant
 * #######(fonction a appeler pour recuperer le lien pdf)##############
 */
public static String getPdfCiteseer(String Citeseerlink)
    { String echec="echec recuperation pdf";
            Document doc;
        try {

            // se connecter 
            doc = Jsoup.connect(Citeseerlink).get();

            // parcourir tous les liens de la page web
            Elements links = doc.select("a[href]");
            for (Element link : links) {
            if( link.attr("href").contains("pdf")){
                // si le lien contient la chaine pdf
              
                //afficher le titre de la page
                    //  System.out.println("\033[0;1m"+"titre de la page : "+ doc.title());
                    System.out.println("Lien pdf : " + "http://citeseerx.ist.psu.edu"+link.attr("href"));
                     System.out.println("=============          =====================               ===============");

                return "http://citeseerx.ist.psu.edu"+link.attr("href");
            }}

        } catch (IOException e) {
            e.printStackTrace();
            return  echec ;
        }
   return  echec;
    }


  /*  public static void main(String[] args) {
    String pdf;
    //7 liens de test.
    pdf=getPdfCiteseer("http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.402.8376&rank=3");
    /*pdf=getPdfCiteseer("http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.368.1446&rank=2");
    pdf=getPdfCiteseer("http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.740.4494&rank=2");
    pdf=getPdfCiteseer("http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.332.3049&rank=1");
    pdf=getPdfCiteseer("http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.400.4958&rank=1");
    pdf=getPdfCiteseer("http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.402.8376&rank=3");
    pdf=getPdfCiteseer("http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.20.4681&rank=3");
    
    

    }*/
    

}