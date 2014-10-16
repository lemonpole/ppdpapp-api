package edu.papolicy.daos;

import java.util.List;
import edu.papolicy.models.Role;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class RoleDAOImpl implements RoleDAO {
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
}
