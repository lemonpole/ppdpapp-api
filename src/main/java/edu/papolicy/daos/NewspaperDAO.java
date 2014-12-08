package edu.papolicy.daos;

import edu.papolicy.models.Newspaper;
import java.util.List;

public interface NewspaperDAO {
    public List<Newspaper> list();
    public void add(String name);
}
