package edu.papolicy.controllers;

import edu.papolicy.daos.FilterDAO;
import edu.papolicy.models.Filter;

import java.util.List;

import edu.papolicy.models.User;
import edu.papolicy.services.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filters")
public class FilterController {
    @Autowired private FilterDAO FilterDAO;
    @Autowired private Account accountSvc;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity getFilters(@RequestParam(value="token") String token){
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        return new ResponseEntity<List<Filter>>(FilterDAO.list(), HttpStatus.OK);}

    @RequestMapping(method=RequestMethod.GET, value="/{id:.+}")
    public ResponseEntity getFilter(@PathVariable Integer id, @RequestParam(value="token") String token){
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        return new ResponseEntity<Filter>(FilterDAO.find(id), HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity postFilter(@RequestBody Filter filterObj, @RequestParam(value="token") String token){
            User user = null;
            try { user = accountSvc.doAuthentication(token); }
            catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        return new ResponseEntity<Filter>(FilterDAO.save(filterObj), HttpStatus.OK); }
}

