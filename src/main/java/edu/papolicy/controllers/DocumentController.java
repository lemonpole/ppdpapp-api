package edu.papolicy.controllers;

import edu.papolicy.daos.DocumentDAO;
import java.util.List;
import java.util.Map;

import edu.papolicy.models.Code;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
public class DocumentController {
	@Autowired DocumentDAO documentDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/{tableName}")
    public List<Map<String, String>> getDocuments(@PathVariable String tableName){
        return documentDAO.findDocuments(tableName);}

    @RequestMapping(method = RequestMethod.GET, value = "/{tableName}/{id}")
    public Object getDocument(@PathVariable String tableName, @PathVariable String id){
        return documentDAO.findDocument(tableName, id); }

    @RequestMapping(method = RequestMethod.GET, value = "/{tableName}/{id}/codes")
    public Object getDocumentCodes(@PathVariable String tableName, @PathVariable String id, @RequestBody Code CodeObj){
        return documentDAO.findDocumentCodes(tableName, id); }
}