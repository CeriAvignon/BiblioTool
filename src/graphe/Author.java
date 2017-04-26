package graphe;

import java.util.List;

public class Author {

	private int id_auth;
	private String name_auth;
	private String first_name;
	private String affiliation;
	
	public Author(){
		
	}

	public Author(int id_auth, String name_auth, String first_name, String affiliation) {
		super();
		this.id_auth = id_auth;
		this.name_auth = name_auth;
		this.first_name = first_name;
		this.affiliation = affiliation;
	}

	public int getId_auth() {
		return id_auth;
	}

	public void setId_auth(int id_auth) {
		this.id_auth = id_auth;
	}

	public String getName_auth() {
		return name_auth;
	}

	public void setName_auth(String name_auth) {
		this.name_auth = name_auth;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}
	
	
	
}
