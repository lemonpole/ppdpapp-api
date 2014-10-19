package edu.papolicy.daos;

import edu.papolicy.models.Code;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CodeDAOImpl implements CodeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public CodeDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Code> list(){
        List<Code> listCodes = (List<Code>) sessionFactory.getCurrentSession().createCriteria(Code.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return listCodes;
    }
}
