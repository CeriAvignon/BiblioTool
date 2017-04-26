package projet_2017;

public class test {
 
	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub


//tester avec un exemple 

Article a=new Article(1, "aasqdqsd", "", "http://exo7.emath.fr/ficpdf/ficall.pdf", "", 2, 1, "2016", true) ;





a.getUrlPage();


Download d =new Download();
//telecharger le pdf avec l'url existant dans la classe article
d.getFile(a.getUrlPdf());

//recuperer les meta donnée du pdf téléchargé

Meta_Donnes meta=new Meta_Donnes(d.fileName);

System.out.println(d.fileName);
String auteur;
 auteur= meta.getAuteur();
 //mettre ajour la classe article avec les meta données 
 
 //a.setListAuthor(auteur);
 a.setYearPub(meta.getDate());
 a.setTitle(meta.getTitre());
 
 
System.out.println(auteur);
System.out.println(meta.getTitre());
System.out.println(a.getTittle());
System.out.println(meta.getNbPage());
System.out.println(meta.getSujet());
System.out.println(meta.getCreateur());

	}

}
