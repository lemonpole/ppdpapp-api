package edu.papolicy.controllers;

import edu.papolicy.daos.TablesDAO;

import edu.papolicy.models.User;
import edu.papolicy.services.Account;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tables")
public class TablesController {
    @Autowired private TablesDAO tablesDAO;
    @Autowired private Account accountSvc;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getTables(@RequestParam(value="token") String token){
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        return new ResponseEntity<Object>(tablesDAO.findTables(), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
    public ResponseEntity getTablesByID(@PathVariable int id, @RequestParam(value="token") String token){
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        return new ResponseEntity<Object>(tablesDAO.findByID(id), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/name/{tableTitle}")
    public ResponseEntity getTablesByName(@PathVariable String tableTitle, @RequestParam(value="token") String token){
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        return new ResponseEntity<Object>(tablesDAO.findByName(tableTitle), HttpStatus.OK);
    }
}
