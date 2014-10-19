package edu.papolicy.controllers;

import edu.papolicy.daos.UserDAO;
import edu.papolicy.models.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDAO userDAO;

    @RequestMapping(method=RequestMethod.GET)
    public List<User> getUsers(@RequestParam(value="email", required = false, defaultValue = "") String email){
        List<User> users = new ArrayList<User>();

	    if(email != null && !email.isEmpty()) users.add(userDAO.find(email));
	    else users = userDAO.list();

        return users;
    }

    @RequestMapping(method=RequestMethod.POST)
    public void postUsers(){}
}
