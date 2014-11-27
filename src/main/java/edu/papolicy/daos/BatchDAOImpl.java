package edu.papolicy.daos;

import edu.papolicy.models.Batch;
import edu.papolicy.models.User;
import java.util.List;
import java.util.Map;

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
	public List<User> findUsers(int id){
		Batch batchObj = (Batch) sessionFactory.getCurrentSession().get(Batch.class, id);
		return batchObj.getUsers();
	}

	@Override
	@Transactional
	public Batch save(Batch batchObj){
		sessionFactory.getCurrentSession().saveOrUpdate(batchObj);
		return batchObj;
	}

	@Override
	@Transactional
	public List<Map<String, String>> findDocuments(int id){
		// find batch.
		// is it file_id?
		// find the document types this batch consists of.
		// return mapping of it.
	}
}