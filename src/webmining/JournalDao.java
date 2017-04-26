package webmining;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JournalDao extends Dao<Journal> {
	
	public JournalDao(Connection conn){
		
		super(conn);
	}

	@Override
	public boolean create(Journal obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Journal obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Journal obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Journal find(int idJour) {
		Journal journal = new Journal(idJour, null);  
		  ResultSet result=null;

		    try {
		    	result = this.connect.createStatement(
		        ResultSet.TYPE_SCROLL_INSENSITIVE, 
		        ResultSet.CONCUR_READ_ONLY
		      ).executeQuery("SELECT * FROM Journal WHERE idJour = " + idJour);
		        if(result.first())
		          journal = new Journal(idJour, result.getString("nameJour"));         
		    
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return journal;
      }
}
