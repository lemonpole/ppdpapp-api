package edu.papolicy.daos;

import edu.papolicy.models.Code;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CodeDAOImpl implements CodeDAO {
    @Autowired private SessionFactory sessionFactory;

    public CodeDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Object> list(String tableName){
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT MajorOnly FROM Tables WHERE TableName= '" + tableName + "'");
        int majorOnly = (Integer) query.uniqueResult();
        if(majorOnly==1){
            query = sess.createSQLQuery("SELECT * FROM MajorCode");
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            return query.list();
        }
        else{
            return (List<Object>) sess.createCriteria(Code.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        }
    }
    @Override
    @Transactional
    public List<Object> listMajorMinor(String majorOrMinor){
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT * FROM " + majorOrMinor);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }

	@Override
	@Transactional
	public Object find(String tableName, int id){
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT MajorOnly FROM Tables WHERE TableName= '" + tableName + "'");
        int majorOnly = (Integer) query.uniqueResult();
        if(majorOnly==1){
            query = sess.createSQLQuery("SELECT * FROM MajorCode WHERE Code = " + id);
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            return query.uniqueResult();
        }
        else {
            return sess.get(Code.class, id);
        }
    }

    @Override
    @Transactional
    public List<Object> findSearch(String tableName, String search) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT MajorOnly FROM Tables WHERE TableName= '" + tableName + "'");
        Integer majorOnly = (Integer) query.uniqueResult();
        String codeTable = null;
        if(majorOnly==1){
            codeTable="MajorCode";
        }
        else{
            codeTable="Code";
        }
        query = sess.createSQLQuery("SELECT * FROM " + codeTable + " WHERE Code LIKE '%" + search + "%' OR Description LIKE '%" + search + "%'");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }
}
