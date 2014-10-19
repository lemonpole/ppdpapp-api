package edu.papolicy.controllers;

import edu.papolicy.daos.RoleDAO;
import edu.papolicy.models.Role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {
	@Autowired
	private RoleDAO roleDAO;

	@RequestMapping(method=RequestMethod.GET)
	public List<Role> getRoles(){
		return roleDAO.list();
	}

	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public Role getRole(@PathVariable int id){
		return roleDAO.find(id);
	}
}