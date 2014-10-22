package edu.papolicy.daos;

import edu.papolicy.models.Batch;
import edu.papolicy.models.User;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class BatchDAOImpl implements BatchDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public BatchDAOImpl(SessionFactory sessionFactory){ this.sessionFactory = sessionFactory; }

	@Override
	@Transactional
	public List<Batch> list(){
		return (List<Batch>) sessionFactory.getCurrentSession().createCriteria(Batch.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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
		sessionFactory.getCurrentSession().save(batchObj);
		return batchObj;
	}
}