package test_dom4j;
import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.jaxen.dom4j.*;


interface  : modélise l'intégralité des types de nœud que l'on peut trouver dans un document XML. Une istance de Node peut être enfant d'un élément XML.

interface Branch : modélise les types de nœud XML qui peuvent posséder des enfants, à savoir Document et Element.

interfaces Text, Attribute, Comment, etc... : modélisent les notions XML correspondantes. Ces interfaces étendent Node.

interfaces Document et Element : modélisent le document tout entier et les éléments XML respectivement.

Deux classes échappent à cette hiérarchie : Namespace, qui modélise les espaces de nom, et QName, qui modélise les noms complets ( fully qualified name ).




/**
 * <b>Test_dom4j est la classe qui parse un document XML en utilisant l'API DOM4j afin d'extraire les données de ce document et créer l'arborescence </b>
 * Pour obtenir une telle représentation, dom4j utilise soit SAX, soit DOM.
 * 
 * Les objets qui forment cette arborescence sont de type: 
 * Document: est le document XML.
 * Node: est un noeud dans un document XML.
 * Element: est un élement dans un document XML.
 * List<Node>: est une liste des noeuds.
 *
 *
 * le fichier XML est défini par :
 * Une balise racine: breakfast_menu  
 * La sous_balise de breakfast_menu: food  
 * Les sous_balises de food: name, price, description, calories	
 * 
 * 
 * Le déroulement de la classe "JdomParseur": 
 *  - Création d'un parseur "reader" qui permet la lecture du documet XML.
 *  - Création d'un document "doc" afin d'analyser le document XML "simple.xml".
 *  - Récupération de la racine de l'arborescencede "breakfast" de type d'objet "Element".
 *  - Récupération de la liste des "Noeuds" de type d'objet "List<Node>", elle contient tous les noeuds de la racine se trouve dans 
 *  le chemin "/breakfast_menu/food".
 *  - Parcour de cette liste des noeuds.
 *  - Récupération, pour chaque noeuds, de la liste ses sous éléments.
 *  - Récupération, pour chaque sous noeud, de sa valeur.
 *  - Récupération des valeurs des sous noeud de chaque noeud "food" dans une matrice.
 * 
 *
 * La durée d'éxecution de ce processus est 1284 milliseconde.
 *
 * @author Saoussan TRIGUI
 * @version 2.0
 */



public class Test_dom4j {


	public static void main(String[] args) {
		long debut = System.currentTimeMillis();
		try{
			 /**
			  * Créer un parseur "reader" qui permet la lecture du documet XML
			  */
		 	 SAXReader parseurDom4j = new SAXReader();
			/**
			 * Creér un document "doc" généré par le parseur "parseurDom4j" à l'aide de la methode "read()" afin de parser le 	  		 * document "simple.xml".
			 */
			 Document doc = parseurDom4j.read(new File("simple.xml"));
			 System.out.println("Root element :" + doc.getRootElement().getName());
			 /**
			  * "ElementRacine_breakfeast" reçoit l'élement racine de l'arborescencede du document XML(i.e breakfast).
			  */
			 Element ElementRacine_breakfeast = doc.getRootElement();
			 /**
			  * "listeDesNoeud_Food" reçoit tous les noeuds de la racine se trouve dans le chemin "/breakfast_menu/food".
			  */
			 List<Node> listeDesNoeud_Food = doc.selectNodes("/breakfast_menu/food" );
			 System.out.println("Les noeuds:"+listeDesNoeud_Food);

			 int nbElementFood=4;
		     	 String [ ][ ] MatriceFoodElement = new String [listeDesNoeud_Food.size()][nbElementFood];
			 /**
			  * Le parcours de la matrice et la liste des "Nodes" de l'element "ElementRacine_breakfeast".
			  * Pour la matrice: i est le compteur des lignes(i.e food), kl est le compteur des colonnes(i.e les valeurs des 				  * noeuds du food).
			  */
			  for (int i = 0; i < listeDesNoeud_Food.size(); i++)
		          {  
				/**
				 * A chaque itération l'objet "plat" de type "Node" reçoit le noeud d'indice "i" de la liste 					 * "listeDesNoeud_Food"(i.e le noeud food).
				 */ 
				  Node plat = listeDesNoeud_Food.get(i);
				/**
				 * "plat.getName()" retourne la nom de cet noeud de type "String"(i.e "food").
				 */			
				  System.out.println(i+")" + plat.getName()); 
				 

				  int kl= 0;
				  /**
				   * La méthode "selectSingleNode("name")" retourne le noeud nommée "name" du noeud food.
				   * La méthode "getText()" donne la valeur du noeud "name".
				   */
	     			  MatriceFoodElement[i][kl]=plat.selectSingleNode("name").getText();
	     			  kl=kl+1;
				 /**
				  * Récupération de la valeur de l'élement "price". 
				  */
	     			  MatriceFoodElement[i][kl] =plat.selectSingleNode("price").getText();
	     			  kl=kl+1;
	     			 /**
				  * Récupération de la valeur de l'élement "description". 
				  */
	     			  MatriceFoodElement[i][kl] =plat.selectSingleNode("description").getText();
	     			 /**
				  * Récupération de la valeur de l'élement "calories". 
				  */
	     			  kl=kl+1;
	     			  MatriceFoodElement[i][kl] =plat.selectSingleNode("calories").getText();


	     	    	
		     	    
		     	          for (int m = 0; m<=kl; m++)
				  {
		     	          System.out.println( MatriceFoodElement[i][m]);
		     	          }	

			}



		/**
		* @exception: JDOMException
		* Une exeption "e" de type "DocumentException" se déclenche s'il y a des erreurs lors de l'utilisation du 
		* document XML et on demande d'afficher tous ces erreurs à l'aide de la méthode		 				 			* "printStackTrace()".
		*/
	
		}
		catch(DocumentException e) 
		{
		         e.printStackTrace();
		}
		long fin = System.currentTimeMillis();
                System.out.println("fin: "+fin);
		long dureeProcessus=fin-debut;	
                System.out.println("dureeProcessus: "+dureeProcessus);	
	     
		
	}

}



