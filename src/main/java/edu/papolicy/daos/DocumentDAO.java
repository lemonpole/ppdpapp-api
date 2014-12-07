package edu.papolicy.daos;

import edu.papolicy.models.User;

import java.util.List;
import java.util.Map;

public interface DocumentDAO {
	public Object find(String docType, int id);
    public List<Object> findDocuments(String docType);
	public List<Object> findDocumentsNoBatch(String docType);
	public Object findDocument(String docType, int id);
	public List<Map<String, String>> findDocumentCodes(String docType, int id);
	public void addDocumentCode(String email, String tableName, int docid, int batchid, int codeid);
	public List<Object>  findDocumentsNoCodes(String tableName, int batchid, String email);
}
