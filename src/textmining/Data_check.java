package textmining;
import java.util.Calendar;
import java.lang.String;

public class Data_check {
    /*
     * Cette classe permet de verifiées donné extraite lors du text-mining (ou celle saisie par l'utilisateur)
     * 
     * tolerence: nombre de caracteres speciaux maximum dans: hauteur, titre.
     * tolerencePourcentage: pourcentage de caracteres speciaux maximum dans le resumé/description auteur.
     * 
     * Toute les fonction return des int indiquant si la donnée est cohérente:
     *     Si le return est inférieur a 0 alors la donné est incohérente:
     *         -1 donnée trop grande ex: 400 caractere pour un auteur
     *         -2 Trop de caractere speciaux
     * 
     *			-5 année incohérente
     *			-6 caractere interdit dans URL
     * 			-7 Double '/' dans url
     * Sinnon elle est validée.
     *         1 donné cohérente
     *         
     *         
     *La verif Article verifie tt l'article si il est cohérent la fct renvoie 0 sinon elle renvoie -1
     *
     *@Author cedricales
     */
	
	
    private static String caractereCoherent = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static int tolerence= 2;
    private static int tolerencePourcentage= 20;
    
    public static int checkarticle(Article a)
    {
    	int tmp=0;
    	//tmp = checkTitre(a.titleArt) + checkurl(a.urlPage) + checkurl(a.urlPdf) + checkAnnee(a.yearPub);
    	tmp = checkTitre(a.mTitre) + checkAnnee(a.mAnnee);
    	for(int i=0; i<a.mAuteur.size() ;i++)
    	{
    		tmp+=checkAuteur(a.mAuteur.get(i));
    	}
    	for(int i=0; i<a.mReferences.size() ;i++)
    	{
    		tmp+=checkReference(a.mReferences.get(i));
    	}
    	if(tmp < 0) return -1;
    	else return 0;
    }
    
    
    
    public void switchTolerence(int tolerence, int tolerencePourcentage)
    {
        this.tolerence = tolerence;
        this.tolerencePourcentage = tolerencePourcentage;
    }

    
    
    public  static int checkTitre(String titre)
    {    
        if( titre.length > 200) return -1;
        
        int indice;
        for(int i=0; i<titre.length(); i++)
        {
            if(caractereCoherent.indexOf(titre.charAt(i)) == -1 )
            {
                indice++;
                if( indice > tolerence) return -2;
            }
        }    
        return 0;
    }
    
    
    
    public static int checkurl(String url)
    { 	
    	if( titre.length > 400) return -1;
    	int y=0;
        for(int i=0; i<url.length(); i++)
        {
            if(" <>'#%{}|^,é~[]`;/?:@=&\"".indexOf(url.charAt(i)) == 1 )
            {
               return -6;
            }
            else if(url.charAt(i)=='/')
            {
            	y++;
            	if(y==2) return -7;
            }
            else y=0;
        }   
        return 0;
    }
    
    
    
    public static int checkAuteur(String auteur)
    {
        if( auteur.length > 200) return -1;
        
        int indice;
        for(int i=0; i<auteur.length(); i++)
        {
            if(caractereCoherent.indexOf(auteur.charAt(i)) == -1 )
            {
                indice++;
                if( indice > tolerence) return -2;
            }
        }
        return 0;
    }
    
    
    
    public static int checkResume(String resume)
    {
        int indice;
        int nbChar = 0;
        for(int i=0; i<resume.length(); i++)
        {
            nbChar++;
            if(caractereCoherent.indexOf(resume.charAt(i)) == -1 )
            {
                indice++;
                if(indice>(tolerencePourcentage*nbChar)/100) return -2;
            }
        }
        return 0;
    }
    
    
    
    public static int checkDescriptionAuteur(String descriptionAuteur)
    {
        int indice;
        int nbChar = 0;
        for(int i=0; i<descriptionAuteur.length(); i++)
        {
            nbChar++;
            if(caractereCoherent.indexOf(descriptionAuteur.charAt(i)) == -1 )
            {
                indice++;
                if(indice>(tolerencePourcentage*nbChar)/100) return -2;
            }
        }
        return 0;
    }
    
    
    public static int checkAnnee(date annee)
    {
        Calendar c = Calendar.getInstance();
        if(annee.getYear() > c.get(Calendar.YEAR)) return -5;
        return 0;
    }
    public static int checkAnnee(int annee)
    {
        Calendar c = Calendar.getInstance();
        if(annee > c.get(Calendar.YEAR)) return -5;
        return 0;
    }
    
    public static int checkReference(String references)
    {
        int indice;
        int nbChar = 0;
        for(int i=0; i<references.length(); i++)
        {
            nbChar++;
            if(caractereCoherent.indexOf(references.charAt(i)) == -1 )
            {
                indice++;
                if(indice>(tolerencePourcentage*nbChar)/100) return -2;
            }
        }
        return 0;
    }
}