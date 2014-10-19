package edu.papolicy.daos;

import edu.papolicy.models.Code;
import java.util.List;

public interface CodeDAO {
    public List<Code> list();
	public Code find(int id);
}
