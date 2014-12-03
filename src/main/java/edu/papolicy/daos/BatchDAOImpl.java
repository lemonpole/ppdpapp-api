package edu.papolicy.daos;

import edu.papolicy.models.Batch;
import edu.papolicy.models.User;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class BatchDAOImpl implements BatchDAO {
	@Autowired private SessionFactory sessionFactory;

	public BatchDAOImpl(SessionFactory sessionFactory){ this.sessionFactory = sessionFactory; }

	@Override
	@Transactional
	public List<Batch> list(){
		return (List<Batch>) sessionFactory.getCurrentSession().createCriteria(Batch.class).list();
	}

	@Override
	@Transactional
	public Batch find(int id){
		return (Batch) sessionFactory.getCurrentSession().get(Batch.class, id);
	}

	@Override
	@Transactional
	public Batch save(Batch batchObj){
		sessionFactory.getCurrentSession().saveOrUpdate(batchObj);
		return batchObj;
	}

	@Override
	@Transactional
	public void delete(int id){
		Session sess = sessionFactory.getCurrentSession();
		Batch batchObj = (Batch) sess.get(Batch.class, id);

		try { sess.delete(batchObj); }
		catch(Exception e){ System.out.println(e.getMessage()); }

		// manually delete the associated docs from BatchDocuments since we manually added them.
		try {
			SQLQuery query = sess.createSQLQuery("DELETE FROM BatchDocument WHERE BatchID = " + id);
			query.executeUpdate();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	@Override
	@Transactional
	public List<User> findUsers(int id){
		Batch batchObj = (Batch) sessionFactory.getCurrentSession().get(Batch.class, id);
		return batchObj.getUsers();
	}

	@Override
	@Transactional
	public List<Object> findDocuments(int id){
		// find batch.
		// is it file_id?
		// find the document types this batch consists of.
		// return mapping of it.
		SQLQuery query = null;
		Session sess = sessionFactory.getCurrentSession();

		Batch batchObj = (Batch) sess.get(Batch.class, id);
		query = sess.createSQLQuery("SELECT TableName FROM Tables WHERE ID = (SELECT TablesID FROM Batches WHERE BatchID = " + id + ")");
		String docType = query.uniqueResult().toString();

		query = sess.createSQLQuery("SELECT * FROM " + docType + " AS nc " +
			"LEFT JOIN BatchDocument AS bd ON nc.ID = bd.DocumentID " +
			"WHERE bd.BatchID = " + id);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return (List<Object>) query.list();
	}

	@Override
	@Transactional
	public void addDocument(int batchID, int docID) {
		Session sess = sessionFactory.getCurrentSession();
		SQLQuery query = sess.createSQLQuery("SELECT ID FROM Tables WHERE ID = (SELECT TablesID FROM Batches WHERE BatchID = " + batchID + ")");
		String tableID = query.uniqueResult().toString();
		query = sess.createSQLQuery("INSERT INTO BatchDocument (DocumentID, TablesID ,BatchID)" +
				"VALUES ("+ docID + "," + tableID + "," + batchID + ");");
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void deleteDocument(int batchID, int docID) {
		Session sess = sessionFactory.getCurrentSession();


		SQLQuery query = sess.createSQLQuery("SELECT ID FROM Tables WHERE ID = (SELECT TablesID FROM Batches WHERE BatchID = " + batchID + ")");
		String tableID = query.uniqueResult().toString();

		// manually delete the associated docs from BatchDocuments since we manually added them.
		try {
			query = sess.createSQLQuery("DELETE FROM BatchDocument WHERE BatchID = " + batchID + " AND TablesID = " + tableID + " AND DocumentID = " + docID); //
			query.executeUpdate();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void deleteUser(int batchID, String email) {
		Session sess = sessionFactory.getCurrentSession();
		try {
			SQLQuery query = sess.createSQLQuery("DELETE FROM BatchUser WHERE BatchID = " + batchID + " AND Email = '" + email + "'");
			query.executeUpdate();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}


}