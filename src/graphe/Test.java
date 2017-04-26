package graphe;
//package graphe;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Test {
//
//	public void changeStatusArticle(ListArticles a, int id_article) {
//		for (Article article : a.getArticles()) {
//			if (article.getId() == id_article) {
//				article.setStatut("True");
//				/*
//				 * cette fonction permet de changer le statut de l article avec
//				 * id_article dans la BDD réalisée par le group de BDD
//				 */
//				// ChangeStatus(id_article);
//
//			}
//		}
//	}
//
//	public static void main(String[] args) {
//		List<String> auteur = new ArrayList<String>();
//		auteur.add("auteur1");
//		auteur.add("auteur2");
//		auteur.add("auteur3");
//		List<String> reference = new ArrayList<String>();
//		auteur.add("reference1");
//		auteur.add("reference2");
//		auteur.add("reference3");
//		Article article1 = new Article(1, "titre1", auteur, 2001, "page1", 36, "journal1", "url1", reference, "false");
//		Article article2 = new Article(2, "titre2", auteur, 2002, "page2", 69, "journal2", "url2", reference, "false");
//		Article article3 = new Article(3, "titre3", auteur, 2003, "page3", 95, "journal3", "url3", reference, "false");
//		Article article4 = new Article(4, "titre4", auteur, 2004, "page4", 99, "journal", "url3", reference, "false");
//
//		ListArticles List = new ListArticles();
//		List.addToListe(article1, article2, article3, article4);
//		Test ab = new Test();
//		ab.changeStatusArticle(List, 2);
//		for (Article article : List.getArticles()) {
//			System.out.println(article.getStatut());
//
//		}
//
//	}
//
//}