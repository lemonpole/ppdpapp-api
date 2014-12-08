package edu.papolicy.controllers;

import edu.papolicy.daos.UserDAO;
import edu.papolicy.models.Batch;
import edu.papolicy.models.User;

import java.util.List;

import edu.papolicy.services.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired private UserDAO userDAO;
    @Autowired private Account accountSvc;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity getUsers(@RequestParam(value="token") String token){
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        return new ResponseEntity<List<User>>(userDAO.list(), HttpStatus.OK);
    }
	@RequestMapping(method=RequestMethod.GET, value="/{email:.+}")
	public ResponseEntity getUser(@PathVariable String email, @RequestParam(value="token") String token){
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        return new ResponseEntity<User>(userDAO.find(email), HttpStatus.OK);
	}
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity postUser(@RequestBody User userObj, @RequestParam(value="token") String token){
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        return new ResponseEntity<User>(userDAO.save(userObj), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{email:.+}/batches")
    public ResponseEntity getUserBatches(@PathVariable String email, @RequestParam(value="token") String token){
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        return  new ResponseEntity<List<Batch>>(userDAO.findBatches(email), HttpStatus.OK);
    }
}
