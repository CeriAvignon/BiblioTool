package webmining;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDao extends Dao<Author>{
	
	public AuthorDao(Connection conn){
		super(conn);
	}

	@Override
	public boolean create(Author obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Author obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Author obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Author find(int idAut) {
		Author author = new Author(idAut, null, null, null);  
		  ResultSet result=null;

		    try {
		    	result = this.connect.createStatement(
		        ResultSet.TYPE_SCROLL_INSENSITIVE, 
		        ResultSet.CONCUR_READ_ONLY
		      ).executeQuery("SELECT * FROM Author WHERE idAut = " + idAut);
		        if(result.first())
		          author = new Author(idAut, result.getString("nameAut"),result.getString("surnameAut"),
		        		  result.getString("affiliation"));         
		    
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return author;
		    }
	
	 

}
