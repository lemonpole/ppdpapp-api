package edu.papolicy.daos;

import edu.papolicy.models.Filter;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class FilterDAOImpl implements FilterDAO {
    @Autowired private SessionFactory sessionFactory;

    public FilterDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Filter> list(){
        List<Filter> listFilters = (List<Filter>) sessionFactory.getCurrentSession().createCriteria(Filter.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return listFilters;
    }

    @Override
    @Transactional
    public Filter find(Integer id){
        Filter filterObj = (Filter) sessionFactory.getCurrentSession().get(Filter.class, id);
        return filterObj;
    }
}
