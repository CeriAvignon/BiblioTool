package webmining;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public Article find(int idArt) {
		Article article = new Article(idArt, null, null, null, null, idArt, idArt, null, false);
		ResultSet result=null;
		try {
		       result = this.connect.createStatement(
		        ResultSet.TYPE_SCROLL_INSENSITIVE, 
		        ResultSet.CONCUR_READ_ONLY ).executeQuery("SELECT * FROM Article  "
		        		+ "WHERE idArt="+idArt );
		        if(result.first())
		            article = new Article(idArt, result.getString("titleArt"), result.getString("urlPage"),
		            		result.getString("urlPdf"),result.getString("doi"),result.getInt("numIssue"),
		            		result.getInt("nbPage"),result.getDate("yearPub"),result.getBoolean("status"));         
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return article;
		  }
	
}

