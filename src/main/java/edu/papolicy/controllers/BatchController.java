package edu.papolicy.controllers;

import edu.papolicy.daos.BatchDAO;
import edu.papolicy.daos.UserDAO;
import edu.papolicy.models.Batch;
import edu.papolicy.models.User;
import edu.papolicy.services.Account;

import java.util.List;

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
	public ResponseEntity getBatch(@PathVariable int id){
		return new ResponseEntity<Object>(batchDAO.find(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/users")
	public ResponseEntity getBatchUsers(@PathVariable int id){
		return new ResponseEntity<List<User>>(batchDAO.findUsers(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/documents")
	public ResponseEntity getDocuments(@PathVariable int id){
		return new ResponseEntity<List<Object>>(batchDAO.findDocuments(id), HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity postBatch(@RequestBody Batch batchObj){
		return new ResponseEntity<Batch>(batchDAO.save(batchObj), HttpStatus.OK);
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