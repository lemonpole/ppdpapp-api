package edu.papolicy.controllers;

import edu.papolicy.daos.NewspaperDAO;
import edu.papolicy.models.Newspaper;

import java.util.List;

import edu.papolicy.models.User;
import edu.papolicy.services.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/Newspapers")
public class NewspaperController {
    @Autowired private NewspaperDAO NewspaperDAO;
    @Autowired private Account accountSvc;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity getNewspapers(@RequestParam(value="token") String token){
        User user = null;
        try { user = accountSvc.doAuthentication(token); }
        catch(Exception e){ return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        return new ResponseEntity<List<Newspaper>>(NewspaperDAO.list(), HttpStatus.OK);
    }
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity postNewspapers(@RequestParam(value="token") String token) {
        User user = null;
        try { user = accountSvc.doAuthentication(token);
        } catch (Exception e) { return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); }
        return null;
    }
}
