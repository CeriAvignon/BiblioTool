package webmining;
import java.sql.Connection;

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
	public Reference find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
