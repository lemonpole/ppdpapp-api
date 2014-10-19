package edu.papolicy.controllers;

import edu.papolicy.daos.UserDAO;
import edu.papolicy.models.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDAO userDAO;

    @RequestMapping(method=RequestMethod.GET)
    public List<User> getUsers(){
        return userDAO.list();
    }

	@RequestMapping(method=RequestMethod.GET, value="/{email:.+}")
	public User getUser(@PathVariable String email){
		return userDAO.find(email);
	}

    @RequestMapping(method=RequestMethod.POST)
    public void postUsers(){}
}
