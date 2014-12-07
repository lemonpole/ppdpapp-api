package edu.papolicy.controllers;

import edu.papolicy.daos.DocumentDAO;
import edu.papolicy.daos.UserDAO;
import edu.papolicy.models.Code;

import edu.papolicy.models.User;
import edu.papolicy.services.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {
	@Autowired DocumentDAO documentDAO;
    @Autowired private UserDAO userDAO;
    @Autowired private Account accountSvc;

    @RequestMapping(method = RequestMethod.GET, value = "/{tableName}")
    public ResponseEntity getDocuments(@PathVariable String tableName) {
        return new ResponseEntity<List<Object>>(documentDAO.findDocuments(tableName), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{tableName}/nobatch")
    public ResponseEntity getDocumentsNoBatch(@PathVariable String tableName) {
        return new ResponseEntity<List<Object>>(documentDAO.findDocumentsNoBatch(tableName), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{tableName}/{id}")
    public ResponseEntity getDocument(@PathVariable String tableName, @PathVariable int id){
        return new ResponseEntity<Object>(documentDAO.findDocument(tableName, id), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{tableName}/{id}/codes")
    public ResponseEntity getDocumentCodes(@PathVariable String tableName, @PathVariable int id){
        return new ResponseEntity<Object>(documentDAO.findDocumentCodes(tableName, id), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/{tableName}/{docid}/add/code/{codeid}")
    public ResponseEntity addDocumentCodes(@PathVariable String tableName, @PathVariable int docid, @PathVariable int codeid, @RequestParam(value="token") String token){
        //testing
        token = "k0nXf9nsC8ndoMrjgNZwDb8Lq42rHfET:1417047552017";
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        documentDAO.addDocumentCode(user, tableName, docid, codeid);
        return new ResponseEntity<String>("document code added, bud", HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{tableName}/{batchId}/nocodes")
    public ResponseEntity getDocumentNoCodes(@PathVariable String tableName, @PathVariable int batchid, @RequestParam(value="token") String token){
        //testing
        token = "k0nXf9nsC8ndoMrjgNZwDb8Lq42rHfET:1417047552017";
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        return new ResponseEntity<List<Object>>(documentDAO.findDocumentsNoCodes(tableName, id, user.getEmail()), HttpStatus.OK);
    }
}