package edu.papolicy.daos;

import edu.papolicy.models.Code;
import java.util.List;

public interface CodeDAO {
    public List<Code> list(String tableName);
	public Code find(String tableName, int id);
}
