package edu.papolicy.daos;

import java.util.List;

public interface DocumentDAO {
	public Object find(String docType, int id);
    public List<Object> findDocuments(String docType);
	public Object findDocument(String docType, int id);
}
