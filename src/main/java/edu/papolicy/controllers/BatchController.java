package edu.papolicy.controllers;

import edu.papolicy.daos.BatchDAO;
import edu.papolicy.daos.UserDAO;
import edu.papolicy.models.Batch;
import edu.papolicy.models.User;
import java.util.List;

import edu.papolicy.services.Account;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batches")
public class BatchController {
	@Autowired
	private BatchDAO batchDAO;
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(method=RequestMethod.GET)
	public List<Batch> getBatches(){
		User user = Account.doAuthentication();

		if (user.getRoleID() > 1){
			return batchDAO.list(); }
		else{
			return userDAO.findBatches(user.getEmail()); }
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