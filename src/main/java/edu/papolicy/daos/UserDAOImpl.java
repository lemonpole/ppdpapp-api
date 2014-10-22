package edu.papolicy.daos;

import edu.papolicy.models.User;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<User> list(){
        List<User> listUsers = (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return listUsers;
    }

	@Override
	@Transactional
	public User find(String email){
		User userObj = (User) sessionFactory.getCurrentSession().get(User.class, email);
		return userObj;
	}

	@Override
	@Transactional
	public User save(User userObj){
		sessionFactory.getCurrentSession().save(userObj);
		return userObj;
	}
}
