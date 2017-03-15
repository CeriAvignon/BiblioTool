package textmining;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.itextpdf.text.pdf.PdfReader;

// Pourquoi pas utiliser la classe TextMining et Article directement ?

public class MetaDonnees {
	public String titre = null;
	public  String auteur = null;
	public  String date = null;
	public  String createur = null;
	public  String sujet = null;
	public  int nbrpage , nbrdonnees;

	public MetaDonnees(String f){
		
		Fichier(f);
		
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 



		}

//	}
	
	
	
	void Fichier(String nomfichier){
		
		File pdf = null;// Fichier pdf
		File sortie = null;// fichier text
		PdfReader pdfr = null; // outil itext pour lire les pdf
		int mod = 1;
		pdf = new File(nomfichier);
	 	//sortie = new File("aa");
		
		
	
		
		
			if (pdf.isFile()) {
				try {
					pdfr = new PdfReader(pdf.getAbsolutePath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					ecrire(sortie,
							"impossible de lire le fichier pdf; erreur : "
									+ e.toString(), mod);
				}
				HashMap infos = pdfr.getInfo();
				//ecrire(sortie, "nb meta=" + infos.size(), mod);
				//ecrire(sortie, "nb page=" + pdfr.getNumberOfPages(), mod);
				//ecrire(sortie, "nb page=" + pdfr.getNamedDestinationFromNames(), mod);
				// System.out.println(infos.size()+" taille");
				// ecrire(sortie,infos.toString());
				String result = infos.toString();
				String[] result2 = result.split(",");
				int i;
				
				
				
				//parcourir le hashmap et recuperer les valeur qu'on a besoin 
				
				for (Entry<String, String> a : pdfr.getInfo().entrySet())
				{
				  if (a.getKey().equals("Author")  ) {
					auteur=a.getValue();
				} else if (a.getKey().equals("Title") ) {
					titre= a.getValue();
					
				}else if (a.getKey().equals("CreationDate")) {
					
					date=a.getValue().substring(8, 10)+"/"+a.getValue().substring(6,8)+"/"+a.getValue().substring(2, 6);
				}else if (a.getKey().equals("Creator")) {
					createur= a.getValue();
				}else if (a.getKey().equals("Subject")) {
					sujet= a.getValue();
				}
				  
				//System.out.println(a.getKey());
				//System.out.println(a.getValue());
				 // a.getKey();
				   //a.getValue();
				  
				}
				  nbrdonnees= infos.size();
				  nbrpage= pdfr.getNumberOfPages();
				 
				
				
			} else {
				ecrire(sortie, "le fichier pdf n'existe pas", mod);
			}
		
		
	}
	
	public  String getAuteur(){
		
		
		
		return auteur;
			
	}

public  String getDate(){
		
		
		
		return date;
			
	}

public  int getNbPage(){
	
	
	
	return nbrpage;
		
	}

public  String getTitre(){
	
	
	
	return titre;
		
	}
public  String getSujet(){
	
	
	
	return sujet;
		
	}
	
	
	
	
	
public  String getCreateur(){
		
		
		
		return createur;
			
	}

	public static void ecrire(File f, String m, int mode) {//fonction qui permet d'ecrire les meta-donnees dans le fichier texte de sortie
		PrintWriter fic = null;
		System.out.println(m);
		FileWriter n = null;
		if (mode == 1) {
			try {

				n = new FileWriter(f, true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			fic = new PrintWriter(n, true);

			fic.println(m);
			fic.flush();
			fic.close();
		}
	}
}