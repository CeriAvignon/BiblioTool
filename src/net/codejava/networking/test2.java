package net.codejava.networking;

import java.io.IOException;

public class test2 {
	
	  public static void main(String[] args) {
		  /**
		     * @param URL du fichier a télécharger 
		     * @param chemin du répértoire pour enregistre les fichier */
		  
	        String fileURL = "http://www.lineosoft.fr/documentations/le-manager/3-la-fiche-article";
	        String saveDir = "C:/Users/admin/Desktop";
	        try {
	            test.downloadFile(fileURL, saveDir);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
