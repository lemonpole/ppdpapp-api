package edu.papolicy.daos;

import edu.papolicy.models.Filter;
import java.util.List;

public interface FilterDAO {
    public List<Filter> list();
    public Filter find(Integer ID);
}
