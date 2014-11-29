package edu.papolicy.daos;

import java.util.List;
import java.util.Map;

public interface DocumentDAO {
	public Object find(String docType, int id);
    public List<Object> findDocuments(String docType);
	public Object findDocument(String docType, String id);
	public List<Map<String, String>> findDocumentCodes(String docType, String id);
}
