package edu.papolicy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@RequestMapping("/users")
	public String users(@RequestParam(value="name", required=false, defaultValue="World") String name){
		return "HELLO THERE, " + name;
	}
}