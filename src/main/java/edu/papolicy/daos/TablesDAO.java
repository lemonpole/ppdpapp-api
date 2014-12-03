package edu.papolicy.daos;

import java.util.List;

public interface TablesDAO {
    public Object findByID(int id);
    public Object findByName(String tableTitle);
    public List<Object> findTables();
}
