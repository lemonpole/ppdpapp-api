package edu.papolicy.controllers;

import edu.papolicy.daos.CodeDAO;
import edu.papolicy.models.Batch;
import edu.papolicy.models.Code;

import java.util.List;

import edu.papolicy.models.User;
import edu.papolicy.services.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/codes")
public class CodeController {
    @Autowired private CodeDAO codeDAO;
    @Autowired private Account accountSvc;

    @RequestMapping(method=RequestMethod.GET, value="/{tableName}")
    public ResponseEntity getCodes(@PathVariable String tableName, @RequestParam(value="token") String token){
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        return new ResponseEntity<List<Object>>(codeDAO.list(tableName), HttpStatus.OK);
    }
	@RequestMapping(method=RequestMethod.GET, value="/{tableName}/{id}")
	public ResponseEntity getCode(@PathVariable String tableName, @PathVariable int id, @RequestParam(value="token") String token){
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        return new ResponseEntity<Object>(codeDAO.find(tableName,id), HttpStatus.OK);
    }
    @RequestMapping(method=RequestMethod.GET, value="/{tableName}/search/")
    public ResponseEntity getCodeSearch(@PathVariable String tableName, @RequestParam(value="query") String query, @RequestParam(value="token") String token){
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        return new ResponseEntity<List<Object>>(codeDAO.findSearch(tableName,query), HttpStatus.OK);
    }
}
