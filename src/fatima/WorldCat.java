package fatima;

public class WorldCat extends Library implements Runnable{
    private String auteur;
    private String article;
    private String uri;
    
    public WorldCat(String author, String article, String uri) {
        System.out.println("recherch worldcat");
        this.auteur = author;
        this.article = article;
        this.uri = uri;
    }

    @Override
    public void run() {
        System.out.println("WorldCat Implementation");
        WorldCatSearch WC = new WorldCatSearch(auteur, article, uri);
    }
    
}
