package edu.papolicy.controllers;

import edu.papolicy.daos.BatchDAO;
import edu.papolicy.models.Batch;
import edu.papolicy.models.User;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batches")
public class BatchController {
	@Autowired
	private BatchDAO batchDAO;

	@RequestMapping(method=RequestMethod.GET)
	public List<Batch> getBatches(){
		return batchDAO.list();
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
}