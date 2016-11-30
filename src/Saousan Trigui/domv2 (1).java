package domParseur;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * <b>DomParseur est la classe qui parse un document XML en utilisant l'API DOM afin d'extraire les données de ce document </b>
 * Le parseur DOM est chargé de créer une arborescence à partir du document XML et la stocker dans la memoire.
 *
 * Les objets qui forment cette arborescence sont de type: 
 * Document: est le document XML.
 * Element: est un nœud dans un document XML.
 * Node: est un nœud de l'arborescence d'un document XML.
 * NodeList: est une liste des noeuds.
 *
 * Le fichier XML est défini par :
 * Une balise racine: breakfast_menu  (le menu du déjeuner).
 * La sous_balise de breakfast_menu: food  (le plat).
 * Les sous_balises de food: name, price, description, calories	 (les informations sur chaque plat).
 *
 *  
 * Le déroulement de la classe "DomParseur": 
 *  - Création d'une instance "factory" de la classe "DocumentBuilderFactory" afin de créer le parseur.
 *  - Création un document "doc" afin de parser le document XML nommé "simple.xml.
 *  - Récupération de la racine de l'arborescencede "breakfast" de type d'objet "Element".
 *  - Récupération de la liste des noeuds type d'objet "NodeList", elle contient tous les noeuds "food".
 *  - Probléme détecté: A ce stade la liste des noeuds contient aussi d'autres noeuds qui ne nous interessent pas.
 *  - Solution: On compte que les noeuds de la liste qui sont de type "Element".
 *  - Parcourir cette liste des noeuds.
 *  - Récupération, pour chaque élement, de la liste ses noeuds.
 *  - Récupération, pour chaque noeud (casté en "Element"), de sa valeur.
 *  - Récupération des valeurs des noeuds de chaque élement "food" dans une matrice.  	          			 		   
 * 
 *
 * La durée d'éxecution de ce processus est 209 milliseconde.
 *
 * @author Saoussan TRIGUI
 * @version 2.0
 */





public class DomParseur {



