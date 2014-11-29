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

    @RequestMapping(method=RequestMethod.GET)
    public List<Code> getCodes(){ return codeDAO.list(); }

	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public Code getCode(@PathVariable int id){ return codeDAO.find(id); }
}
