package edu.papolicy.daos;

import edu.papolicy.models.Document;
import java.util.List;

import org.hibernate.Criteria;
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
}
