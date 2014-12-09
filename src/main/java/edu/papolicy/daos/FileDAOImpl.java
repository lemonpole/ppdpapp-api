package edu.papolicy.daos;

import edu.papolicy.models.Batch;
import edu.papolicy.models.File;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class FileDAOImpl implements FileDAO {
    @Autowired private SessionFactory sessionFactory;

    public FileDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<File> list(){
		return (List<File>) sessionFactory.getCurrentSession().createCriteria(File.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	@Transactional
	public File find(int id){
		return (File) sessionFactory.getCurrentSession().get(File.class, id);
	}

	@Override
	@Transactional
	public File save(File fileObj) {
		sessionFactory.getCurrentSession().save(fileObj);
		return fileObj;
	}
	@Override
	@Transactional
	public Object findBatchByFileID(int fileid) {
		Session sess = sessionFactory.getCurrentSession();
		SQLQuery query = sess.createSQLQuery("SELECT * FROM Batches WHERE FileID = " + fileid);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return query.uniqueResult();
	}
	@Override
	@Transactional
	public int create(File fileObj) {
		Session sess = sessionFactory.getCurrentSession();
		System.out.println("INSERT INTO Files (Name, FileURL, DateAdded, Creator)" +
				"Values('"+ fileObj.getName() +"','"+fileObj.getFileURL()+"',"+fileObj.getDateAdded()+"',"+fileObj.getCreator()+"'");
		SQLQuery query = sess.createSQLQuery("INSERT INTO Files (Name, FileURL, DateAdded, Creator)" +
				"Values('"+ fileObj.getName() +"','"+fileObj.getFileURL()+"',"+fileObj.getDateAdded()+"',"+fileObj.getCreator()+"'");
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return query.executeUpdate();
	}
}
