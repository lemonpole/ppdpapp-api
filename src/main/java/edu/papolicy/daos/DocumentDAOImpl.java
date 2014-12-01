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
        SQLQuery query = sess.createSQLQuery("SELECT * FROM " + docType + " Order By ID Desc LIMIT 10");

        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }

    @Override
    @Transactional
    public Object findDocument(String docType, String id) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT * FROM " + docType + " WHERE ID = " + id);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.uniqueResult();
    }
    public List<Map<String, String>> findDocumentCodes(String docType, String id) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT * FROM PAPolicy.UserPolicyCode WHERE documentID = " + id);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }

    @Override
    @Transactional
    public void addDocumentCode(User user, String tableName, int docid, int codeid) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT ID FROM Tables WHERE TableName = '" + tableName +"'");
        String tableID = query.uniqueResult().toString();

        query = sess.createSQLQuery("INSERT INTO UserPolicyCode (Email, DocumentID ,TablesID, Code)" +
                "VALUES ('"+user.getEmail() + "'," + docid + "," + tableID+ "," + codeid+ ");");
        try {
            query.executeUpdate();
        }
        catch (Exception e){
            query = sess.createSQLQuery("UPDATE UserPolicyCode SET Code = " + codeid +
                    " WHERE (Email = '" + user.getEmail() + "' and DocumentID = " + docid + " and TablesID = " + tableID + ");");
            query.executeUpdate();
        }

    }
}
