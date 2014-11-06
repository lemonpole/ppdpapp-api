package edu.papolicy.daos;

import edu.papolicy.models.Document;
import java.util.List;

public interface DocumentDAO {
	public Object find(int id);
    List<Object> findDocuments(String tableName);
    Object findDocument(String tableName, Integer id);
}
