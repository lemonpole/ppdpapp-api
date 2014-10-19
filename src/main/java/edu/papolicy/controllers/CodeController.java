package edu.papolicy.controllers;

import edu.papolicy.daos.CodeDAO;
import edu.papolicy.models.Code;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/Codes")
public class CodeController {
    @Autowired
    private CodeDAO CodeDAO;

    @RequestMapping(method=RequestMethod.GET)
    public List<Code> getCodes(){
        List<Code> Codes = CodeDAO.list();
        return Codes;
    }

    @RequestMapping(method=RequestMethod.POST)
    public void postCodes(){}
}
