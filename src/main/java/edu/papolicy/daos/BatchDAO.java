package edu.papolicy.daos;

import edu.papolicy.models.Batch;
import edu.papolicy.models.User;
import java.util.List;

public interface BatchDAO {
	public List<Batch> list();
	public Batch find(int id);
	public Batch save(Batch batchObj);
	public void delete(int id);
	public List<User> findUsers(int id);
	public List<Object> findDocuments(int id);
	public void addDocument(int batchID, int docID);
	public void deleteUser(int batchID, String email);
}
