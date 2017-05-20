package textmining;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


import com.itextpdf.text.pdf.fonts.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.*;
import com.itextpdf.text.Rectangle;

public class AnalyseReferences {

	/**
	* La classe AnalyseReferences permet de trouver la partie du texte contenant les r�f�rences bibliographiques
	* et va executer les fonctions pour r�cup�rer les r�f�rences unes � unes.
	*
	* @author  Carlier Nils
	*/
	
	int lDebut,lFin;
	private final String bibHeader[] = {"Bibliographie", "Bibliography", "Références", "References", "Reference"};
	private ArrayList<String> Lines;
	private ArrayList<Float> LineSizes;
	private float avgSize;
	public ArrayList<Section> sections;
	public int indexReferences = -1;
	
	public AnalyseReferences(String pdf)
	{		
		Lines = new ArrayList<String>();
		LineSizes = new ArrayList<Float>();
		sections = new ArrayList<Section>();
		toText(pdf);
		lDebut = lFin = 0;
	}
	
	private void toText(String pdf)
	{
		/**
		* Cette fonction va analyser le texte pour le transformer en texte et le diviser
		* en sections, dont la section de r�f�rences.
		* 
		* @param Prend en param�tre un lien vers le pdf
		* @return
		*/
		try {
			PdfReader pdfr = new PdfReader(pdf);
			PdfReaderContentParser parser = new PdfReaderContentParser(pdfr);
			customStrategy strategy;
			
			FileOutputStream out = new FileOutputStream("test.txt");
			
			avgSize = 0;
			
			String tmp = "";
			ArrayList<Float> tmp2 = new ArrayList<Float>();
			for(int i=1;i<= pdfr.getNumberOfPages();i++)
			{				
				strategy = parser.processContent(i, new customStrategy());
				tmp += strategy.getResultantText();
				
				tmp2.addAll(strategy.height);
			}
			PageToLines(tmp, tmp2);
			
			avgSize /= LineSizes.size();
			
			ArrayList<Pair<Integer,Integer>> test = findSections(); // Division du pdf en sections
			
			for(int i=0;i<test.size();i++)
			{
				//System.out.println(Lines.get(test.get(i).getLeft()) + " // " + LineSizes.get(test.get(i).getLeft()));
				sections.add(new Section(test.get(i).getLeft(),test.get(i).getRight(),Lines.get(test.get(i).getLeft())));
			}
			
			for(int i=0;i<sections.size();i++)
			{
				if(sections.get(i).comparaisonTitre(bibHeader)) indexReferences = i;
				//System.out.println(sections.get(i).titre + " : " + sections.get(i).debut + " / " + sections.get(i).fin);
			}
			
			out.close();
			
			pdfr.close();
		} catch(IOException e)
		{
			System.out.println("PB : " + e.getMessage());
		}
	}
	
	private void PageToLines(String Page,ArrayList<Float> size)
	{
		/**
		 * Cette fonction d�coupe le texte en lignes et pr�pare la moyenne de tailles des lignes du texte.
		 * @param Le texte complet et le tableau de tailles des lignes du texte
		 */
		String[] lines = Page.split("\\n");
		for(int i=0;i<lines.length-1;i++)
		{
			Lines.add(lines[i]);
			LineSizes.add(size.get(i));
			avgSize += size.get(i);
		}
	}
	
	private ArrayList<Pair<Integer,Integer>> findSections()
	{
		/**
		 * Cette fonction d�coupe le texte en lignes et pr�pare la moyenne de tailles des lignes du texte.
		 * @param Le texte complet et le tableau de tailles des lignes du texte
		 * @return Retourne une liste de 2 entiers correspondants � la position de la premi�re et derni�re ligne de la section dans le tableau de lignes
		 */
		ArrayList<Pair<Integer,Integer>> ret = new ArrayList<Pair<Integer,Integer>>();
		
		int beginLine = 0;
		
		for(int i=0;i<Lines.size();i++)
		{
			if(LineSizes.get(i) > avgSize)
			{
				if(beginLine == 0) beginLine = i; // i est le début de la première section
				else // Si on trouve une autre partie
				{
					ret.add(new Pair<Integer,Integer>(beginLine,i-1));
					beginLine = i;
				}
			}
		}
		ret.add(new Pair<Integer,Integer>(beginLine,Lines.size()));
		return ret;
	}
	
	public ArrayList<Reference> getReferences()
	{
		/**
		* Cette fonction va trouver les r�f�rences et les retourner vers TextMining.processPdf
		* 
		* @return Les r�f�rences bibliographiques trouv�es
		*/
		ArrayList<Reference> references = new ArrayList<Reference>();
		
		if(indexReferences != -1)
		{
			SplitReferences sR = new SplitReferences();
			references = sR.splitReferences(sections.get(indexReferences).toText(Lines));
		}
		
		return references;
	}
}

class customStrategy implements TextExtractionStrategy{
	
