package edu.papolicy.controllers;

import edu.papolicy.daos.DocumentDAO;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
public class DocumentController {
	@Autowired DocumentDAO documentDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/{tableName}")
    public List<Object> getDocuments(@PathVariable String tableName){ return documentDAO.findDocuments(tableName); }

    @RequestMapping(method = RequestMethod.GET, value = "/{tableName}/{id}")
    public Object getDocument(@PathVariable String tableName, @PathVariable Integer id){ return documentDAO.findDocument(tableName, id); }
}