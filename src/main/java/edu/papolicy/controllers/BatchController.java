package edu.papolicy.controllers;

import edu.papolicy.daos.BatchDAO;
import edu.papolicy.daos.UserDAO;
import edu.papolicy.models.Batch;
import edu.papolicy.models.User;
import edu.papolicy.services.Account;
import java.util.List;
import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batches")
public class BatchController {
	@Autowired private BatchDAO batchDAO;
	@Autowired private UserDAO userDAO;
	@Autowired private Account accountSvc;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity getBatches(@RequestParam(value="token") String token){
		User user = null;
		try { user = accountSvc.doAuthentication(token); }
		catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }

		if(user.getRole().getRoleID() > 1) return new ResponseEntity<List<Batch>>(batchDAO.list(), HttpStatus.OK);
		else return new ResponseEntity<List<Batch>>(userDAO.findBatches(user.getEmail()), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Batch getBatch(@PathVariable int id){
		return batchDAO.find(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/users")
	public List<User> getBatchUsers(@PathVariable int id){
		return batchDAO.findUsers(id);
	}

	@RequestMapping(method=RequestMethod.POST)
	public Batch postBatch(@RequestBody Batch batchObj){
		return batchDAO.save(batchObj);
	}

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/add/user")
    public ResponseEntity postAddUser(@PathVariable int id, @RequestBody User userObj){
        Batch batchObj = batchDAO.find(id);
        List<User> userList = batchObj.getUsers();
        userList.add(userObj);
        batchDAO.save(batchObj);
        return new ResponseEntity<String>("user added, friend", HttpStatus.OK);
    }
}