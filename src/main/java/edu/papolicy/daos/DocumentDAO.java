package edu.papolicy.daos;

import edu.papolicy.models.User;

import java.util.List;
import java.util.Map;

public interface DocumentDAO {
	public Object find(String docType, int id);
    public List<Object> findDocuments(String docType);
	public List<Object> findDocumentsNoBatch(String docType);
	public Object findDocument(String docType, String id);
	public List<Map<String, String>> findDocumentCodes(String docType, String id);
	public void addDocumentCode(User user, String tableName, int docid, int codeid);
}
