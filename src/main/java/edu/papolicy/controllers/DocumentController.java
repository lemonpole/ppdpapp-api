package edu.papolicy.controllers;

import java.util.List;

import edu.papolicy.daos.DocumentDAO;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @RequestMapping(method = RequestMethod.GET, value = "/{tableName}")
    public List<Object> getDocuments(@PathVariable String tableName){ return DocumentDAO.findDocuments(tableName); }

    @RequestMapping(method = RequestMethod.GET, value = "/{tableName}/{id}")
    public Object getDocument(@PathVariable String tableName, @PathVariable Integer id){ return DocumentDAO.findDocument(tableName, id); }
}