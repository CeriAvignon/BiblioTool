package webmining;
import java.sql.Connection;

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
	public Journal find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
