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
    public List<Code> list(String tableName){
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT MajorOnly FROM Tables WHERE TableName= " + tableName);
        int majorOnly = (Integer) query.uniqueResult();
        if(majorOnly==1){
            query = sess.createSQLQuery("SELECT * FROM MajorCode");
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            return query.list();
        }
        else{
            return (List<Code>) sess.createCriteria(Code.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        }
    }

	@Override
	@Transactional
	public Code find(String tableName, int id){
        return (Code) sessionFactory.getCurrentSession().get(Code.class, id);
    }
}
