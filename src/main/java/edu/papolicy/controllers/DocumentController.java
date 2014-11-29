package edu.papolicy.controllers;

import edu.papolicy.daos.DocumentDAO;

import edu.papolicy.models.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {
	@Autowired DocumentDAO documentDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/{tableName}")
    public ResponseEntity getDocuments(@PathVariable String tableName) {
        return new ResponseEntity<List<Object>>(documentDAO.findDocuments(tableName), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{tableName}/{id}")
    public ResponseEntity getDocument(@PathVariable String tableName, @PathVariable String id){
        return new ResponseEntity<Object>(documentDAO.findDocument(tableName, id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{tableName}/{id}/codes")
    public ResponseEntity getDocumentCodes(@PathVariable String tableName, @PathVariable String id, @RequestBody Code CodeObj){
        return new ResponseEntity<Object>(documentDAO.findDocumentCodes(tableName, id), HttpStatus.OK);
    }
}