package textmining;

import java.io.*;



public class TypePdf {
//TODO p-e prendre l'url du PDF pour faire plus de test

 /*
 * TypeTXT:
 * Retour:
 *     1		= fichier texte cohérent
 *     -2    	= txt incohérent: Erreur probable de convertion pdf->txt 
 */
    public static int typeTXT(String [] text)
    {
        int nbLigneTest=20;
        int tolerence=20; 
            /*
             * On lit les 5premiere ligne du fichier texte
             * On vérifie si la convertion pdf -> txt est cohérente.
             */
            int i=0;        //compteur du nombre de ligne lu
            int indice=0;    //nombre de caractère special
            int nbChar=0;    //nombre de caractère rencontré
            
            //liste des caractères 'normaux' (minuscule majuscule espace)
            String caractereCoherent = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        

            while(i<text.length  && i<nbLigneTest)
            {
                for(int y=0; y<text[i].length(); y++)
                {
                    nbChar++;
                    if(caractereCoherent.indexOf(text[i].charAt(y)) == -1 )
                    {
                        indice++;
                    }
                }
                i++;
            }
            
        //tolérence de characterespecial en %
        if(indice>(tolerence*nbChar)/100) return -4;
              
        return 1;
    }
}
