package edu.papolicy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/roles")
public class RoleController {
	@RequestMapping(method=RequestMethod.GET)
	public void getRoles(){}

	@RequestMapping(method=RequestMethod.POST)
	public void postRoles(){}
}