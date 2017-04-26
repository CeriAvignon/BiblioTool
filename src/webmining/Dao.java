package webmining;

import java.sql.Connection;

public abstract class Dao <T>{
		
       protected Connection connect= null;
	 	 
		//le constructeur de la classe abstraite
		 public Dao(Connection conn){

		    this.connect = conn;
		    
		 }
			public abstract boolean create(T obj); // une méthode abstraite qui permet de créer un objet

			public abstract boolean delete(T obj); //une méthode abstraite qui permet de supprimer un objet


	        public abstract boolean update(T obj);  //une méthode abstraite qui permet de faire la mise à jour d'un objet


		    public abstract T find(int id);    // une méthode abstraite qui permet de rechercher les information d'un objet


}
