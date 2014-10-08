package edu.papolicy.controllers;

import edu.papolicy.util.SessionUtil;
import edu.papolicy.models.Role;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
	@RequestMapping("/roles")
	public Role roles(){
		Session session = SessionUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Role role = new Role();
		role.setName("Sample Role");
		
		session.save(role);
		session.getTransaction().commit();
		SessionUtil.shutdown();
		
		return role;
	}
}