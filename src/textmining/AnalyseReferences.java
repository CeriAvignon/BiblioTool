package textmining;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class AnalyseReferences {

	
	int lDebut,lFin;
	private final String bibHeader[] = {"Bibliographie", "Bibliography", "Références", "References"};
	private ArrayList<String> Lines;
	
	AnalyseReferences(String pdf)
	{
		toText(pdf);
		lDebut = lFin = 0;
	}
	
	private void toText(String pdf)
	{
		try {
			PdfReader pdfr = new PdfReader(pdf);
			PdfReaderContentParser parser = new PdfReaderContentParser(pdfr);
			TextExtractionStrategy strategy;
			
			for(int i=1;i<= pdfr.getNumberOfPages();i++)
			{
				strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
				//System.out.println(strategy.getResultantText());
				Lines.add(strategy.getResultantText()); // Ajout de la possibilité d'utiliser une liste de lignes à la place d'un fichier txt
			}
			
			pdfr.close();
		} catch(IOException e)
		{
			System.out.println("PB : " + e.getMessage());
		}
	}
	
	private boolean findBibSection()
	{
		//Trouver la partie Bib dans le fichier texte
		
		boolean findHeader = true;
		for(int i=0;i<Lines.size();i++)
		{
			String line = Lines.get(i);
			if(containsList(line, bibHeader) && findHeader) // Recherche du début
			{
				lDebut = i;
				findHeader = false;
			}
			//Trouver la fin
		}
		return !findHeader;		
	}
	
	private boolean containsList(String line, String[] keys)
	{
		for(int i=0;i<keys.length;i++)
		{
			if(line.toLowerCase().contains(keys[i].toLowerCase())) return true;
		}
		return false;
	}
	
	public String[] getReferences()
	{
		if(!findBibSection()) return null;
		String[] references = listReferences();
		
		
		
		return references;
	}
	
	private String[] listReferences()
	{
		String[] references = {};
		
		//Lister chaque référence à partir du fichier texte de la ligne lDebut à lFin
		
		return references;
	}
}