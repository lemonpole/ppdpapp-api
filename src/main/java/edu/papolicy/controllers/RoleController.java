package edu.papolicy.controllers;

import edu.papolicy.util.SessionUtil;
import edu.papolicy.models.Role;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {
	@RequestMapping(method=RequestMethod.GET)
	public List<Role> getRoles(){
		Session session = SessionUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		List<Role> res = session.createCriteria(Role.class).list();
		session.getTransaction().commit();

		return res;
	}
	@RequestMapping(method=RequestMethod.POST)
	public Role postRoles(){
		Session session = SessionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Role role = new Role();
		role.setName("Sample Role");

		session.save(role);
		session.getTransaction().commit();

		return role;
	}
}