package test_jdom;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

/**
 * <b>JdomParseur est la classe qui parse un document XML en utilisant l'API JdOM afin d'extraire les données de ce document et créer l'arborescence </b>
 *
 * JDOM produit l'arborescence d'objets à partir d'un flux, d'un arbre DOM ou d'événements SAX.
 * En realité JDOM n'est pas un parseur, il a besoin d'un parseur externe de type SAX ou DOM.
 * Par défaut, JDOM utilise le parseur défini par JAXP.
 * Les noeuds de l'arbre XML sont appelés "Element", chaque Element peut posséder des fils de type "Element".
 *
 *
 * Les objets qui forment cette arborescence sont de type: 
 * Document: est le document XML.
 * Element: est un nœud dans un document XML.
 * List<Element>: est une liste des élements.
 *
 * le fichier XML est défini par :
 * Une balise racine: breakfast_menu  
 * La sous_balise de breakfast_menu: food  
 * Les sous_balises de food: name, price, description, calories	
 *
 *
 * Le déroulement de la classe "JdomParseur": 
 *  - Création d'une instance "saxBuilder" de la classe "SAXBuilder" qui permet d'analyser le document XML avec un parseur  
 *    de type SAX et de créer un arbre JDOM. 
 *  - Création d'un document "doc" afin d'analyser le document XML "simple.xml".
 *  - Récupération de la racine de l'arborescencede "breakfast" de type d'objet "Element".
 *  - Récupération de la liste des "Element" de type d'objet "List<Element>", elle contient tous les noeuds "food".
 *  - Parcour de cette liste d'élements.
 *  - Récupération, pour chaque élement, de la liste ses sous éléments.
 *  - Récupération, pour chaque sous élement, de sa valeur.
 *  - Récupération des valeurs des sous élements de chaque élement "food" dans une matrice.
 * 
 *
 * La durée d'éxecution de ce processus est 672 milliseconde.
 *
 * @author Saoussan TRIGUI
 * @version 2.0
 */
public class JdomParseur {


	public static void main(String[] args) throws JDOMException, IOException
	{
		long debut = System.currentTimeMillis();
		System.out.println("debut: "+debut);
		try{
			/**
			 * Créer une instance "saxBuilder" de la classe "SAXBuilder" qui permet d'analyser le document XML avec un parseur 				 * de type SAX et de créer un arbre JDOM.
			 */
			SAXBuilder saxBuilder = new SAXBuilder();
			/**
			* Créer une instance "doc" de "Document" générer par "saxBuilder" à l'aide de la 		  			 	* methode "build()" afin d'analyser le document XML "simple.xml".
			*/
			Document doc = saxBuilder.build(new File("simple.xml"));
			System.out.println("Menu: " + doc.getRootElement().getName());
			/**
			 * Récupération du premier élement "breakfast" du document xml de type d'objet "Element"  généré par le document 		         	 * "doc" à l'aide de la méthode "getRootElement()".
			 */
			Element breakfast = doc.getRootElement();


			/**
			 * Récupération de la liste des élements "breakfastNoeuds" (qui représente la liste des food) de type d'objet 
			 * "List<Element>"  généré par l'élément "breakfast", à l'aide de la méthode" getChildren".
			 */

			List<Element> breakfastNoeuds = breakfast.getChildren();
			int NombreBreakfastNoeuds=breakfastNoeuds.size();
			/**
			 * Récupération d'un élément de la liste "BreakfastNoeuds" nommé "PremierElementFood" à l'aide de
			 * la méthode "get()". 
			 */	  
			Element PremierElementFood = breakfastNoeuds.get(1);
			/**
			 * "FoodNoeuds" la liste des élements de l'élément food.
			 */
			List<Element> FoodNoeuds = PremierElementFood.getChildren();
			/**
			 * La variable "NombreFoodNoeuds" reçoit la longueur de la liste "FoodNoeuds".
			 */
			int NombreFoodNoeuds=FoodNoeuds.size();
			String [ ][ ] MatriceFoodElement = new String [NombreBreakfastNoeuds][NombreFoodNoeuds];
			/**
			 * Le parcours de la matrice et la liste des élements de l'element breakfast.
			 * Pour la matrice: i est le compteur des lignes, kl est le compteur des colonnes.
			 */
			for (int i = 0; i < breakfastNoeuds.size(); i++) 
			{  
				/**
				 * A chaque itération l'élement "food" reçoit l'élement d'indice "i" de la liste BreakfastNoeuds.
				 */
				Element food = breakfastNoeuds.get(i);
				/*
				 * food.getName() retourne la valeur de l'element food de type "String"
				 */
				System.out.println(i+")" + food.getName()); 
				int kl= 0;
				/** La méthode "getChild("name")" donne l'élement nommée "name" du noeud food.
				 * La méthode "getText()" donne la valeur de l'élement "name".
				 */ 
				MatriceFoodElement[i][kl]=food.getChild("name").getText();
				kl=kl+1;
				/**
				 * Récupération de la valeur de l'élement "price". 
				 */ 
				MatriceFoodElement[i][kl] =food.getChild("price").getText();
				kl=kl+1;
				/**
				 * Récupération de la valeur de l'élement "description". 
				 */ 
				MatriceFoodElement[i][kl] =food.getChild("description").getText();
				/**
				 * Récupération de la valeur de l'élement "calories". 
				 */ 
				kl=kl+1;
				MatriceFoodElement[i][kl] =food.getChild("calories").getText();
				for (int m = 0; m<=kl; m++) 
				{
					System.out.println( MatriceFoodElement[i][m]);
				}	
			}
		/**
		* @exception: JDOMException
		* Une exeption "e" de type "JDOMException" se déclenche s'il y a des erreurs dans l'analyse du 
		* document XML et on demande d'afficher tous ces erreurs à l'aide de la méthode		 				 			* "printStackTrace()".
		*/
		}
		catch(JDOMException e)
		{
		 	e.printStackTrace();
		}
		/**
		* @exception: IOException
		* Une exeption "e" de type "IOException" se déclenche s'il y a des erreurs aux flux d'entrées lors de l'importation du 
		* document XML et on demande d'afficher tous ces erreurs à l'aide de la méthode printStackTrace().			 
		*/
		catch(IOException e)
		{
		 	e.printStackTrace();
		}
		long fin = System.currentTimeMillis();
                System.out.println("fin: "+fin);
		long dureeProcessus=fin-debut;	
                System.out.println("dureeProcessus: "+dureeProcessus);	
	}
}

