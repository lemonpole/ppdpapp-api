package edu.papolicy.controllers;

import edu.papolicy.daos.RoleDAO;
import edu.papolicy.models.User;
import edu.papolicy.models.Role;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/roles")
public class RoleController {
	@Autowired
	private RoleDAO roleDAO;

	@RequestMapping(method=RequestMethod.GET)
	public List<Role> getRoles(){
		List<Role> roles = roleDAO.list();
		return roles;
	}

	@RequestMapping(method=RequestMethod.POST)
	public void postRoles(){}
}