package edu.papolicy.controllers;

import edu.papolicy.daos.CodeDAO;
import edu.papolicy.models.Code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/codes")
public class CodeController {
    @Autowired
    private CodeDAO codeDAO;

    @RequestMapping(method=RequestMethod.GET, value="/{tableName}")
    public List<Code> getCodes(@PathVariable String tableName){ return codeDAO.list(tableName); }

	@RequestMapping(method=RequestMethod.GET, value="/{tableName}/{id}")
	public Code getCode(@PathVariable String tableName, @PathVariable int id){ return codeDAO.find(tableName,id); }
}
