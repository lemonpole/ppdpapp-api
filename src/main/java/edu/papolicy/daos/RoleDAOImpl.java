package edu.papolicy.daos;

import edu.papolicy.models.Role;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class RoleDAOImpl implements RoleDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public RoleDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<Role> list(){
		List<Role> listRoles = (List<Role>) sessionFactory.getCurrentSession().createCriteria(Role.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listRoles;
	}

	@Override
	@Transactional
	public Role find(int id){
		Role roleObj = (Role) sessionFactory.getCurrentSession().get(Role.class, id);
		return roleObj;
	}
}
