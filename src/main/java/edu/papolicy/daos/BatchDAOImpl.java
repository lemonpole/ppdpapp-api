package edu.papolicy.daos;

import edu.papolicy.models.Batch;
import edu.papolicy.models.User;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
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
	public List<Object> findDocuments(int id){
		// find batch.
		// is it file_id?
		// find the document types this batch consists of.
		// return mapping of it.
		SQLQuery query = null;
		Session sess = sessionFactory.getCurrentSession();

		Batch batchObj = (Batch) sess.get(Batch.class, id);
		query = sess.createSQLQuery("SELECT TableName FROM Tables WHERE ID = (SELECT TablesID FROM Batches WHERE BatchID = " + id + ")");
		String docType = query.uniqueResult().toString();

		query = sess.createSQLQuery("SELECT * FROM " + docType + " AS nc " +
			"LEFT JOIN BatchDocument AS bd ON nc.ID = bd.DocumentID " +
			"WHERE bd.BatchID = " + id);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return (List<Object>) query.list();
	}
}