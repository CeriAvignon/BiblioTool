package textmining;



import java.io.File;
import java.io.IOException;
import java.lang.System;
import java.util.HashMap;

import com.itextpdf.text.pdf.PdfReader;

/**
 * 
 * @author Eresea
 * <p>
 * La classe TextMining est la classe visible et utilisable en dehors du groupe.
 * Elle permet de lancer l'analyse et récupérer un objet de classe Article contenant les informations reçues.
 * 
 */
public class TextMining {
	/**
	 * 
	 * @param pdf Lien vers le pdf à analyser
	 * @return Un objet de classe Article contenant les informations de l'analyse.
	 */
	public static Article processPdf(String pdf)
	{
		Article a = new Article();
		MetaDonnees meta=new MetaDonnees(pdf);
		
		//Récupérer les meta d'une classe à part (Décider de l'API (itextpdf,clownPDF ou ..?))
		
		String reference[] = {};
		a.SetTitre(meta.getTitre());
		a.SetAuteur(meta.getAuteur());
		a.SetResume("");
		a.SetDescriptionAuteur("");
		if(meta.getDate().length() == 0) a.SetAnnee(-1);
		else a.SetAnnee(Integer.parseInt(meta.getDate().substring(meta.getDate().length() - 4)));
		a.SetSource("");
		a.SetReference(reference);
		
		return a;
	}

}
