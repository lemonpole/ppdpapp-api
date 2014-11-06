package edu.papolicy.daos;

import edu.papolicy.models.Document;
import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class DocumentDAOImpl implements DocumentDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public DocumentDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}


	@Transactional
	public List<Document> list(){
		List<Document> listDocs = (List<Document>) sessionFactory.getCurrentSession().createCriteria(Document.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listDocs;
	}
	@Override
	@Transactional
	public Document find(int id){
		Document docObj = (Document) sessionFactory.getCurrentSession().get(Document.class, id);
		return docObj;
	}

    @Transactional
    public List<Object> findDocuments(String tableName) {
        Session sess = sessionFactory.getCurrentSession();
        String sql = "Select * from " + tableName + " LIMIT 100";
        List<Object> documents = sess.createSQLQuery(sql).addEntity(Object.class).list();

        return documents;
    }

    @Transactional
    public Object findDocument(String tableName, String id){
        Session sess = sessionFactory.getCurrentSession();
        String sql = "Select * from " + tableName + " LIMIT 100 WHERE id = " + id;
        Object document = sess.createSQLQuery(sql).addEntity(Object.class).list();
        return document;
    }
}
