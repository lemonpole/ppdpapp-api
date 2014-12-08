package edu.papolicy.daos;

import edu.papolicy.models.Code;
import java.util.List;

public interface CodeDAO {
    public List<Object> list(String tableName);
	public Object find(String tableName, int id);
}
