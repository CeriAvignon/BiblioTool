package graphe;

public class Journal {

	private int id;
	private String titre;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public void afficher() {
		System.out.println(titre);;
	}
	
	
}
