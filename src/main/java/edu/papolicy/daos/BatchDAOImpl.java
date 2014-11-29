package edu.papolicy.daos;

import edu.papolicy.models.Batch;
import edu.papolicy.models.User;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class BatchDAOImpl implements BatchDAO {
	@Autowired private SessionFactory sessionFactory;

	public BatchDAOImpl(SessionFactory sessionFactory){ this.sessionFactory = sessionFactory; }

	@Override
	@Transactional
	public List<Batch> list(){
		return (List<Batch>) sessionFactory.getCurrentSession().createCriteria(Batch.class).list();
	}

	@Override
	@Transactional
	public Batch find(int id){
		return (Batch) sessionFactory.getCurrentSession().get(Batch.class, id);
	}

	@Override
	@Transactional
	public Batch save(Batch batchObj){
		sessionFactory.getCurrentSession().saveOrUpdate(batchObj);
		return batchObj;
	}

	@Override
	@Transactional
	public List<User> findUsers(int id){
		Batch batchObj = (Batch) sessionFactory.getCurrentSession().get(Batch.class, id);
		return batchObj.getUsers();
	}

	@Override
	@Transactional
	public void findDocuments(int id){
		// find batch.
		// is it file_id?
		// find the document types this batch consists of.
		// return mapping of it.
		SQLQuery query = null;
		Session sess = sessionFactory.getCurrentSession();

		Batch batchObj = (Batch) sess.get(Batch.class, id);
		query = sess.createSQLQuery("SELECT * BatchDocument WHERE BatchID = " + id);
		List<Object> res = query.list();

		//int docTypeID = (int) res.get(0).TablesID;
		//query = sess.createSQLQuery("SELECT TableName FROM Tables WHERE ID = " + docTypeID);
		//String docType = query.uniqueResult().TableName;

		List<Object> docList = new ArrayList<Object>();
		for(Object batchDoc: res){
			// do work.'
		}
	}
}