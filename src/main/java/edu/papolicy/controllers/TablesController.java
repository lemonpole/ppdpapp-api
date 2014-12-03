package edu.papolicy.controllers;

import edu.papolicy.daos.TablesDAO;

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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getTables(){
        return new ResponseEntity<Object>(tablesDAO.findTables(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
    public ResponseEntity getTablesByID(@PathVariable int id){
        return new ResponseEntity<Object>(tablesDAO.findByID(id), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/name/{tableTitle}")
    public ResponseEntity getTablesByName(@PathVariable String tableTitle){
        return new ResponseEntity<Object>(tablesDAO.findByName(tableTitle), HttpStatus.OK);
    }

}
