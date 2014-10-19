package edu.papolicy.daos;

import edu.papolicy.models.Batch;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BatchDAOImpl implements BatchDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public BatchDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<Batch> list(){ return (List<Batch>) sessionFactory.getCurrentSession().createCriteria(Batch.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list(); }

	@Override
	@Transactional
	public Batch find(int id){ return (Batch) sessionFactory.getCurrentSession().get(Batch.class, id); }

	@Override
	@Transactional
	public Batch save(Batch batchObj){
		sessionFactory.getCurrentSession().save(batchObj);
		return batchObj;
	}
}
