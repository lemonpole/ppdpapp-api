package edu.papolicy.daos;

import java.util.*;

import com.sun.rowset.internal.Row;
import org.hibernate.*;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
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
    public List<Map<String, String>> findDocuments(String docType) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT * FROM " + docType + " Order By ID Desc LIMIT 10");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String,String>> ResultsMapList=query.list();
        return ResultsMapList;
    }

    @Override
    @Transactional
    public List<Map<String, String>> findDocument(String docType, String id) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT * FROM " + docType + " WHERE ID = " + id);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String,String>> ResultsMapList=query.list();

        return ResultsMapList;
    }

}
