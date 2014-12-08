package edu.papolicy.daos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.papolicy.models.Code;
import edu.papolicy.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.SQLQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class DocumentDAOImpl implements DocumentDAO {
	@Autowired private SessionFactory sessionFactory;
    @Autowired private TablesDAO tablesDAO;

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
    public List<Object> findDocuments(String docType) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT * FROM " + docType + " Order By ID Desc");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }
    @Override
    @Transactional
    public List<Object> findDocumentsNoBatch(String tableName) {
        Session sess = sessionFactory.getCurrentSession();
        Integer tableID = tablesIDByName(tableName);
        SQLQuery query = sess.createSQLQuery("SELECT * FROM " + tableName + " ns " +
                "WHERE ns.ID NOT IN (SELECT bd.DocumentID FROM BatchDocument bd " +
                "WHERE bd.TablesID = " + tableID + ") Order By ID Desc");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }
    @Override
    @Transactional
    public Object findDocument(String docType, int id) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT * FROM " + docType + " WHERE ID = " + id);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.uniqueResult();
    }
    @Override
    @Transactional
    public List<Map<String, String>> findDocumentCodes(String docType, int id) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT * FROM PAPolicy.UserPolicyCode WHERE documentID = " + id);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }
    @Override
    @Transactional
    public void addDocumentCode(String email, String tableName, int docid, int batchid, int codeid) {
        //set some initial variables to make the logic below more straightforward
        Session sess = sessionFactory.getCurrentSession();
        Integer tableID = tablesIDByName(tableName);
        SQLQuery query = sess.createSQLQuery("SELECT Num FROM TablesMatchingCodesNum WHERE Tables_ID = " + tableID);
        Integer maxNumOfCodes = (Integer) query.uniqueResult();


        //take the batchID
        //count how many UserPolicyCodes there currently are for that document/table.
        //if the maxCodes = numberOfCodes
        //set the final code
        //insert into UserPolicyCode
        //endIf
        //if there are too many codes
        //throw exception

        query = sess.createSQLQuery("SELECT Code FROM UserPolicyCode WHERE DocumentID = " + docid + " AND TablesID = " + tableID);
        List<Integer> userPolicyCodes = query.list();
        Integer matches = 1; //because the codeid always matches itsself
        if (userPolicyCodes.size() == maxNumOfCodes) { //if there is already the max value of userPolicyCodes in the database, this must be a tiebreak.
            //insert into UserPolicyCode
            insertUserPolicyCode(email, tableName, docid, batchid, codeid);
            //set the final code
            updateDocumentFinalCode(tableName, docid, batchid, codeid);
        } else if (userPolicyCodes.size() < maxNumOfCodes) { // enter this logic-block if the size of less than our maxNumOfCodes
            for (int i = 0; i <= userPolicyCodes.size() - 1; i++) { // keep looping while i is less than the size minus 1 (to avoid nullpointer error)
                if (userPolicyCodes.get(i) == codeid) { // we are comparing to the codeid submitted by the user
                    matches++;
                    // if we have reached the number of matches needed, exit the loop.
                    if (matches == maxNumOfCodes) break;
                }
            }
            // check again and do what needs to be done.
            if (matches == maxNumOfCodes) {
                //insert into UserPolicyCode
                insertUserPolicyCode(email, tableName, docid, batchid, codeid);
                //set final code
                updateDocumentFinalCode(tableName, docid, batchid, codeid);
            } else {
                //insert into UserPolicyCode
                insertUserPolicyCode(email, tableName, docid, batchid, codeid);
            }
        }
    }

    @Override
    @Transactional
    public List<Object> findDocumentsNoCodes(String tableName, int batchid, String email) {
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT * FROM " + tableName + " WHERE ID IN( " +
                        "SELECT DocumentID FROM BatchDocument WHERE DocumentID NOT IN(" +
                        "SELECT DocumentID FROM UserPolicyCode " +
                        "WHERE Email = '" + email + "' AND BatchID = " + batchid + ") AND BatchID = " + batchid + ");");
        //todo: fix this stuff :( not taking the batchID into consideration?
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }

    // a few helper functions
    public void insertUserPolicyCode(String email, String tableName, int docid, int batchid, int codeid) {
        Session sess = sessionFactory.getCurrentSession();
        Integer tableID = tablesIDByName(tableName);
        String codeName = tableCodeByID(tableID);
        SQLQuery query = sess.createSQLQuery("INSERT INTO UserPolicyCode (Email, DocumentID ,TablesID, BatchID, " + codeName  +
                ") VALUES ('" + email + "'," + docid + "," + tableID + "," + batchid + "," + codeid+ ");");
        try {query.executeUpdate(); }
        catch (Exception e){
            query = sess.createSQLQuery("UPDATE UserPolicyCode SET Code = " + codeid +
                    " WHERE (Email = '" + email + "' and DocumentID = " + docid + " and TablesID = " + tableID + " AND BatchID = " + batchid + ");");
            query.executeUpdate();
        }
    }

    public void updateDocumentFinalCode(String tableName, int docid, int batchid, int codeid){
        Session sess = sessionFactory.getCurrentSession();
        Integer tableID = tablesIDByName(tableName);
        String codeName = tableCodeByID(tableID);
        //sets the document as complete.
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        SQLQuery query = sess.createSQLQuery("UPDATE BatchDocument SET DateCompleted = '" + dateString.toString() + "' WHERE DocumentID = " + docid + " AND TablesID = " + tableID + " AND BatchID = " + batchid);
        query.executeUpdate();
        query = sess.createSQLQuery("UPDATE " + tableName + " SET " + codeName + " = " + codeid +
                " WHERE ID = " + docid);
        query.executeUpdate();

    }
    public int tablesIDByName(String tableName){
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT ID FROM Tables WHERE TableName = '" + tableName + "'");
        return (Integer) query.uniqueResult();
    }
    public String tableCodeByID(int tablesID){
        Session sess = sessionFactory.getCurrentSession();
        SQLQuery query = sess.createSQLQuery("SELECT CodeColumn FROM Tables WHERE ID = " + tablesID);
        return (String) query.uniqueResult();
    }
}
