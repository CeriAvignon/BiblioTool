package dom_parseur;


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
	 
	 *le fichier XML est défini par :
	 *<ul>
	 *<li>Une balise racine: breakfast_menu </li>
	 *<li>La sous_balise de breakfast_menu: food </li>
	 *<li>Les sous_balises de food: name, price, description, calories	</li>
	 *</ul>
	 * Le parseur DOM est chargé de créer une arborescence à partir du document XML et la stocker dans la memoire.
	 * Les objets qui forme cette arborescence sont de type: 
	 *<ul>
	 * <li>Document: est le document XML.
	 * <li>Element: est un nœud d'élément dans un document XML.
	 * <li>Node: est un nœud de l'arborescence d'un document XML.
	 * <li>NodeList: est une liste des noeuds.
	 * </ul>
	 * 
	 * 
	 *
	 * @author Saoussan TRIGUI
	 * @version 1.0
	 */

public class DomParseur {

		public static void main(String[] args) {

	        /**
	         *  Afin de créer un parseur il faut d'abord créer une instance "factory" de la classe "DocumentBuilderFactory"
	         */
	        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	           /**


	!!!!!!!!!!fonction newDocumentBuilder(), peut lever une exception, il convient de le placer dans un bloc de type try/catch :
		    */ 	
	        try {
	            /**
	             * Créer un parseur nommé "parseurDom" de type d'objet "DocumentBuilder" générer par le fabrique "factory" à l'aide de la 		     * methode "newDocumentBuilder()".
	             */
	            final DocumentBuilder parseurDom = factory.newDocumentBuilder();
				
		    /**
		     * creér un document nommé "doc" de type d'objet "Document" générer par le parseur "parseurDom" à l'aide de la 		     * methode "parse()" afin d'importer le document XML nommé "simple.xml".
		     * @param simple.xml le document xml à parer
		     */
		    final Document doc = parseurDom.parse(new File("simple.xml"));
				
		   
		    /**
		     * Récupération du premier element du document de type d'objet "Element" qui est la racine de l'arborescence nommé 		  		     * "breakfast" générer par le document "doc" à l'aide de la méthode "getDocumentElement".
		     */
		    final Element breakfast = doc.getDocumentElement();//racine=breakfast_menu!!!!!!!!!!!!!!!!!!!!!!!!!
			
		    /**
		     *Affichage de la resultat dela récupération de la valeur de l'objet breakfast à l'aide de la méthode "getNodeName()".
		     */
			
		    System.out.println(breakfast.getNodeName());
			
		   /**
		     * Récupération la liste des noeuds (liste de food) nommé "breakfastNoeuds" de type d'objet 		     * 			     * "NodeList"  générer par l'element "breakfast", à l'aide de la méthode "getChildNodes".
		     */
		    final NodeList BreakfastNoeuds = breakfast.getChildNodes();//racineNoeud=breakfastNoeud
		  /**
		    * Récupération, dans une variable nommé "nbbreakfastNoeuds" de type int,elle contient la longueur de la liste 			    * "BreakfastNoeuds".
		    */
		    final int NombreBreakfastNoeuds = BreakfastNoeuds.getLength();//nbracineNoeuds=nbbreakfastNoeuds!!!!!!

		/**
		  * Boucle for pour compter le nombre de noeuds de type d'elements de la liste BreakfastNoeuds.

		 */

			int NbBreakfastNoeuds=0;
		 	for (int compt1 = 0; compt1< NombreBreakfastNoeuds;compt1++) {
		        	if(BreakfastNoeuds.item(compt1).getNodeType() == Node.ELEMENT_NODE) {
	    			NbBreakfastNoeuds++;
	    			}
	    		}
		/**
		 * Récupération du premier element de la liste "BreakfastNoeuds" nommé "PremierElementFood" à l'aide de la méthode "item()"
!!!!!!!!!	 * en caster(casting ??) la noeud resultant de(BreakfastNoeuds.item(1)) en un Element
		*/	  
		    final Element PremierElementFood = (Element) BreakfastNoeuds.item(1);
		/**
		 * Récupération la liste des noeuds de l'element food, elle nommé "FoodNoeuds".
		*/
			  final NodeList FoodNoeuds = PremierElementFood.getChildNodes();
		/**
		 * Récupération, dans une variable nommé "NbFoodNoeuds" de type int, la longueur de la liste "FoodNoeuds".
		*/
			  int NombreFoodNoeuds=FoodNoeuds.getLength();
			  int NbFoodNoeuds=0;
		/**
		  * Boucle for pour compter le nombre de noeuds de type d'elements de la liste FoodNoeuds

		 */
		 	for (int compt = 0; compt< NombreFoodNoeuds;compt++) {
		        	if(BreakfastNoeuds.item(compt).getNodeType() == Node.ELEMENT_NODE) 
				{
	    			NbFoodNoeuds++;
		    		}
		    	}
		 	System.out.println("NbFoodNoeuds: "+NbFoodNoeuds+"NbBreakfastNoeuds: "+NbBreakfastNoeuds);
		/**
		 * Creation d'un tableau de deux dimensions nommé "MatriceFoodElement",les lignes représentent les elements "food", les 		 * colonnes représentent les valeurs des noeuds (de type "String") de chaque element "food",
		*/
		    String [ ][ ] MatriceFoodElement = new String [NbBreakfastNoeuds][NbFoodNoeuds];//nbElementFood=NbFoodNoeuds!!!!!!!!
		    final  Element [] TabFood = new Element [NbBreakfastNoeuds];

  			    
  		   /**
			 * Boucle for pour parcourir la matrice, i represente la ligne, kl représente la colonne et aussi pour parcourir 				 *la liste des noeuds de l'element breakfast.
			*/
  	  		int lm=0;
			for (int i = 0; i<NombreBreakfastNoeuds; i++) {
				   int kl= 0;
			/**
			 * Test sur le type des noeuds de l'element breakfast, on comparant le type de nos noeuds avec la constante 				 * "Node.ELEMENT_NODE" afin d'obtenir que les noeuds étant des éléments.
			 * La méthode "getNodeType()" retourne le type des noeuds. 

			 */
		        	if(BreakfastNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
		        		
		        		 /**
			   			  * A chaque itération on récupération l'élement d'indice "i" de la liste BreakfastNoeuds dans une objet de type 				  * "Element" nommée food.

			   			  */	
			        		TabFood[lm]=(Element) BreakfastNoeuds.item(i);
						
				   ///////// final Element food = (Element) BreakfastNoeuds.item(i);

			  /**
			   * Message d'affichage
			  */
				    System.out.println("\n*************Food************");
		    	  /**
			   * L'objet "nom_plat" de type Element récupération l'élements "name" du noeud "food".
	   		   * La méthode "getElementsByTagName()" de l'objet Element retourne une liste d'element, et puisque l'element 				   * "name" ne contient qu'un seul élement donc le retour de cette méthode est une liste qui contient un seul 		 		   * élement qu'on peut lui accéder directement avec la méthode item() avec l'entier "0".
		           */
				    final Element nom_plat = (Element) TabFood[lm].getElementsByTagName("name").item(0);

		    	  /**
			   *L'objet "prix" de type Element récupération l'élements "price" du noeud "food".
		           */

				    final Element prix = (Element) TabFood[lm].getElementsByTagName("price").item(0);
		    	  /**
			   *L'objet "detail" de type Element récupération l'élements "description" du noeud "food".
		           */
				    final Element detail = (Element) TabFood[lm].getElementsByTagName("description").item(0);
			  /**
			   *L'objet "les_calories" de type Element récupération l'élements "description" du noeud "food".
		           */
				    final Element les_calories = (Element) TabFood[lm].getElementsByTagName("calories").item(0);

			  /**
			   * Récuperation pour chaque colonne de la matrice "MatriceFoodElement" la valeur d'un element qui est de type  				   * String à l'aide de la méthode "getTextContent()".
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
			   * Parcourir la matrice "MatriceFoodElement" et afficher son contenu, la variable "m" de type int est le 				   * compteur des colonnes.
		           */			 	
	    
			    for (int m = 0; m< kl; m++) {
			    System.out.println(MatriceFoodElement[lm][m]);

			    }
        		lm++;		        	

////////////////////////////return matrice
		        }

		    }			
	        }


	  /**
	   * @exception: ParserConfigurationException
	   * Une exeption "e" de type "ParserConfigurationException" se déclanche s'il y a des erreurs au niveau de la configuration du 	   * parseur et on demande d'afficher tout ces erreurs à l'aide de la méthode printStackTrace() (affiche l'exception et l'état de 	     * la pile d'exécution au moment de son appel(from google)!!!!!!!!!!!!!!!).			 	
	   */ 
	        catch (final ParserConfigurationException e) {
	            e.printStackTrace();
	        }
	  /**
	   * @exception: SAXException
	   * Une exeption "e" de type "SAXException" se déclanche s'il y a des erreurs dans l'analyse du 
	   * document XML parseur et on demande d'afficher tout ces erreurs à l'aide de la méthode printStackTrace() (affiche l'exception  	      * et l'état de la pile d'exécution au moment de son appel(from google)!!!!!!!!!!!!!!!).			 	
	   */

	        catch (final SAXException e) {
	            e.printStackTrace();
	        }
	  /**
	   * @exception: IOException
	   * Une exeption "e" de type "IOException" se déclanche s'il y a des erreurs aux flux d'entrées lors de l'importation du 
	   * document XML parseur et on demande d'afficher tout ces erreurs à l'aide de la méthode printStackTrace() (affiche l'exception  	      * et l'état de la pile d'exécution au moment de son appel(from google)!!!!!!!!!!!!!!!).			 	
	   */
	        catch (final IOException e) {
	            e.printStackTrace();
	        }		
	    }
	}
