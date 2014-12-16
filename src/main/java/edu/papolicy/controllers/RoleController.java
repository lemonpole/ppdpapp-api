package edu.papolicy.controllers;

import edu.papolicy.daos.RoleDAO;
import edu.papolicy.models.Role;

import java.util.List;

import edu.papolicy.models.User;
import edu.papolicy.services.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {
	@Autowired private RoleDAO roleDAO;
	@Autowired private Account accountSvc;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity getRoles(@RequestParam(value="token") String token){
		User user = null;
		try { user = accountSvc.doAuthentication(token); }
		catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
		return new ResponseEntity<List<Role>>(roleDAO.list(), HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity getRole(@PathVariable int id,@RequestParam(value="token") String token){
		User user = null;
		try { user = accountSvc.doAuthentication(token); }
		catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
		return new ResponseEntity<Role>(roleDAO.find(id), HttpStatus.OK);
	}
}