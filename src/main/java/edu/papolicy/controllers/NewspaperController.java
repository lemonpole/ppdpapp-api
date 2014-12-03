package edu.papolicy.controllers;

import edu.papolicy.daos.NewspaperDAO;
import edu.papolicy.models.Newspaper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/Newspapers")
public class NewspaperController {
    @Autowired private NewspaperDAO NewspaperDAO;

    @RequestMapping(method=RequestMethod.GET)
    public List<Newspaper> getNewspapers(){
        List<Newspaper> Newspapers = NewspaperDAO.list();
        return Newspapers;
    }

    @RequestMapping(method=RequestMethod.POST)
    public void postNewspapers(){}
}