	public static void main(String[] args) {
	/**
	 * System.currentTimeMillis() retourne le temps en milliseconde.
	 */
	long debut = System.currentTimeMillis();
        System.out.println("debut: "+debut);	
	/**
	 * Création d'une instance "factory" de la classe "DocumentBuilderFactory" afin de créer le parseur.
	 */	
	final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    try {
		           /**
		            * Créer un parseur "parseurDom"  généré par le fabrique "factory".
		            */
			   final DocumentBuilder parseurDom = factory.newDocumentBuilder();
			   /**
			    * Creér un document "doc" de type d'objet "Document" généré par le parseur "parseurDom" à l'aide de la 		    	   	    * methode "parse()" afin de parser le document XML nommé "simple.xml".
			    */
			   final Document doc = parseurDom.parse(new File("simple.xml"));
			   /**
			    * Récupération du premier élément du document "breakfast" qui est la racine de l'arborescencede type d'objet 				    * "Element"		          	
			    */
			   final Element breakfast = doc.getDocumentElement();
			   /**
			    * Affichage du résultat de la récupération de la valeur de l'objet breakfast à l'aide de la méthode 			    * "getNodeName()".
			    */
			   System.out.println(breakfast.getNodeName());
			   /**
			    * Récupération de la liste des noeuds (liste des "food") nommé "breakfastNoeuds" de type d'objet 		    		    * "NodeList".
			    */
			   final NodeList BreakfastNoeuds = breakfast.getChildNodes();//racineNoeud=breakfastNoeud
			   /**
			    * La variable "NombreBreakfastNoeuds" contient la longueur de la liste "BreakfastNoeuds".			  
			    */
			   final int NombreBreakfastNoeuds = BreakfastNoeuds.getLength();
		   	   /**
		    	    * Boucle for pour compter le nombre de noeuds de type "Element" de de l'élement "breakfast".
	    		    */
			   int NbBreakfastNoeuds=0;
		 	   for (int compt1 = 0; compt1< NombreBreakfastNoeuds;compt1++) 
			   {
			       /**
				* Test sur le type des noeuds de l'element "breakfast": on compare le type de nos noeuds avec la 		 		 	* constante "Node.ELEMENT_NODE" afin de n'obtenir que les noeuds étant des éléments.
				*/
		        	if(BreakfastNoeuds.item(compt1).getNodeType() == Node.ELEMENT_NODE)
		        	{
	    				NbBreakfastNoeuds++;
	    			}
	    	            }
			    /**
			     * L'element "PremierElementFood" récupére un élément de la liste "BreakfastNoeuds" à l'aide de la méthode 
			     * "item()" en faisant un casting du noeud résultant de(BreakfastNoeuds.item(1)) en un Element.
			     */	  
			    final Element PremierElementFood = (Element) BreakfastNoeuds.item(1);
			    /**
			     * "FoodNoeuds" reçoit la liste des noeuds  de l'élément food.
			     */
			    final NodeList FoodNoeuds = PremierElementFood.getChildNodes();
			    /**
			     * "NombreFoodNoeuds" reçoit la longueur de la liste "FoodNoeuds".
			     */
				  int NombreFoodNoeuds=FoodNoeuds.getLength();
				  int NbFoodNoeuds=0;
			    /**
			     * Boucle for pour compter le nombre de noeuds de type "Element" de l'élement "PremierElementFood".
			     */
			    for (int compt = 0; compt< NombreFoodNoeuds;compt++)
  			    {
				/**
				 * Test sur le type des noeuds de l'element "PremierElementFood".
				 */
			    	if(FoodNoeuds.item(compt).getNodeType() == Node.ELEMENT_NODE) 
				{
		    			NbFoodNoeuds++;
			    	}
			    }
			 	System.out.println("NbFoodNoeuds: "+NbFoodNoeuds+" NbBreakfastNoeuds: "+NbBreakfastNoeuds);
			    /**
			     * Creation d'un tableau de deux dimensions "MatriceFoodElement",les lignes représentent les elements "food", 				     * les colonnes représentent les valeurs des noeuds (de type "String") de chaque élement "food",
			     */
			    String [ ][ ] MatriceFoodElement = new String [NbBreakfastNoeuds][NbFoodNoeuds];
			    /**
			     * Creation d'un tableau "TabFood" qui va recevoir les élements de la noeud "food".
			     */
			    final  Element [] TabFood = new Element [NbBreakfastNoeuds];	    
			    /**
			     * Le parcours de la matrice et la liste des noeuds de l'element breakfast.
			     * Pour la matrice: i est le compteur des lignes, kl est le compteur des colonnes.
			     */
		  	    int lm=0;
			    for (int i = 0; i<NombreBreakfastNoeuds; i++) 
			    {
		            	int kl= 0;
				if(BreakfastNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE)
				{
					/**
					 * A chaque itération, "TabFood" reçoit l'élement d'indice "i" de la liste BreakfastNoeuds 						 * (i.e "food").
					 */	
					TabFood[lm]=(Element) BreakfastNoeuds.item(i);
				  
					System.out.println("\n*************Food************");
			    	  	/**
					 * L'objet "nom_plat" reçoit l'élement "name" du noeud "food".
		   		         * La méthode "getElementsByTagName()" de l'objet Element retourne une liste d'elements, et 	 						 * puisque le noeud "name" ne contient qu'un seul élement, le retour de cette méthode est une  		 		   		 * liste qui contient un seul élement auquel on peut accéder directement avec la méthode item() 					 * avec l'entier "0".
				         */
					final Element nom_plat = (Element) TabFood[lm].getElementsByTagName("name").item(0);
			    	        /**
				 	 * L'objet "prix" reçoit l'élements "price" du noeud "food".
				         */
					final Element prix = (Element) TabFood[lm].getElementsByTagName("price").item(0);
			    	        /**
				 	 * L'objet "detail" reçoit l'élements "description" du noeud "food".
				         */
					final Element detail = (Element) TabFood[lm].getElementsByTagName("description").item(0);
			    	        /**
				 	 * L'objet "les_calories" reçoit l'élements "calories" du noeud "food".
				         */
					final Element les_calories = (Element) TabFood[lm].getElementsByTagName("calories").item(0);
				  	/**
				  	 * Chaque colonne de "MatriceFoodElement" reçoit la valeur de chaque élement  l'aide de la méthode 					   	 * "getTextContent()".
				   	 */			
					 MatriceFoodElement[lm][kl]=nom_plat.getTextContent();
					 kl=kl+1;
					
					MatriceFoodElement[lm][kl] =prix.getTextContent();
					kl=kl+1;
			
					MatriceFoodElement[lm][kl] =detail.getTextContent();				
					kl=kl+1;
					   
					MatriceFoodElement[lm][kl] =les_calories.getTextContent();
					kl=kl+1;

				   /**
				    * Parcourir la matrice "MatriceFoodElement" et afficher son contenu, la variable "m" est le 				    * compteur des colonnes.
				    */			 	
		    
				    for (int m = 0; m< kl; m++)
				    {
				    System.out.println(MatriceFoodElement[lm][m]);
				    }

				lm++;		        	
				}
			    }			
			}


		       /**
		        * @exception: ParserConfigurationException
		        * Une exeption "e" de type "ParserConfigurationException" se déclenche s'il y a des erreurs au niveau de la 	   				* configuration du parseur et on demande d'afficher tous ces erreurs à l'aide de la méthode 				* "printStackTrace()".			 	
		        */ 
			catch (final ParserConfigurationException e)
			{
				e.printStackTrace();
			}
		        /**
		         * @exception: SAXException
		         * Une exeption "e" de type "SAXException" se déclenche s'il y a des erreurs dans l'analyse du 
		         * document XML et on demande d'afficher tous ces erreurs à l'aide de la méthode 
			 * "printStackTracE()".			 	
		   	 */

			catch (final SAXException e)
			{
		    		e.printStackTrace();
			}
		  	/**
		   	 * @exception: IOException
		   	 * Une exeption "e" de type "IOException" se déclenche s'il y a des erreurs aux flux d'entrées lors de 	
		   	 * l'importation du document XML et on demande d'afficher tous ces erreurs à l'aide de la méthode 
			 * "printStackTrace()".			 	
		         */
		catch (final IOException e) {
		    e.printStackTrace();
		}
	        long fin = System.currentTimeMillis();
                System.out.println("fin: "+fin);
		long dureeProcessus=fin-debut;	
                System.out.println("dureeProcessus: "+dureeProcessus);	
	    }
	}
