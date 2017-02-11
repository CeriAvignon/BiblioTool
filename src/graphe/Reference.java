package graphe;

public class Reference {
	private int id;
	// objet de type article
	private int source;
	private int target;
	private static int i = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public Reference(int source, int target) {
		super();
		this.source = source;
		this.target = target;
	}

	public Reference() {
		super();
	}

}
