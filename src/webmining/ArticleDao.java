package webmining;

import java.sql.Connection;

public class ArticleDao extends Dao<Article> {
	
	 public ArticleDao(Connection conn){
		 super(conn);
	 }

	@Override
	public boolean create(Article obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Article obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Article obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Article find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

