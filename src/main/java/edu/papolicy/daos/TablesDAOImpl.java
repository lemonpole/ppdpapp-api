package edu.papolicy.daos;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.loader.custom.Return;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TablesDAOImpl implements TablesDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public TablesDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Object findByID(int id) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT * FROM Tables WHERE ID = " + id);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.uniqueResult();
    }

    @Override
    @Transactional
    public Object findByName(String tableTitle) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT * FROM Tables WHERE TableName = '" + tableTitle + "'");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.uniqueResult();
    }

    @Override
    @Transactional
    public List<Object> findTables() {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT * FROM Tables");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }

    public String findCodeColumnName(int tablesID){
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT CodeColumn FROM Tables WHERE ID = " + tablesID);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return (String) query.uniqueResult();
    }

    public boolean MajorOnly(int tablesID){
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT MajorOnly FROM Tables WHERE ID = " + tablesID);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        String s = (String) query.uniqueResult();
        if (s.equals("1")){ return true; }
        if (s.equals("0")){ return false;}
        else throw new IllegalArgumentException(s+" problem with the boolean Code");
    }
}
