package edu.papolicy.daos;

import java.util.List;
import java.util.Map;

public interface DocumentDAO {
	public Object find(String docType, int id);
    public List<Map<String, String>> findDocuments(String docType);
	public List<Map<String, String>> findDocument(String docType, String id);
}
