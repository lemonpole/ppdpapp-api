package edu.papolicy.daos;

import edu.papolicy.models.Newspaper;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class NewspaperDAOImpl implements NewspaperDAO {
    @Autowired private SessionFactory sessionFactory;

    public NewspaperDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Newspaper> list(){
        List<Newspaper> listNewspapers = (List<Newspaper>) sessionFactory.getCurrentSession().createCriteria(Newspaper.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return listNewspapers;
    }

    @Override
    @Transactional
    public void add(String name) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("INSERT INTO Newspaper (Name) VALUES (" + name +");");
        query.executeUpdate();
    }
}
