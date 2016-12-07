package net.codejava.networking;

	import java.io.File;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.net.HttpURLConnection;
	import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
	 
public class test extends Recherche  {
	
	private static final int BUFFER_SIZE = 4096;

			
     public test(int id, String type, String nom, String url) {
		super(id, type, nom, url);
	
     }
     
  public String lecture () {
	 try{
		 
	      String[] tab=null;
	      
		// on charge le pilote JDBC 
         Class.forName("com.mysql.jdbc.Driver");
         // on crée une connection du package java.sql
         Connection conn=DriverManager.getConnection
  ("jdbc:mysql://localhost:3306/projetjava",	"root" ,"");
         
       //création d'un objet preparedstatement pour une requette qui récupére les url 
         PreparedStatement ps=conn.prepareStatement("select url from Recherche");
         // execution de la requette 
      		   ResultSet rs=ps.executeQuery();
      		   ResultSetMetaData rsmd=rs.getMetaData();
      		   
      		int i=0;
      		tab= new String[rsmd.getColumnCount()];
      		
      		       for(int i=1;i<rsmd.getColumnCount();i++){
      			   
      		   }
          		   while(rs.next())
          				   {
          			   String fileURL=rs.getString("url");
	                     }
	  
	   
      return tab ;       
      }
   
	    /**
	     * @param URL du fichier a télécharger 
	     * @param chemin du répértoire pour enregistre les fichier 
	     * @throws IOException
	     */
	    public static void downloadFile(String fileURL, String saveDir)
	            throws IOException {
	    	
	    	/** cree une instance de URL qui pointe vers le lien * */
	        URL url = new URL(fileURL);
	        
	        /** ouvrir une connection http */
	        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	        
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
	                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
	                        fileURL.length());
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
	            
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
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
	    }
}

