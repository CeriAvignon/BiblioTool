package download_file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Article {
	
       public static   ArrayList<String> Resultat = new ArrayList<>();
       private static final int BUFFER_SIZE = 4096;
    
    public Article() {
           }
        public static List<String> recupurl(){
               try {
        Class.forName("com.mysql.jdbc.Driver");
       /** on crée une connection du package java.sql	**/	
          Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava","root" ,"");
 
       /** la requette sql **/
          String queryString = "select url from recherche";
       
       /**création d'un objet preparedstatement pour une requette qui récupére les url **/
       
           Statement stm = conn.createStatement();
           ResultSet rs = stm.executeQuery(queryString);
           ResultSetMetaData res = rs.getMetaData();
       /**Création d'une boucle pour parcourir la list **/
            while(rs.next()) {
 	         for(int i = 1; i <=  res.getColumnCount(); i++)
 	              {
 	        	 /**ajouter tous les urls sur la liste **/
 		            Resultat.add(rs.getString(i));
 		            }
                        }
            rs.close();
            stm.close();
             } catch (Exception e){}
               
 	  return(Resultat);
         }
    /**
/**
* @param URL du fichier a télécharger 
* @param chemin du répértoire pour enregistre les fichier 
* @throws IOException
*/
         
     public static void downloadFile( String array, String saveDir){
             // throws IOException {
    	 
    	    try {
    	   /** cree une instance de URL qui pointe vers le lien * */
               URL url = new URL(array) ;
             
    	       /** ouvrir une connection http */
             HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
 
             int responseCode = httpConn.getResponseCode();
                /** verification du code de réponse http */ 
 
    if (responseCode == HttpURLConnection.HTTP_OK) {
             String fileName = "";
             String disposition = httpConn.getHeaderField("Content-Disposition");
             String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();
            
               if (disposition != null) {
     	
              /** Extraire le nom de fichier sur l'en-tête */
     	 
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                fileName = disposition.substring(index + 10,
                     disposition.length() - 1);
                       }
                   } 
              else {
     	 /** extrait le nom de fichier de l'URL */
                 fileName = array.substring(array.lastIndexOf("/") + 1,
                 array.length());
                   }
         System.out.println("Type du contenu = " + contentType);
         System.out.println("Contenu Disposition = " + disposition);
         System.out.println("Taille = " + contentLength);
         System.out.println("Nom-fichier = " + fileName);
         /**
          Ouvrir le chemin d'entrée à partir de la connexion HTTP*/	            
        InputStream inputStream = httpConn.getInputStream();
        String saveFilePath = saveDir + File.separator + fileName;
        
        /** Ouvre un flux de sortie pour enregistrer dans le fichier */
         FileOutputStream outputStream = new FileOutputStream(saveFilePath);

         int bytesRead = -1;
          byte[] buffer = new byte[BUFFER_SIZE];
     
          while ((bytesRead = inputStream.read(buffer)) != -1) 
          {
               outputStream.write(buffer, 0, bytesRead);
          }

            outputStream.close();
            inputStream.close();

           System.out.println("Fichier télécharger");
 } 
 else {
     System.out.println("Aucun fichier , Le serveur a répondu au code HTTP:  " + responseCode);
 }
 /** déconnection 
  */
 
 httpConn.disconnect();
   } catch (IOException e){}   	 
}
}
