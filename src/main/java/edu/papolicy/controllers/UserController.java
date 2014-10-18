package edu.papolicy.controllers;

import edu.papolicy.daos.UserDAO;
import edu.papolicy.models.User;
import edu.papolicy.models.User;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/Users")
public class UserController {
    @Autowired
    private UserDAO UserDAO;

    @RequestMapping(method=RequestMethod.GET)
    public List<User> getUsers(){
        List<User> Users = UserDAO.list();
        return Users;
    }

    @RequestMapping(method=RequestMethod.POST)
    public void postUsers(){}
}
