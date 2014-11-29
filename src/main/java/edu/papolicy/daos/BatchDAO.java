package edu.papolicy.daos;

import edu.papolicy.models.Batch;
import edu.papolicy.models.User;
import java.util.List;

public interface BatchDAO {
	public List<Batch> list();
	public Batch find(int id);
	public Batch save(Batch batchObj);
	public List<User> findUsers(int id);
	public List<Object> findDocuments(int id);
}
