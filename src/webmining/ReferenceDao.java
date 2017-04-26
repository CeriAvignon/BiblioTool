package webmining;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReferenceDao extends Dao<Reference> {
	
	public ReferenceDao(Connection conn){
		
		super(conn);
	}

	@Override
	public boolean create(Reference obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Reference obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Reference obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Reference find(int idArt) {
		 Reference reference = new Reference(idArt, idArt);  
		  ResultSet result=null;

		    try {
		    	result = this.connect.createStatement(
		        ResultSet.TYPE_SCROLL_INSENSITIVE, 
		        ResultSet.CONCUR_READ_ONLY
		      ).executeQuery("SELECT * FROM Reference WHERE idArt = " + idArt);
		        if(result.first())
		          reference = new Reference(idArt, result.getInt("idArt_Art"));         
		    
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return reference;
		    }

}
