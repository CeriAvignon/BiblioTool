package textmining;

public class test {
	
	
	
	
		 public static void main(String[] args) {
		
			 
			Download d =new Download();
			//telecharger le pdf reference avec les acolade 
			d.getFile("http://hpac.gforge.inria.fr/Offres/PostdocEngineer-GrenobleLyonParis-HPAC-fr.pdf");	
			
			System.out.println("le nom de pdf est :"+d.fileName);
			
			//extraire les reference et metadonnées de pdf telecharger 
			Article a = TextMining.processPdf(d.fileName);
			 
			 
			 a.ViewArticle();
			 
			
		 }

}
