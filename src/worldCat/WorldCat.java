package searchingmodule.libraries.worldCat;

public class WorldCat extends Library implements Runnable{
    private String author;
    private String article;
    private String uri;
    
    public WorldCat(String author, String article, String uri) {
        System.out.println("WorldCat Search");
        this.author = author;
        this.article = article;
        this.uri = uri;
    }

    @Override
    public void run() {
        System.out.println("WorldCat Implementation");
        WorldCatSearch WC = new WorldCatSearch(author, article, uri);
    }
    
}
