package edu.papolicy.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class DocumentDAOImpl implements DocumentDAO {
	@Autowired
	private SessionFactory sessionFactory;

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
        String sql = "Select * from " + docType + " LIMIT 100";
        List<Object> documents = sess.createSQLQuery(sql).addEntity(Object.class).list();

        return documents;
    }

	@Override
    @Transactional
    public Object findDocument(String docType, int id){
        Session sess = sessionFactory.getCurrentSession();
        String sql = "Select * from " + docType + " LIMIT 100 WHERE id = " + id;
        Object document = sess.createSQLQuery(sql).addEntity(Object.class).list();
        return document;
    }
}
