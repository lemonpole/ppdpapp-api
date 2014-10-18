package edu.papolicy.daos;

import edu.papolicy.models.File;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class FileDAOImpl implements FileDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public FileDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<File> list(){
        List<File> listFiles = (List<File>) sessionFactory.getCurrentSession().createCriteria(File.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return listFile;
    }
}
