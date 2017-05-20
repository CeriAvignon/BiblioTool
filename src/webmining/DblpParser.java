package webmining;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
/**
 * 
 * @author DAOU Ahmed Amine
 * la classe qui gere les pdf de dblp
 */
public class DblpParser {
    
    public static String linkExtract(String strr,String chaine){
        /*renvoi l'URL contenant la chaine passée en parametre*/
        
     Document doc;
        try {

            // need http protocol
            doc = Jsoup.connect(strr).get();

            // get page title
            String title = doc.title();
            System.out.println("title : " + title);

            // get all links
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                if (link.attr("href").contains(chaine)){
                // get the value from href attribute
               
                return (link.attr("href"));

            }}

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    
public static String getPdfLinkFrom(String T){
 Document doc;
        try {

            // need http protocol
            doc = Jsoup.connect(T).get();

            // get page title
            String title = doc.title();
            System.out.println("title : " + title);

            // parcourir tout les liens de la page 
            Elements links = doc.select("a[href]");
            for (Element link : links) {//pour tout les elements de type lien
                if (link.attr("name").toUpperCase().contains("PDF")||link.attr("href").toUpperCase().contains("PDF") 
                        ||link.attr("title").contains("pdf")){
                //get the value from href attribute
               System.out.println("\nlink : " + link.attr("href"));
                System.out.println("text : " + link.text());
                return link.attr("href");
        //        return (link.attr("href"));

            }}

        } catch (IOException e) {
            e.printStackTrace();
        }
      return "";  
    }


/**
 * ###fonction a appeler pour recuperer le pdf de dblp###
 * @param str
 * @return 
 */
public static String documentExistGoTo(String str ){
    /*cette fonction prend en parametre 
    un lien d'une bibliotheque*/
    /*Elle verifie si un document est mis en ligne dans la  page de la bibliotheque*/
    
 Document doc;
 String s,T,pdf;
  String   T2;
        try {
            //get all images
            doc = Jsoup.connect(str).get();/*recuperer le fichier html
                                            a partir du lien passé en parametre*/
            Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
            for (Element image : images) {
                if (image.attr("alt").contains("no document")){
                    //si il n'ya pas de documents
                    s="pas de document pdf";
                System.out.println("\nsrc : " + image.attr("src"));
                System.out.println("alt : " + image.attr("alt"));
                 System.out.println(s);
                }
                                    

               else if (image.attr("alt").contains("view") && image.attr("src").contains("venues.dark.hollow")){
                   //si le lien ne donne pas un fichier pdf 
                   //mais un lien vers plusieurs fichiers pdf 
                   //donc la recherche est imprecise
                    s="recherche imprecise ,resultat sans apport,lien vers plusieurs documents";
                System.out.println("\nsrc : " + image.attr("src"));
                System.out.println("alt : " + image.attr("alt"));
                                    System.out.println(s);}
                else if (image.attr("alt").contains("view") && image.attr("src").contains("paper.dark.hollow")){
                    s="Liens vers la bibliotheque existe";
                     System.out.println(s);
                   //aller vers la bibliotheque via le doi
                 
                   T=linkExtract(str,"doi.");
                   
                   //renvoie le lien contenant la chaine doi
                  System.out.println(T);
                 pdf= getPdfLinkFrom(T);
                  return pdf;
                /*System.out.println("\nsrc : " + image.attr("src"));
                System.out.println("alt : " + image.attr("alt"));*/
            }}

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";}




   /* public static void main(String[] args) {
        String pdflink;
        
pdflink=documentExistGoTo("http://dblp.org/rec/books/daglib/0002358");//V //exemple1
pdflink=documentExistGoTo("http://dblp.org/rec/conf/javacard/2000");//V   //exemple2
pdflink=documentExistGoTo("http://dblp.org/rec/journals/csur/HartelM01");//V //Exemple3

}*/}