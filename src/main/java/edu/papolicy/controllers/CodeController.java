package edu.papolicy.controllers;

import edu.papolicy.daos.CodeDAO;
import edu.papolicy.models.Batch;
import edu.papolicy.models.Code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/codes")
public class CodeController {
    @Autowired
    private CodeDAO codeDAO;

    @RequestMapping(method=RequestMethod.GET, value="/{tableName}")
    public ResponseEntity<List<Object>> getCodes(@PathVariable String tableName){
        return new ResponseEntity<List<Object>>(codeDAO.list(tableName), HttpStatus.OK);}

	@RequestMapping(method=RequestMethod.GET, value="/{tableName}/{id}")
	public ResponseEntity<Object> getCode(@PathVariable String tableName, @PathVariable int id){
        return new ResponseEntity<Object>(codeDAO.find(tableName,id), HttpStatus.OK);}

    @RequestMapping(method=RequestMethod.GET, value="/{tableName}/search/")
    public ResponseEntity<List<Object>> getCodeSearch(@PathVariable String tableName, @RequestParam(value="query") String query){
        return new ResponseEntity<List<Object>>(codeDAO.findSearch(tableName,query), HttpStatus.OK); }
}