	/**
	* Cette classe agit comme une interface entre la biblioth�que itextPdf et la classe AnalyseReferences
	* Elle permet de r�cup�rer la taille de chaque ligne du pdf dans un tableau
	* 
	*/
	
	private Vector lastStart;
    private Vector lastEnd;
    
    /** used to store the resulting String. */
    private final StringBuffer result = new StringBuffer();;
    public ArrayList<Float> height = new ArrayList<Float>();
    private float heightBuffer;

    /**
     * Creates a new text extraction renderer.
     */
    public customStrategy() {
    }

    public void beginTextBlock() {
    }

    public void endTextBlock() {
    }
    
    public String getResultantText(){
        return result.toString();
    }

    protected final void appendTextChunk(CharSequence text){
    	result.append(text);
    }
    
    public void renderText(TextRenderInfo renderInfo) {
    	/**
		* Cette fonction va calculer la hauteur de la ligne actuellement en analyse et l'ajouter � la liste d'�l�ments pdf trait�s.
		* 
		* @param Un argument renderInfo qui contient les informations sur le texte pdf en cours d'analyse par la biblioth�que
		*/
    	
    	int tmp = result.lastIndexOf("\n");
        boolean firstRender = result.length() == 0;
        boolean hardReturn = false;
        
        LineSegment segment = renderInfo.getBaseline();
        Vector start = segment.getStartPoint();
        Vector end = segment.getEndPoint();
		
        //Modification Begin
		Vector curBaseline = renderInfo.getBaseline().getStartPoint();
		Vector topRight = renderInfo.getAscentLine().getEndPoint();
		
		Rectangle rect = new Rectangle(curBaseline.get(1), curBaseline.get(2), topRight.get(1), topRight.get(2));
		float curFontSize = topRight.get(1)-curBaseline.get(1);//rect.getHeight();

		//Modification End
        
        if (!firstRender){
            Vector x0 = start;
            Vector x1 = lastStart;
            Vector x2 = lastEnd;
            
            float dist = (x2.subtract(x1)).cross((x1.subtract(x0))).lengthSquared() / x2.subtract(x1).lengthSquared();

            float sameLineThreshold = 1f;
            if (dist > sameLineThreshold)
                hardReturn = true;
        }
        
        if (hardReturn){
        	appendTextChunk("\n");
        } else if (!firstRender){ 
            if (result.charAt(result.length()-1) != ' ' && renderInfo.getText().length() > 0 && renderInfo.getText().charAt(0) != ' '){ // we only insert a blank space if the trailing character of the previous string wasn't a space, and the leading character of the current string isn't a space
                float spacing = lastEnd.subtract(start).length();
                if (spacing > renderInfo.getSingleSpaceWidth()/2f){
                	appendTextChunk(" ");
                    //System.out.println("Inserting implied space before '" + renderInfo.getText() + "'");
                }
            }
        } else {
            //System.out.println("Displaying first string of content '" + text + "' :: x1 = " + x1);
        }
        
        appendTextChunk(renderInfo.getText());
        if(tmp != result.lastIndexOf("\n"))
        {
        	height.add(heightBuffer);
        }
        heightBuffer = curFontSize;
        
        lastStart = start;
        lastEnd = end;
    }
    
    public void renderImage(ImageRenderInfo renderInfo) {
        // do nothing - we aren't tracking images in this renderer
}
}

class Section {
	/**
	* La classe section permet de classer facilement les sections du pdf
	* 
	*/
	public int debut;
	public int fin;
	public String titre;
	
	public Section(int debut,int fin, String titre)
	{
		this.debut = debut;
		this.fin = fin;
		this.titre = titre;
	}
	
	public boolean comparaisonTitre(String[] clefs)
	{
		/**
		* Cette fonction va comparer une liste de cha�nes de caract�res au titre de la section
		* 
		* @param Prend en argument une liste de cha�nes de caract�res � comparer
		* @return Retourne vrai si une des clefs en arguments sont pr�sentes dans le titre de la section
		*/
		for(int i=0;i<clefs.length;i++)
		{
			if(titre.toLowerCase().contains(clefs[i].toLowerCase())) return true;
			
		}
		return false;
	}
	
	public List<String> toText(ArrayList<String> Lines)
	{
		List<String> ret = new ArrayList<String>();
		for(int i=debut+1;i<fin;i++)
		{
			ret.add(Lines.get(i));
		}
		return ret;
	}
}

class Pair<L,R> {
	  private final L left;
	  private final R right;

	  public Pair(L left, R right) {
	    this.left = left;
	    this.right = right;
	  }

	  public L getLeft() { return left; }
	  public R getRight() { return right; }

	  @Override
	  public int hashCode() { return left.hashCode() ^ right.hashCode(); }

	  @Override
	  public boolean equals(Object o) {
	    if (!(o instanceof Pair)) return false;
	    Pair pairo = (Pair) o;
	    return this.left.equals(pairo.getLeft()) &&
	           this.right.equals(pairo.getRight());
	  }

	}