package edu.papolicy.daos;

import edu.papolicy.models.Batch;
import java.util.List;

public interface BatchDAO {
	public List<Batch> list();
	public Batch find(int id);
	public Batch save(Batch batchObj);
}
