package edu.papolicy.controllers;

import edu.papolicy.daos.RoleDAO;
import edu.papolicy.models.User;
import edu.papolicy.models.Role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/roles")
public class RoleController {
	@Autowired
	private RoleDAO roleDAO;

	@RequestMapping(method=RequestMethod.GET)
	public List<Role> getRoles(@RequestParam(value="id", required = false, defaultValue = "0") int id){
		List<Role> roles = new ArrayList<Role>();

		if(id>0) roles.add(roleDAO.find(id));
		else roles = roleDAO.list();

		return roles;
	}
}