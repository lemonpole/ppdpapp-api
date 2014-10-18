package edu.papolicy.controllers;

import edu.papolicy.daos.BatchDAO;
import edu.papolicy.models.Batch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/batches")
public class BatchController {
	@Autowired
	private BatchDAO batchDAO;

	@RequestMapping(method=RequestMethod.GET)
	public List<Batch> getBatches(){
		List<Batch> Batches = batchDAO.list();
		return Batches;
	}

	@RequestMapping(method=RequestMethod.POST)
	public void postBatches(){}
}