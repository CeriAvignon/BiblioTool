package webmining;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
/**
 * IEEEparser est la classe qui gere les pdf de IEEE
 * @author DAOU Ahmed Amine
 * @version 1.0
 */
public class IEEEparser {
/**
 * getPdfID 
 * @param ieeeLink
 * @return renvoie l'id du pdf
 */
    public static String getPdfID(String ieeeLink){
    
   int debut=ieeeLink.indexOf("document");
  
   int fin= ieeeLink.length();
    String newStr = ieeeLink.substring(debut+"document".length()+1,fin);
    return newStr;
    }
    /**
     * forme le lien du pdf #######(fonction a appeler pour recuperer le lien pdf)##############
     * @param ieeeLink
     * @return le lien vers le pdf
     */
public static String getPDFIeee( String ieeeLink)
{   String permission=" ";
    Document doc;
        try {

      
            doc = Jsoup.connect("http://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber="+getPdfID(ieeeLink)).get();
            
            

        } catch (IOException e) {
            e.printStackTrace();
        }
 return "http://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber="+getPdfID(ieeeLink);
    }


   /*public static void main(String[] args) {

      String pdfLink= getPDFIeee("http://ieeexplore.ieee.org/document/7898579/");
	}*/
}
