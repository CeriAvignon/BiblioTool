package searchingmodule.libraries.worldCat;



public class Library {
   private String articleName;
   private String author;
   private String place;
   private int year;
   private float raiting;
   private int citationsNumber;

   public String getArticleName() {
       return articleName;
   }

   public String getAuthor() {
       return author;
   }

   public int getCitationsNumber() {
       return citationsNumber;
   }

   public String getPlace() {
       return place;
   }

   public float getRaiting() {
       return raiting;
   }

   public int getYear() {
       return year;
   }
}
