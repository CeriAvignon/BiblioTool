package ObjectsToXml;

import java.io.*;
import javax.xml.bind.*;

/*
 * la classe ArticleXml permet de definier deux methode:
 * marshallObject: qui permet de recevoir un objet  et creer un fichier xml 
 * contient cet objet.
 * marshallListObject: il recoit  une list d'objet(article) pour les  ecrire dans un fichier 
 * xml 
 * */
public class ArticleXml {

	public void ObjectToXml(Article a){
		
		try{
			
			JAXBContext jc=JAXBContext.newInstance(Article.class);
			Marshaller ms=jc.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
			ms.marshal(a, System.out);
			ms.marshal(a, new File("article.xml"));
		}catch(Exception e){
			System.out.println(e.getMessage());
				
			
		}
		
	}
public void ListObjectToXml(ListArticles list){
		try{
		    JAXBContext jc=JAXBContext.newInstance(ListArticles.class);
			Marshaller ms=jc.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
			ms.marshal(list, System.out);
			ms.marshal(list, new File("Listarticle.xml"));
		}catch(Exception e){
			System.out.println(e.getMessage());
				
			
		}
		
	}

}
