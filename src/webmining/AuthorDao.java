package webmining;

import java.sql.Connection;

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
	public Author find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 

}
