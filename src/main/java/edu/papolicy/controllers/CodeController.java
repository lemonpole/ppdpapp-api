package edu.papolicy.controllers;

import edu.papolicy.daos.CodeDAO;
import edu.papolicy.models.Code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/codes")
public class CodeController {
    @Autowired
    private CodeDAO codeDAO;

    @RequestMapping(method=RequestMethod.GET, value="/{tableName}")
    public List<Object> getCodes(@PathVariable String tableName){ return codeDAO.list(tableName); }

	@RequestMapping(method=RequestMethod.GET, value="/{tableName}/{id}")
	public Object getCode(@PathVariable String tableName, @PathVariable int id){ return codeDAO.find(tableName,id); }

    @RequestMapping(method=RequestMethod.GET, value="/{tableName}/search/")
    public Object getCodeSearch(@PathVariable String tableName, @RequestParam(value="query") String search){ return codeDAO.findSearch(tableName,search); }
}
