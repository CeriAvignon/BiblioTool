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


import com.itextpdf.text.pdf.fonts.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.*;
import com.itextpdf.text.Rectangle;

public class AnalyseReferences {

	
	int lDebut,lFin;
	private final String bibHeader[] = {"Bibliographie", "Bibliography", "Références", "References"};
	private ArrayList<String> Lines;
	private ArrayList<Float> LineSizes;
	private float avgSize;
	
	public AnalyseReferences(String pdf)
	{		
		Lines = new ArrayList<String>();
		LineSizes = new ArrayList<Float>();
		toText(pdf);
		lDebut = lFin = 0;
	}
	
	private void toText(String pdf)
	{
		try {
			PdfReader pdfr = new PdfReader(pdf);
			PdfReaderContentParser parser = new PdfReaderContentParser(pdfr);
			customStrategy strategy;
			
			FileOutputStream out = new FileOutputStream("test.txt");
			
			avgSize = 0;
			
			//ArrayList<String> tmp = new ArrayList<String>();// textes (pas des phrases)
			//ArrayList<Float> tmp2 = new ArrayList<Float>(); // Tailles
			for(int i=1;i<= pdfr.getNumberOfPages();i++)
			{				
				strategy = parser.processContent(i, new customStrategy());
				//tmp.add(strategy.getResultantText();
				//tmp2.append(strategy.height()); // PAS NECESSAIREMENT, VERIFIER que height se reset à la fin !!
				//PageToLines(strategy.getResultantText(),strategy.height); // NE S'APPELLE PAS ICI : strategy non complète
				System.out.println(strategy.height.size());
			}
			avgSize /= LineSizes.size(); // PB ICI : Prend par page != par ligne
			avgSize *=1.2;
			
			
			ArrayList<Pair<Integer,Integer>> test = findSections();
			
//			for(int i=0;i<test.size();i++)
//			{
//				System.out.println(Lines.get(test.get(i).getLeft()) + " // " + LineSizes.get(test.get(i).getLeft()));
//				/*for(int j=test.get(i).getLeft();j<test.get(i).getRight();j++)
//				{
//					System.out.println(Lines.get(j));
//				}*/
//			}
			
			out.close();
			
			pdfr.close();
		} catch(IOException e)
		{
			System.out.println("PB : " + e.getMessage());
		}
	}
	
	private void PageToLines(String Page,ArrayList<Float> size)
	{
		String[] lines = Page.split("\\n");
		for(int i=0;i<lines.length;i++)
		{
			Lines.add(lines[i]);
			LineSizes.add(size.get(i));
			avgSize += size.get(i);
		}
	}
	
	private ArrayList<Pair<Integer,Integer>> findSections()
	{
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
		return ret;
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
	
	private int getTextFontSize(int debut, int fin)
	{
		
		
		
		return -1;
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

class customStrategy implements TextExtractionStrategy{
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
		
		
		
		if(renderInfo.getText().contains("\\n"))
		{
			System.out.println("ICI");
		}
		/*if(renderInfo.getText().contains("\\n")) { height.add(curFontSize);
			System.out.println(height.get(height.size()-1));
		}*/
		//height.add((topRight.subtract(curBaseline)).get(1));

		//Modification End
        
        if (!firstRender){
            Vector x0 = start;
            Vector x1 = lastStart;
            Vector x2 = lastEnd;
            
            // see http://mathworld.wolfram.com/Point-LineDistance2-Dimensional.html
            float dist = (x2.subtract(x1)).cross((x1.subtract(x0))).lengthSquared() / x2.subtract(x1).lengthSquared();

            float sameLineThreshold = 1f; // we should probably base this on the current font metrics, but 1 pt seems to be sufficient for the time being
            if (dist > sameLineThreshold)
                hardReturn = true;
            
            // Note:  Technically, we should check both the start and end positions, in case the angle of the text changed without any displacement
            // but this sort of thing probably doesn't happen much in reality, so we'll leave it alone for now
        }
        
        if (hardReturn){
            //System.out.println("<< Hard Return >>");
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
        	//System.out.println(renderInfo.getText()); // Probablement un décalage de 1 'ligne'
        	height.add(heightBuffer);
        }
        heightBuffer = curFontSize;
        
        // /!\ PB : Taille est par rapport à une partie de phrase et non une phrase complète
        // Solution : Override la fonction qui recrée la phrase à partir des parties et garder sa hauteur
        
        //System.out.println(renderInfo.getText() + " : " + height.get(height.size()-1) + " | " + height.size());
        
        lastStart = start;
        lastEnd = end;
    }
    
    public void renderImage(ImageRenderInfo renderInfo) {
        // do nothing - we aren't tracking images in this renderer
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