package edu.papolicy.daos;

import java.util.List;
import java.util.Map;

import edu.papolicy.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.SQLQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class DocumentDAOImpl implements DocumentDAO {
	@Autowired private SessionFactory sessionFactory;
    @Autowired private TablesDAO tablesDAO;

	public DocumentDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Override
	@Transactional
	public Object find(String docType, int id){
		Object docObj = sessionFactory.getCurrentSession().get(Object.class, id);
		return docObj;
	}
	@Override
    @Transactional
    public List<Object> findDocuments(String docType) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT * FROM " + docType + " Order By ID Desc");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }
    @Override
    @Transactional
    public List<Object> findDocumentsNoBatch(String docType) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT ID FROM Tables WHERE TableName = '" + docType +"'");
        String tableID = query.uniqueResult().toString();
        query = sess.createSQLQuery("SELECT * FROM " + docType + " ns " +
                "WHERE ns.ID NOT IN (SELECT bd.DocumentID FROM BatchDocument bd " +
                "WHERE bd.TablesID = " + tableID + ") Order By ID Desc");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }
    @Override
    @Transactional
    public Object findDocument(String docType, int id) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT * FROM " + docType + " WHERE ID = " + id);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.uniqueResult();
    }
    @Override
    @Transactional
    public List<Map<String, String>> findDocumentCodes(String docType, int id) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT * FROM PAPolicy.UserPolicyCode WHERE documentID = " + id);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }
    @Override
    @Transactional
    public void addDocumentCode(User user, String tableName, int docid, int codeid) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("INSERT INTO UserPolicyCode (Email, DocumentID ,TablesID, BatchID, Code)" +
                "VALUES ('admin@temple.edu'," + docid + ", (SELECT ID FROM Tables WHERE TableName = '" + tableName + "'), 1," + codeid+ ");");
                //"VALUES ('"+user.getEmail() + "'," + docid + ", (SELECT ID FROM Tables WHERE TableName = '" + tableName + "')," + codeid+ ");");
        query.executeUpdate();
        //try {query.executeUpdate(); }
        //catch (Exception e){
        //    query = sess.createSQLQuery("UPDATE UserPolicyCode SET Code = " + codeid +
        //            " WHERE (Email = '" + user.getEmail() + "' and DocumentID = " + docid + " and TablesID = (SELECT ID FROM Tables WHERE TableName = '" + tableName + "'));");
        //    query.executeUpdate();
        //}
    }

    @Override
    @Transactional
    public List<Object> findDocumentsNoCodes(String tableName, int batchid, String email) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT * FROM " + tableName +
                " WHERE ID NOT IN(SELECT DocumentID FROM UserPolicyCode" +
                "    WHERE Email = '" + email + "' AND BatchID = " + batchid +");");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }
}
